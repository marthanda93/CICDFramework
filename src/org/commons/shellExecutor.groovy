package org.commons

import org.generic.common

class shellExecutor implements Serializable, common.shellRegistry {
	shellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	def bashShell(command) {
		stepExecutor.sh(script: "${command}", returnStdout: true).trim()
	}
}