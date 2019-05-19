package org.commons

import org.generic.IShellRegistry
import org.generic.IMissingObject

class ShellExecutor implements IShellRegistry, IMissingObject, Serializable {
	private _steps

	ShellExecutor(_steps) {
		this._steps = _steps
	}

	@Override
	String bashShell(String command) {
		try {
			return _steps.sh(script: "${command}")
		} catch(e) {
			_steps.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String bashShellOutput(String command) {
		try {
			return _steps.sh(script: "set +x; ${command}; set -x", returnStdout: true)
		} catch(e) {
			_steps.error "ERROR:bashShellOutput: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String bashShellStatus(String command) {
		try {
			return _steps.sh(script: "${command}", returnStatus: true)
		} catch(e) {
			_steps.error "ERROR:bashShellOutput: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String batchScript(String command) {
		try {
			return _steps.bat(script: "${command}", returnStdout: true)
		} catch(e) {
			_steps.error "ERROR:batchScript: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String powerShellScript(String command) {
		try {
			return _steps.powershell(script: "${command}", returnStdout: true)
		} catch(e) {
			_steps.error "ERROR:powerShellScript: Failed with \n${e.getMessage()}"
		}
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
