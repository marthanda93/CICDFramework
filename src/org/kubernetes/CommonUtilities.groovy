package org.kubernetes

@Grab('org.yaml:snakeyaml:1.17')
import org.yaml.snakeyaml.Yaml
import java.io.File

class CommonUtilities {
    static boolean opsSyncFileBetweenMasterSlavenGenerate(Object _steps, String k8Object, Map k8Param) {
		String opsSlaveParameterPath = "${k8Param.opsRepoPath}/${_steps.globalPipelineSetting.standardization.templateParameter.MStringTemplateEngine(k8Param)}/${k8Object}"
		String opsMasterParameterPath = "${_steps.env.JENKINS_HOME}/workspace/${_steps.env.JOB_NAME}@libs/${_steps.env.getEnvironment().findAll { it.key =~ /^library.(.+).version$/ }.keySet()[0].split('\\.')[1]}/resources/org/kubernetes"

		if(_steps.fileExists(opsSlaveParameterPath)) {
			Object path = new File( "${opsMasterParameterPath}/parameter" )

			if(path.exists()) {
				File file = new File("${opsMasterParameterPath}/parameter/${k8Object}")
				file.write "${_steps.readFile(opsSlaveParameterPath)}\n"
			} else {
				_steps.println "__path does not exists"
			}
		}

		_steps.println('----------------------------//1')
		org.generic.CommonUtilities.executeOnMaster("""
			/usr/bin/j2 -f yaml template/${k8Object.split('\\.')[0].toLowerCase()}.j2 parameter/${k8Object} -o output/${k8Object}
		""", opsMasterParameterPath)
		_steps.println('----------------------------//2')

		try {
			String data = new File("${opsMasterParameterPath}/output/${k8Object}").text

			if(data.size() > 25) {
				_steps.writeYaml(file:k8Object, data: data.trim())
			} else {
				_steps.error "${k8Object.split('\\.')[0].toUpperCase()}: content size is very less"
			}
		} catch(e) {
			_steps.error "${k8Object.split('\\.')[0].toUpperCase()}: ${e.getMessage()}"
		}

		return true
    }

    static boolean parameterProcessnGenerate(Object _steps, String k8Object, Map k8Param) {
    	// alternante of opsSyncFileBetweenMasterSlavenGenerate
    	// will create parameterfile from parameter
    	_steps.println "__PASS__"
    }

    static boolean yamltoJson(Object _steps, Object data) {
		_steps.println('----------------------------//3')
		return _steps.readJSON(text:groovy.json.JsonOutput.toJson(new Yaml().load(data)))
    }
}
