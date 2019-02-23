package org.lib

Class Http implements Serializable {
	def config
	def script

	Http(script, config) {
		this.config = config
		this.script = script
	}
}