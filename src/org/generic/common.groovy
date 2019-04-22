package org.generic

trait scriptRegistry {
	private final stepExecutor
	Map config
}

trait shellRegistry implements scriptRegistry {
	String command
}