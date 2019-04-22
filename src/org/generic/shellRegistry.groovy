package org.generic

interface shellRegistry {
    String bashShell(String command)

	def propertyMissing(String name) {
		"Caught missing property: $name"
	}
}
