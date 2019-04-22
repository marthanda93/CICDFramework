package org.generic

interface scriptRegistry {
	public stepExecutor
	Map config
}

interface shellRegistry extends scriptRegistry {
    String command
}
