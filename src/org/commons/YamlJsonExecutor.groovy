package org.commons

import org.generic.IYamlJsonRegistry
import org.generic.IMissingObject

class YamlJsonExecutor implements IYamlJsonRegistry, IMissingObject, Serializable {
	private _steps
	Map config

	YamlJsonProcess(_steps, config = [:]) {
		this._steps = _steps
		this.config = config
	}
}
