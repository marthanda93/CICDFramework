package org.commons

import org.generic.IShellRegistry
import org.generic.IMissingObject

class CommonValidation implements Serializable {

	static boolean stringValidation(String word) {
		if (word != null && word != "" && word instanceof String && word.size() > 0) {
			return true
		} else {
			return false
		}
	}
}