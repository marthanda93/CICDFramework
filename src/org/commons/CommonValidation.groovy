package org.commons

import org.generic.IMissingObject

class CommonValidation implements IMissingObject, Serializable {

	static boolean stringValidation(String word) {
		if (word != null && word != "" && word instanceof String && word.size() > 0) {
			return true
		} else {
			return false
		}
	}

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING CommonValidation: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.println """
        Possible solutions: 
			boolean stringValidation(String word)
        """

        _steps.error "METHODMISSING CommonValidation: Caught missing method: $name"
    }
}