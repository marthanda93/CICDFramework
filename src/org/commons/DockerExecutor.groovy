package org.commons

import org.generic.IDockerRegistry
import org.generic.IMissingObject
import org.generic.CommonUtilities

class DockerExecutor implements IDockerRegistry, IMissingObject, Serializable {
	private _steps
	Map config

	DockerExecutor(_steps, config = [:]) {
		this._steps = _steps
		this.config = config
	}

	Object plainBuild(String path, String image, String registryID = '') {
		if(CommonUtilities.stringValidation(registryID)) {
			image = "${registryID}/${image}"
		}

		if(CommonUtilities.fileSlaveDownload(_steps, path, 'Dockerfile')) {
			try {
				Object dObject = _steps.docker.build("${image}", '.')

				return dObject
			} catch(e) {
				_steps.error "ERROR plainBuild: Failed with \n${e.getMessage()}"
			}
		} else {
			_steps.error "ERROR: PlainBuild: Build failed"
		}
	}

	Boolean dockerHubPush(Object dObject, String credentialID = '', Boolean latest = false) {
		try {
			if(CommonUtilities.stringValidation(credentialID)) {
				_steps.docker.withRegistry("${_steps.globalPipelineSetting.dockerRegistry}", "${credentialID}") {
					dObject.push()

					if(latest) {
						dObject.push("latest")
					}
				}
			} else {
				_steps.docker.withRegistry("${_steps.globalPipelineSetting.dockerRegistry}") {
					dObject.push()

					if(latest) {
						dObject.push("latest")
					}
				}
			}

			return true
		} catch(e) {
			_steps.error "ERROR dockerHubPush: Failed with \n${e.getMessage()}"
		}
	}
}
