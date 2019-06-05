package org.commons

import org.generic.IJinjaRegistry
import org.generic.IMissingObject

import org.stepRegistry.ContextRegistry

class JinjaExecutor implements IJinjaRegistry, IMissingObject, Serializable {
	private _steps

	JinjaExecutor(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean teamplateProcess(Map jparam) {
		/*
			jparam = [
				teamplate
				param
				output
			]
		**/
		String path = "teamplate${_steps.env.BUILD_NUMBER}"

		_steps.dir ("${path}") {
			try {
				_steps.writeFile file: "${jparam.teamplate.MfnameFromPath()}", text: "${_steps.libraryResource jparam.teamplate}"
				_steps.writeFile file: "${jparam.param.MfnameFromPath()}", text: "${_steps.libraryResource jparam.param}"

				ContextRegistry.getContext().getShellExecutor().bashShell("j2 -f ${_steps.globalPipelineSetting.jinjaFormat} ${jparam.teamplate.MfnameFromPath()} ${jparam.param.MfnameFromPath()} -o ${jparam.output}")
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
			int size = ContextRegistry.getContext().getShellExecutor().bashShellOutput("ls -Ss1pq --block-size=1 ${output}").split(' ')[0].toInteger() as Integer

			if(size > 0) {
				return true
			} else {
				return false
			}
		} catch(e) {
			_steps.error "ERROR:teamplateHealthCheck: Failed with \n${e.getMessage()}"
		}
	}

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING JinjaExecutor: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.error "METHODMISSING JinjaExecutor: Caught missing method: $name"
    }
}
