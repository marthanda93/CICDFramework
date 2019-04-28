package org.commons

import org.generic.IGithubRegistry
import org.generic.IMissingObject

class GithubExecutor implements IGithubRegistry, IMissingObject, Serializable {
	private _steps
	Map config

	YamlJsonProcess(_steps, config = [:]) {
		this._steps = _steps
		this.config = config
	}
}
