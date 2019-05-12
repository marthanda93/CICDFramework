package org.kubernetes

import org.generic.IPersistentVolumeRegistry
import org.generic.IMissingObject

import org.stepRegistry.ContextRegistry

class NameSpace implements INameSpaceRegistry, Serializable {
	private _steps

	NameSpace(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean create()

	@Override
	Boolean templateProcess()

	@Override
	Boolean healthCheck()








	String templateProcess() {
		ContextRegistry.getContext().getGithubExecutor().cloneExecutor(Map appParam, String cloneType = 'parent')
		ContextRegistry.getContext().getJinjaExecutor().teamplateProcess()
	}

}
