package org.commons

import org.generic.shellRegistry

class shellExecutor implements Serializable, shellRegistry {
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
	String propertyMissing(String name) {
		"Caught missing property: $name"
	}

	@Override
	String $static_methodMissing(String name, Object args) {
		stepExecutor.println "wwwwwwwww----------w------"
		return "Missing static method name is $name"
	}
}