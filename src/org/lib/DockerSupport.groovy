package org.lib

class DockerSupport implements Serializable {
	private final script
	Map config
	def scripta

	DockerSupport(script, config = [:]) {
		this.script = script
		this.config = config
	}

	void one() {
		script.sh "echo 'Anand Here'"
		script.println this.script.getClass()
	}

  static def mvna(script, args) {
    script.sh "${script.tool 'Maven'}/bin/mvn -s ${script.env.HOME}/jenkins.xml -o ${args}"
  }
}