package org.commons

import org.generic.IShellRegistry
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
		"Caught missing property: $name"
	}

	@Override
	String methodMissing(String name, Object args) {
		_steps.println """
			Possible solutions: 
			String bashShell(String command)
			String batchScript(String command)
			String powerShellScript(String command)
		"""

		_steps.error "Missing method name is $name"
	}
}