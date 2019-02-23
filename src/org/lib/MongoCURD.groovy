package org.lib

Class MongoCURD implements Serializable {
	def config
	def script

	MongoCURD(script, config) {
		this.config = config
		this.script = script
	}
}