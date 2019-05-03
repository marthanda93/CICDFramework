package org.commons

import org.generic.IDockerRegistry
import org.generic.IMissingObject
import org.generic.CommonUtilities

class DockerExecutor implements IDockerRegistry, IMissingObject, Serializable {
	private _steps
	Map config

	DockerExecutor(_steps) {
		this._steps = _steps
	}

	Object executor(Map dockerParameter, Boolean dockerPush = false) {

	}

	Object plainBuild(String image, String registryID = '', String path = '') {
		String dockerPath

		dockerPath = "project/${_steps.env.AppName}/${_steps.env.DeploymentStage}"

		if(CommonUtilities.stringValidation(path)) {
			dockerPath = "${dockerPath}/${path}".stripSlash()
		}

		if(CommonUtilities.stringValidation(registryID)) {
			image = "${registryID}/${image}"
		}

        try {
			_step.writeFile file: "Dockerfile", text: _step.libraryResource "${path}"
			return _steps.docker.build("${image}", '.')
        } catch(e) {
            _steps.println "ERROR:CommonUtilities: Failed at fileSlaveDownload \n${e.getMessage()}"
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

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING DockerExecutor: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.error "METHODMISSING DockerExecutor: Caught missing method: $name"
    }
}
