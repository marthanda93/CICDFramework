package org.generic

interface shellRegistry {
    String bashShell(String command)

	String propertyMissing(String name) {
		"Caught missing property: $name"
	}
}
