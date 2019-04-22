package org.commons

import org.generic.IShellRegistry
import org.generic.MissingObject

class ShellExecutor extends Serializable IShellRegistry MissingObject {
	private final stepExecutor
	Map config

	ShellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	@Override
	String bashShell(String command) {
		try {
			stepExecutor.sh(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExecutor.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String batchScript(String command) {
		try {
			stepExecutor.bat(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExecutor.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String powerShellScript(String command) {
		try {
			stepExecutor.powershell(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExecutor.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}
}