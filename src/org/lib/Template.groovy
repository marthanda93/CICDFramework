package org.lib

Class Template implements Serializable {
	def config
	def script

	Template(script, config) {
		this.config = config
		this.script = script
	}
}