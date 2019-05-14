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
		String opsSlaveParameterPath = "${k8Param.opsRepoPath}/${_steps.globalPipelineSetting.standardization.templateParameter.MStringTemplateEngine(k8Param)}/namespace.yaml"
		String opsMasterParameterPath = "${_steps.env.JENKINS_HOME}/workspace/${_steps.env.JOB_NAME}@libs/${_steps.env.getEnvironment().findAll { it.key =~ /^library.(.+).version$/ }.keySet()[0].split('\\.')[1]}/resources/org/kubernetes"
// -> validation
// -> standardization of path
// -> write yaml from slave to master
// -> generate
// -> pull back data from master to slave

if(_steps.fileExists(opsSlaveParameterPath)) {
	_steps.println _steps.readFile(opsSlaveParameterPath)

	Object path = new File( opsMasterParameterPath )
	if(folder.exists()) {
		_steps.println "__path exists"
	} else {
		_steps.println "__path does not exists"
	}

}

// _steps.println _steps.fileExists "${k8Param.opsRepoPath}/${_steps.globalPipelineSetting.standardization.templateParameter.MStringTemplateEngine(k8Param)}/namespace.yaml")
// _steps.println _steps.readFile "${k8Param.opsRepoPath}/${_steps.globalPipelineSetting.standardization.templateParameter.MStringTemplateEngine(k8Param)}/namespace.yaml")



		CommonUtilities.executeOnMaster("""
			/usr/bin/j2 -f yaml objectTemplate/namespace.j2 objectTemplate/namespace.yaml -o anand.yaml
		""", opsMasterParameterPath)

		return true;
	}

	@Override
	Boolean templateProcess() {
		_steps.println "__PASS__"
		
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
