package org.generic

class MissingObject extends IShellRegistry {

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