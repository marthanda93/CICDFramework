package org.commons

import org.generic.IJinjaRegistry
import org.generic.IMissingObject

import org.stepRegistry.ContextRegistry

class JinjaExecutor implements IJinjaRegistry, Serializable {
	private _steps

	JinjaExecutor(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean teamplateProcess(Map jparam) {
		String path = "teamplate${_steps.env.BUILD_NUMBER}"

		_steps.dir ("${path}") {
			try {
				_steps.writeFile file: "${jparam.teamplate.fnameFromPath()}", text: "${_steps.libraryResource jparam.teamplate}"
				_steps.writeFile file: "${jparam.param.fnameFromPath()}", text: "${_steps.libraryResource jparam.param}"

				ContextRegistry.getContext().getShellExecutor().bashShell("j2 -f json ${jparam.teamplate.fnameFromPath()} ${jparam.param.fnameFromPath()} -o ${jparam.output}")
			} catch(e) {
				_steps.error "ERROR:teamplateProcess: Failed with \n${e.getMessage()}"
			}
		}

		if(teamplateHealthCheck("${path}/${jparam.output}")) {
			return true
		} else {
			return false
		}
	}

	@Override
	Boolean teamplateHealthCheck(String output) {
		try {
			Object size = ContextRegistry.getContext().getShellExecutor().bashShell("ls -Ss1pq --block-size=1 ${output}")
		} catch(e) {
			_steps.error "ERROR:teamplateHealthCheck: Failed with \n${e.getMessage()}"
		}

		if(size.split(' ')[0].toInteger() > 0) {
			return true
		} else {
			return false
		}
	}

    // @Override
    // String propertyMissing(String name) {
    //     _steps.error "PROPERTYMISSING JinjaExecutor: Caught missing property: $name"
    // }

    // @Override
    // String methodMissing(String name, Object args) {
    //     _steps.error "METHODMISSING JinjaExecutor: Caught missing method: $name"
    // }
}
