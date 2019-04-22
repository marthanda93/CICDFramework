package org.generic

trait scriptRegistry {
	public stepExecutor
	Map config
}

trait shellRegistry extends scriptRegistry {
    String command
}
