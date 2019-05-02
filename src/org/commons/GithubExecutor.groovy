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
	Boolean cloneExecutor(Boolean dependencyRepo = false) {
		_steps.println("__PAASS__")
	}

	@Override
	String cloneWithDirectory(Map appParam) {
		_steps.println("__PAASS__")
	}

	@Override
	Boolean plainClone(Map appParam) {
		if(CommonUtilities.gitValidation(appParam)) {
			_steps.println"""
				${appParam.url.getClass()}
				${CommonUtilities.stringValidation(appParam.url)}
				----
				${appParam.branch.getClass()}
				${CommonUtilities.stringValidation(appParam.branch)}
				----
				${appParam.credentialsId.getClass()}
				${CommonUtilities.stringValidation(appParam.credentialsId)}

			"""
			_steps.git(
				changelog: false,
				url: appParam.url, 
				branch: appParam.branch, 
				credentialsId: appParam.credentialsId
			)
		} else {
			_steps.error "ERROR:Git:plainClone: App Parameter validation failed!"
		}
	}

	@Override
	Boolean extendedClone(Map appParam) {
		_steps.println("__PAASS__")
	}

	@Override
	Boolean pullRequestClone(Map appParam) {
		_steps.println("__PAASS__")
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
