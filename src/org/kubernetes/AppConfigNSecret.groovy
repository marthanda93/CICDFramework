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
		List files = []

		if(org.generic.CommonUtilities.mapValidation(k8Param)) {
			if('configPath' in k8Param.keySet().collect()) {


List itemx = []
List pathx = k8Param.configPath.split("(/\\{|\\}/)") as String[];

pathx.eachWithIndex{ key, index ->
    itemx = key.split(",|\\s")
    if(itemx.size() > 1) {
        itemx.removeAll(Arrays.asList(null,""));
        pathx[index] = itemx
    }
}



List list = []
List path = []
Integer i = 0
Boolean sublist = true

pathx.eachWithIndex { key, index ->
    if(key instanceof java.util.List) {
        i = 0
        key.each{
            if(sublist == true) {
                path << (list + [it])
            } else {
                for(List item in path) {
                    path[i] << it
                    i = i + 1
                    break;
                }
            }
        }
        sublist = false
    } else {
    	_steps.println "------//1/${key}/${key.getClass()}/-----path//${path}/--------sublist//${sublist}"
        if(sublist == false) {
            path.each {
                it << key
            }
        } else {
            list << key
        }
    }
}

_steps.println "--------------//10/${path}"
_steps.println k8Param.configPath.MsubSplit().MsubListjoin()

















				k8Param.configPath.MsubSplit().MsubListjoin().each {
					_steps.println "---------//4"
					_steps.println it
					_steps.println "---------//5"
					files << _steps.findFiles(glob: "${k8Param.scmPath}/petclinic/dev/one/*.properties") as List
					_steps.println "---------//6"
				}
_steps.println "---------//7"
				_steps.println files
			} else if ('secretPath' in k8Param.keySet().collect()) {
				files = _steps.findFiles(glob: "${k8Param.scmPath}/${k8Param.secretPath}")
				_steps.println files
			} else {
				_steps.error "Missing Key \n Hint: configPath/secretPath"
			}
		} else {
			_steps.error "Missing Parameter: ${k8Param}"
		}
	}

	@Override
	Boolean collectConfig() {
		_steps.println "__PASS__"
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