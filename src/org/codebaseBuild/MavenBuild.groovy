package org.codebaseBuild

import org.generic.IMavenRegistry
import org.generic.IMissingObject

class MavenBuild implements IMavenRegistry, IMissingObject, Serializable {
	private _steps

	MavenBuild(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean environmentSetup() {
		_steps.println('__PASS__')
	}

	Boolean tuneMavenBuild() {
		_steps.println('__PASS__')
	}

	Boolean dependencySteup() {
		_steps.println('__PASS__')
	}

	Boolean pruneMavenArtifact() {
		_steps.println('__PASS__')
	}

	// profile = settingProfile && repoType = mono/micro
	Boolean extendedBuild(String profile, Boolean repoType, String codebasePath) {
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
