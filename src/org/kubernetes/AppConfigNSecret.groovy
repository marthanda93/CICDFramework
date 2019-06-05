package org.kubernetes

import org.generic.IK8CSRegistry
import org.generic.IMissingObject
import org.stepRegistry.ContextRegistry

import java.util.regex.Matcher
import java.util.regex.Pattern

class AppConfigNSecret implements IK8CSRegistry, Serializable {
	private _steps

	AppConfigNSecret(_steps) {
		this._steps = _steps
	}

	@Override
	Boolean create(Object k8Param) {
		ArrayList files = []

		if(org.generic.CommonUtilities.mapValidation(k8Param)) {
			if('configPath' in k8Param.keySet().collect()) {
                for(List item in k8Param.configPath.MsubSplit().MsubListjoin()) {
					files = files + (_steps.findFiles(glob: k8Param.scmPath+'/'+item.join('/')) as List)
                }

                collectConfig(files)
			} else if ('secretPath' in k8Param.keySet().collect()) {
                for(List item in k8Param.secretPath.MsubSplit().MsubListjoin()) {
					files = files + (_steps.findFiles(glob: k8Param.scmPath+'/'+item.join('/')) as List)
                }
			} else {
				_steps.error "Missing Key \n Hint: configPath/secretPath"
			}
		} else {
			_steps.error "Missing Parameter: ${k8Param}"
		}
	}

	@Override
	Boolean collectConfig(List files) {
		_steps.println files

		List concatenate = []

		files.each { file ->
			concatenate.addAll(_steps.readFile(file))
			concatenate.add('')
		}

		_steps.println concatenate
	}

	@Override
	Boolean healthCheck() {
		_steps.println "__PASS__"
	}

    // @Override
    // String propertyMissing(String name) {
    //     _steps.error "PROPERTYMISSING AppConfigNSecret: Caught missing property: $name"
    // }

    // @Override
    // String methodMissing(String name, Object args) {
    //     _steps.error "METHODMISSING AppConfigNSecret: Caught missing method: $name"
    // }
}


// Var/ update: false/true/delete -> if config found want to update or delete
// user param: type of config: environment, volume


// 1> clone config
// 2> collect exiting configmap name list
// 3> labelling 
// 4> creating
// 5> verifying
// 6> prune