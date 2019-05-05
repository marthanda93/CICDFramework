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

	// profile = settingProfile && repoType = mono/micro
	@Override
	Boolean extendedBuild(String profile = '', Boolean repoType = 'Micro', String codebasePath = '') {
		_steps.cleanWs()

		switch (repoType.toLowerCase()) {
			case 'micro':
				return (_steps.env.ghprbSourceBranch && _steps.env.ghprbTargetBranch) ? extendedClone(appParam) : plainClone(appParam)
				break
			case 'mono':
				return cloneWithDirectory(appParam)
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
        _steps.error "PROPERTYMISSING ShellExecutor: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.error "METHODMISSING ShellExecutor: Caught missing method: $name"
    }
}
