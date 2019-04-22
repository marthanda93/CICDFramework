package org.commons

import org.generic.commons

class shellExecutor implements Serializable, commons.shellRegistry {
	shellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	def bashShell(command) {
		stepExecutor.sh(script: "${command}", returnStdout: true).trim()
	}
}