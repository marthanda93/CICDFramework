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
			List response = ContextRegistry.getContext().getHttpExecutor().httpPost(
				customHeaders: [Authorization:"Bearer Gtoken"],
				url: "https://104.197.4.139/api/v1/namespaces",
				CommonUtilities.yamltoJson(_steps, _steps.readYaml(file: k8Object))
			)
		}

		_steps.println response

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
