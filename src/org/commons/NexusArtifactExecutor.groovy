package org.commons

import org.generic.INexusArtifactRegistry
import org.generic.IMissingObject

class NexusArtifactExecutor implements INexusArtifactRegistry, IMissingObject, Serializable {
	private _steps
	Map config

	NexusArtifactExecutor(_steps, config = [:]) {
		this._steps = _steps
		this.config = config
	}
}
