package org.lib

class DockerSupport implements Serializable {
	private final script
	Map config

	static DockerSupport(script, config = [:]) {
		this.script = script
		this.config = config
	}

	static void one() {
		script.sh "echo 'Anand Here'"
		script.println this.script.getClass()
	}
}