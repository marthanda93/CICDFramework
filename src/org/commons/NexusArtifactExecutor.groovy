package org.commons

import org.generic.INexusArtifactRegistry
import org.generic.IMissingObject

class NexusArtifactExecutor implements INexusArtifactRegistry, IMissingObject, Serializable {
	private _steps

	NexusArtifactExecutor(_steps) {
		this._steps = _steps
	}
}


release
artifact/config upload
artifact/config download -> with path flag
purge old artifact/config