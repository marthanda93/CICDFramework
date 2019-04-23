package org.commons

import org.generic.IShellRegistry
//import org.generic.MissingObject

class ShellExecutor implements Serializable, IShellRegistry {
	private final stepExe
	Map config

	ShellExecutor(stepExe, config = [:]) {
		this.stepExe = stepExe
		this.config = config
	}

	@Override
	String One.bashShell(String command) {
		try {
			stepExe.sh(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExe.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String One.batchScript(String command) {
		try {
			stepExe.bat(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExe.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}

	@Override
	String One.powerShellScript(String command) {
		try {
			stepExe.powershell(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExe.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}
}