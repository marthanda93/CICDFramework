package org.lib

Class Logger implements Serializable {
	def config
	def script

	Logger(script, config) {
		this.config = config
		this.script = script
	}
}