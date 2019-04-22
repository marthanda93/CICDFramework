package org.commons

import org.generic.shellRegistry

class shellExecutor implements Serializable, shellRegistry {
	shellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	def bashShell(String command) {
		stepExecutor.sh(script: "${command}", returnStdout: true).trim()
	}
}