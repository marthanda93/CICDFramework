package org.commons

import org.generic.IShellRegistry
import org.generic.IMissingObject

class PersistentVolume implements IPersistentVolumeRegistry, IMissingObject, Serializable {
	private _steps

	PersistentVolume(_steps) {
		this._steps = _steps
	}

	@Override
	String bashShell(String command) {
		try {
			return _steps.sh(script: "${command}")
		} catch(e) {
			_steps.error "ERROR:bashShell: Failed with \n${e.getMessage()}"
		}
	}
}

