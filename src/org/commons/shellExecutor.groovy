package org.commons

import org.generic.shellRegistry

class shellExecutor implements Serializable, org.generic.shellRegistry {
	shellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	def bashShell(command) {
		stepExecutor.sh(script: "${command}", returnStdout: true).trim()
	}
}