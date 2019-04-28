package org.commons

import org.generic.IDockerRegistry
import org.generic.IMissingObject

class DockerExecutor implements IDockerRegistry, IMissingObject, Serializable {
	private _steps
	Map config

	YamlJsonProcess(_steps, config = [:]) {
		this._steps = _steps
		this.config = config
	}
}
