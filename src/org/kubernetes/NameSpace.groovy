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
	Boolean create(Map k8Param) {
		String k8Object = 'namespace.yaml'

		if(CommonUtilities.opsSyncFileBetweenMasterSlavenGenerate(_steps, k8Object, k8Param)) {
			_steps.println _steps.readYaml(file: k8Object)
		}

		return true;
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


// -> covert yaml to json | to process more
// -> start executing through API
// -> monitor for healthCheck

// import hudson.model.*
// readJSON text:data

// import static groovy.json.JsonOutput.*

// data.toLowerCase().replaceAll("[.|,|_| ]", '-')

// import groovy.json.*
// JsonOutput.toJson(data)
// data = readJSON text:data