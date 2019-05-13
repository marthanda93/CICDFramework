package org.kubernetes

import org.generic.IK8NameSpaceRegistry
import org.generic.IMissingObject
import org.stepRegistry.ContextRegistry

class NameSpace implements IK8NameSpaceRegistry, IMissingObject, Serializable {
	private _steps

	NameSpace(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean create() {
		_steps.println "__PASS__"
	}

	@Override
	Boolean templateProcess() {
		_steps.println "__PASS__"


		// -> process path from var
		// 	-> create map
		// -> verify path
		// 	-> check current path
		// 	-> check new path with files
		// -> run jinja command
		// 	-> check j2 command is there or not if not then install
		// 	-> run j2
		// -> shift from master to slave

		// ContextRegistry.getContext().getGithubExecutor().cloneExecutor(Map appParam, String cloneType = 'parent')
		// ContextRegistry.getContext().getJinjaExecutor().teamplateProcess()
	}

	@Override
	Boolean healthCheck() {
		_steps.println "__PASS__"
	}

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING NameSpace: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.error "METHODMISSING NameSpace: Caught missing method: $name"
    }
}
