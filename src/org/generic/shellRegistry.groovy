package org.generic

interface scriptRegistry {
	public static stepExecutor
	Map config
}

interface shellRegistry extends scriptRegistry {
	String command
}