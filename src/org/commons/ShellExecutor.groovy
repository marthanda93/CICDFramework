package org.commons

import org.generic.IShellRegistry
//import org.generic.MissingObject

class ShellExecutor implements IShellRegistry, Serializable {
	private _steps
	Map config

	ShellExecutor(_steps, config = [:]) {
		this._steps = _steps
		this.config = config
	}

	@Override
	String bashShell(String command) {
		try {
			_steps.sh(script: "${command}", returnStdout: true)
		} catch(e) {
			_steps.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String batchScript(String command) {
		try {
			_steps.bat(script: "${command}", returnStdout: true)
		} catch(e) {
			_steps.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String powerShellScript(String command) {
		try {
			_steps.powershell(script: "${command}", returnStdout: true)
		} catch(e) {
			_steps.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}
}
