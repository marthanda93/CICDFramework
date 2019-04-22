package org.generic

interface scriptRegistry {
	private final stepExecutor
	Map config
}

interface shellRegistry extends scriptRegistry {
	String command
}