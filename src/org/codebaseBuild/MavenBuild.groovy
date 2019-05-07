package org.codebaseBuild

import org.generic.IMavenRegistry
import org.generic.IMissingObject

import org.generic.CommonUtilities
import org.stepRegistry.ContextRegistry

class MavenBuild implements IMavenRegistry, IMissingObject, Serializable {
	private _steps

	MavenBuild(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean environmentSetup() {
		_steps.println('__PASS__')
	}

	@Override
	Boolean tuneMavenBuild() {
		_steps.println('__PASS__')
	}

	@Override
	Boolean dependencySteup() {
		_steps.println('__PASS__')
	}

	@Override
	Boolean pruneMavenArtifact() {
		_steps.println('__PASS__')
	}

	@Override
	Object mvnBuild(String profile = '', List codebasePaths = []) {
		if(CommonUtilities.listValidation(codebasePaths)) {
			_steps.println('__PASS__')
		} else {
			if(CommonUtilities.stringValidation(profile)) {
				_steps.println('__PASS__')
			} else {
				_steps.sh(script: "${_steps.globalPipelineSetting.maven.command}")
				return ContextRegistry.getContext().getShellExecutor().bashShell(_steps.globalPipelineSetting.maven.command)
			}
		}
	}

	// profile = settingProfile && repoType = mono/micro
	@Override
	Boolean extendedBuild(String profile = '', String repoType = 'Micro', List codebasePaths = []) {
		switch (repoType.toLowerCase()) {
			case 'micro':
				mvnBuild()
				break
			case 'mono':
				return false
				break
			default:
				_steps.error "ERROR:MavenBuild: ${repoType} Undefined Parameter!"
				break
		}
	}

	@Override
	Void buildStats() {
		_steps.println('__PASS__')
	}

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING MavenBuild: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.error "METHODMISSING MavenBuild: Caught missing method: $name"
    }
}
