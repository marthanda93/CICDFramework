package org.template

import org.generic.IJinTemplate
import org.generic.IMissingObject
import org.generic.CommonUtilities

import org.stepRegistry.ContextRegistry

class Jin implements IJinTemplate, IMissingObject, Serializable {
	private _steps

	Jin(_steps, String template, String parameter, String output = '') {
		this._steps = _steps
		this.template = template
		this.parameter = parameter
		this.output = output

        if((template =~ /.[a-zA-Z0-9]+/)[-1].matches("(?i).yaml|.yml") && (parameter =~ /.[a-zA-Z0-9]+/)[-1].matches("(?i).yaml|.yml")) {
            this.type = 'yaml'
        } else if((template =~ /.[a-zA-Z0-9]+/)[-1].matches("(?i).json") && (parameter =~ /.[a-zA-Z0-9]+/)[-1].matches("(?i).json")) {
            this.type = 'json'
        } else {
            _steps.error "TYPE ERROR: undefined type \n Template:${template} :: Parameter:${parameter}"
        }
	}

	@Override
	Void fileProcess() {
        if(this.type == 'yaml') {
            this.template = CommonUtilities.yamlProcess(_steps, _steps.readYaml(file: this.template));
            this.parameter = CommonUtilities.yamlProcess(_steps, _steps.readYaml(file: this.parameter));
        } else {
            this.template = CommonUtilities.jsonProcess(_steps, _steps.readYaml(file: this.template));
            this.parameter = CommonUtilities.jsonProcess(_steps, _steps.readYaml(file: this.parameter));
        }
    }

    @Override
    Void executor() {
        fileProcess()

        this.template.each{ k, v ->
            _steps.println(k)
            _steps.println(v)
            _steps.println("\n------------------------------\n")
        }
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
