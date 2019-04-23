package org.commons

import org.generic.IShellRegistry.One
//import org.generic.MissingObject

class ShellExecutor implements Serializable, One {
	private final stepExe
	Map config

	ShellExecutor(stepExe, config = [:]) {
		this.stepExe = stepExe
		this.config = config
	}

	@Override
	String bashShell(String command) {
		try {
			stepExe.sh(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExe.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String batchScript(String command) {
		try {
			stepExe.bat(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExe.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String powerShellScript(String command) {
		try {
			stepExe.powershell(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExe.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}
}