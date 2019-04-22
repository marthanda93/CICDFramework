package org.generic

interface scriptRegistry {
	private final stepExecutor
	Map config
}

interface shellRegistry implements scriptRegistry {
	String command
}