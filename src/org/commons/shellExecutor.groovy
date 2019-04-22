package org.commons

import org.generic.IShellRegistry

class shellExecutor implements Serializable, IShellRegistry {
	private final stepExecutor
	Map config

	shellExecutor(stepExecutor, config = [:]) {
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

	@Override
	String propertyMissing(String name) {
		"Caught missing property: $name"
	}

	@Override
	String methodMissing(String name, Object args) {
		stepExecutor.println """
			Possible solutions: 
			String bashShell(String command)
		"""

		stepExecutor.error "Missing method name is $name"
	}
}