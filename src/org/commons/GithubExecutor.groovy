package org.commons

import org.generic.IGithubRegistry
import org.generic.IMissingObject
import org.generic.CommonUtilities

class GithubExecutor implements IGithubRegistry, IMissingObject, Serializable {
	private _steps

	GithubExecutor(_steps) {
		this._steps = _steps
	}

	@Override
	Object cloneExecutor(Map appParam, String cloneType = 'plain') {
		_steps.cleanWs()

		switch (cloneType.toLowerCase()) {
			case 'plain':
				return (_steps.env.ghprbSourceBranch && _steps.env.ghprbTargetBranch) ? extendedClone(appParam) : plainClone(appParam)
				break
			case 'parent':
				return cloneWithDirectory(appParam)
				break
			case 'lfs':
				return lfsClone(appParam)
				break
			default:
				_steps.error "ERROR:GitClone: ${cloneType} Undefined Parameter!"
				break
		}
	}

	@Override
	String cloneWithDirectory(Map appParam) {
		String path = "scmRepo_${_steps.env.BUILD_NUMBER}"

		_steps.dir ("${path}") {
			plainClone(appParam)
		}

		return path
	}

	@Override
	Boolean plainClone(Map appParam) {
		if(CommonUtilities.gitValidation(appParam)) {
			_steps.git(
				changelog: false,
				url: appParam.url, 
				branch: appParam.branch, 
				credentialsId: appParam.credentialsId
			)
		} else {
			_steps.error "ERROR:Git:plainClone: App Parameter validation failed!\n ${appParam.getClass()} \n ${appParam}"
		}
	}

	@Override
	Boolean lfsClone(Map appParam) {
		_steps.checkout(
			changelogs: false, 
			poll: true, 
			scm: [
				$class: 'GitSCM', 
				branches: [[name: "${appParam.branch}"]], 
				doGenerateSubmoduleConfigurations: false, 
				extensions: [
					[$class: 'CheckoutOption', timeout: _steps.globalPipelineSetting.githubConfig.timeout], 
					[$class: 'UserIdentity', email: "${_steps.globalPipelineSetting.githubConfig.email}", name: "${_steps.globalPipelineSetting.githubConfig.user}"], 
					[$class: 'GitLFSPull']
				], 
				submoduleCfg: [], 
				userRemoteConfigs: [[credentialsId: "${appParam.credentialsId}", url: "${appParam.url}"]]
			]
		)
	}

	@Override
	Boolean extendedClone(Map appParam) {	//Merge before Build
		if(CommonUtilities.gitValidation(appParam)) {
			String uuid = java.util.UUID.randomUUID().toString()

			_steps.checkout(
				changelogs: false, 
				poll: true, 
				scm: [
					$class: 'GitSCM', 
					branches: [[name: "${_steps.env.ghprbSourceBranch}"]], 
					doGenerateSubmoduleConfigurations: false, 
					extensions: [
						[$class: 'CheckoutOption', timeout: _steps.globalPipelineSetting.githubConfig.timeout], 
						[$class: 'UserIdentity', email: "${_steps.globalPipelineSetting.githubConfig.email}", name: "${_steps.globalPipelineSetting.githubConfig.user}"], 
						[$class: 'PreBuildMerge', options: [mergeRemote: "${uuid}", mergeTarget: "${_steps.env.ghprbTargetBranch}"]]
					], 
					submoduleCfg: [], 
					userRemoteConfigs: [[
						credentialsId: "${appParam.credentialsId}", 
						name: "${uuid}", 
						url: "${appParam.url}"
					]]
				]
			)
		} else {
			_steps.error "ERROR:Git:plainClone: App Parameter validation failed!"
		}
	}

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING GithubExecutor: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.error "METHODMISSING GithubExecutor: Caught missing method: $name"
    }
}
