package org.commons

import org.generic.shellRegistry

class shellExecutor implements Serializable, shellRegistry {
	private final stepExecutor
	
	shellExecutor(stepExecutor, config = [:]) {
		this.stepExecutor = stepExecutor
		this.config = config
	}

	@Override
	def bashShell(String command) {
		stepExecutor.sh(script: "${command}", returnStdout: true).trim()
	}
}