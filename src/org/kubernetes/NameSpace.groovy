package org.kubernetes

import java.io.File

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

		if(_steps.fileExists(opsSlaveParameterPath)) {
			Object path = new File( "${opsMasterParameterPath}/parameter" )

			if(path.exists()) {
				File file = new File("${opsMasterParameterPath}/parameter/namespace.yaml")
				file.write "${_steps.readFile(opsSlaveParameterPath)}\n"
			} else {
				_steps.println "__path does not exists"
			}
		}

		CommonUtilities.executeOnMaster("""
			/usr/bin/j2 -f yaml template/namespace.j2 parameter/namespace.yaml -o output/namespace.yaml
		""", opsMasterParameterPath)

		_steps.writeYaml(file:'anand.yaml', data: new File("${opsMasterParameterPath}/output/namespace.yaml").text.trim())
		_steps.println _steps.readYaml(file:'anand.yaml')

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
