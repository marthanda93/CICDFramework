package org.commons

import org.generic.IShellRegistry
import org.generic.IMissingObject

class ShellExecutor implements IShellRegistry, IMissingObject, Serializable {
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

	@Override
	String propertyMissing(String name) {
		"Caught missing property: $name"
	}

	@Override
	String methodMissing(String name, Object args) {
		_steps.println """
			Possible solutions: 
			String bashShell(String command)
			String batchScript(String command)
			String powerShellScript(String command)
		"""

		_steps.error "Missing method name is $name"
	}
}
