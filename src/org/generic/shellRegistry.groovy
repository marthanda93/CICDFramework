package org.generic

interface scriptRegistry {
	public def stepExecutor
	Map config
}

interface shellRegistry extends scriptRegistry {
	String command
}