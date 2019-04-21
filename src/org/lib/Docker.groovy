package org.lib

class Docker implements Serializable {

	Docker(WorkflowScript script, Map config = [:]) {
		this.script = script
		this.config = config
	}

	void one() {
		this.script.sh "echo 'Anand Here'"
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