package org.commons

import org.generic.IMissingObject

class CommonUtilities implements IMissingObject, Serializable {

	static boolean stringValidation(String word) {
		if (word != null && word != "" && word instanceof String && word.size() > 0) {
			return true
		} else {
			return false
		}
	}

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING CommonUtilities: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.println """
        Possible solutions: 
			boolean stringValidation(String word)
        """

        _steps.error "METHODMISSING CommonUtilities: Caught missing method: $name"
    }
}