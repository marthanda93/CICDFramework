package org.generic

interface scriptRegistry {
	public static final stepExecutor
	Map config
}

interface shellRegistry extends scriptRegistry {
	String command
}