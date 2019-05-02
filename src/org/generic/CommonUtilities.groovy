package org.generic

class CommonUtilities implements Serializable {
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

    // static boolean fileSlaveDownload(Object _step, String path, String destination) {
    //     try {
    //         _step.writeFile file: "${destination}", text: _step.libraryResource "${path}"

    //         return true
    //     } catch(e) {
    //         _steps.println "ERROR:CommonUtilities: Failed at fileSlaveDownload \n${e.getMessage()}"

    //         return false
    //     }
    // }

    static boolean gitValidation(Map collection) {
    	if(mapValidation(collection) && collection.size() <= 3 && (stringValidation(collection.url) && stringValidation(collection.branch) && stringValidation(collection.credentialsId))) {
    		return true
    	} else {
    		return false
    	}
    }
}