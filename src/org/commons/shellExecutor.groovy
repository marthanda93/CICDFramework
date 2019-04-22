package org.commons

import org.generic.shellRegistry

class shellExecutor implements Serializable, shellRegistry {
	private final stepExecutor
	Map config

	shellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	String bashShell(command) {
		try {
			stepExecutor.sh(script: "${command}", returnStdout: true)
		} catch(e) {
			stepExecutor.println "getMessage\n${e.getMessage()}"
			stepExecutor.println "printStackTrace \n${e.printStackTrace()}"
			stepExecutor.error "ERROR:bashShell: Failed with \n${e.message}}"
		}
	}
}