package org.kubernetes

import java.io.File

class CommonUtilities {
    static boolean syncFileBetweenMasterSlavenGenerate(Object _steps, Map k8Param) {
		String opsSlaveParameterPath = "${k8Param.opsRepoPath}/${_steps.globalPipelineSetting.standardization.templateParameter.MStringTemplateEngine(k8Param)}/namespace.yaml"
		String opsMasterParameterPath = "${_steps.env.JENKINS_HOME}/workspace/${_steps.env.JOB_NAME}@libs/${_steps.env.getEnvironment().findAll { it.key =~ /^library.(.+).version$/ }.keySet()[0].split('\\.')[1]}/resources/org/kubernetes"

		if(_steps.fileExists(opsSlaveParameterPath)) {
			Object path = new File( "${opsMasterParameterPath}/parameter" )

			if(path.exists()) {
				File file = new File("${opsMasterParameterPath}/parameter/namespace.yaml")
				file.write "${_steps.readFile(opsSlaveParameterPath)}\n"
			} else {
				_steps.println "__path does not exists"
			}
		}

		org.generic.CommonUtilities.executeOnMaster("""
			/usr/bin/j2 -f yaml template/namespace.j2 parameter/namespace.yaml -o output/namespace.yaml
		""", opsMasterParameterPath)

		String data = new File("${opsMasterParameterPath}/output/namespace.yaml").text
		_steps.println data
		_steps.println data.size()
		_steps.println data.getClass()

		_steps.writeYaml(file:'anand.yaml', data: data.trim())
		_steps.println _steps.readYaml(file:'anand.yaml')
    }
}
