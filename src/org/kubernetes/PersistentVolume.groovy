package org.kubernetes

import org.generic.IPersistentVolumeRegistry
import org.generic.IMissingObject

import org.stepRegistry.ContextRegistry

class PersistentVolume implements IPersistentVolumeRegistry, IMissingObject, Serializable {
	private _steps

	PersistentVolume(_steps) {
		this._steps = _steps
	}

	@Override
	String templateProcess() {
		ContextRegistry.getContext().getGithubExecutor().cloneExecutor(Map appParam, String cloneType = 'parent')
		ContextRegistry.getContext().getJinjaExecutor().teamplateProcess()
	}

}
