package org.kubernetes

import org.generic.IK8NameSpaceRegistry
import org.generic.IMissingObject
import org.generic.CommonUtilities
import org.stepRegistry.ContextRegistry

class NameSpace implements IK8NameSpaceRegistry, IMissingObject, Serializable {
	private _steps

	NameSpace(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean create(Map k8Param) {
		// _steps.println _steps.globalPipelineSetting.standardization.templateParameter.MStringTemplateEngine(k8Param)
		// _steps.println _steps.globalPipelineSetting.standardization.namespace.MStringTemplateEngine(k8Param)


		Object output = CommonUtilities.executeOnMaster("""
			/usr/bin/j2 -f yaml objectTemplate/namespace.j2 objectTemplate/namespace.yaml
		""","${_steps.env.JENKINS_HOME}/workspace/${_steps.env.JOB_NAME}@libs/${_steps.env.getEnvironment().findAll { it.key =~ /^library.(.+).version$/ }.keySet()[0].split('\\.')[1]}/resources/org/kubernetes")

		if(CommonUtilities.stringValidation(output) && output.startsWith("ERROR: ")) {
			_steps.error output.split('ERROR: ')[-1]
		} else {
			_steps.println output
		}

		return true;
	}

	@Override
	Boolean templateProcess() {
		_steps.println "__PASS__"
		
		// -> run jinja command
		// 	-> check j2 command is there or not if not then install
		// 	-> run j2
		// -> shift from master to slave

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
