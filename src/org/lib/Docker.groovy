package org.lib

class DockerSupport implements Serializable {
	private final script
	Map config

	DockerSupport(script, config = [:]) {
		this.script = script
		this.config = config
	}

	void one() {
		script.sh "echo 'Anand Here'"
		script.println this.script.getClass()
	}

	void build() {
		this.script.stage('Building Image') {
			this.dbuild = this.script.docker.build('maven:sprintboot', '.')
		}
	}

	void dbuild() {
		this.script.stage('Pushing Image') {
			def document = this.script.libraryResource 'org/epam/docker/maven';
			this.script.writeFile file: 'Dockerfile', text: document
			def app = this.script.docker.build("dockerHubID/springboot:latest")
			return app
		}
	}

	void dpush(def app) {
		this.script.stage('Pushing Image') {
			this.script.docker.withRegistry('https://registry.hub.docker.com', 'dlogin') {
				app.push()
			}
		}
	}
}