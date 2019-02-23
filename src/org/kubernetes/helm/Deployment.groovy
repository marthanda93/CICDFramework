package src.org.kubernetes.helm

Class Deployment implements Serializable {
	def config
	def script

	Deployment(script, config) {
		this.config = config
		this.script = script
	}
}