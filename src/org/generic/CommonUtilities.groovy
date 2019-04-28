package org.generic

class CommonUtilities implements IMissingObject, Serializable {

	static boolean stringValidation(String word) {
		if (word != null && word != "" && word instanceof String && word.size() > 0) {
			return true
		} else {
			return false
		}
	}

    static boolean listValidation(List collection) {
        if (collection instanceof java.util.List && collection.size > 0) {
            return true
        } else {
            return false
        }
    }

    static boolean mapValidation(Map collection) {
        if (collection instanceof java.util.Map && collection.size() > 0) {
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
        _steps.error "METHODMISSING CommonUtilities: Caught missing method: $name"
    }
}