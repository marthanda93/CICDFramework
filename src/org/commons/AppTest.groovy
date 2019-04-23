package org.commons

import org.generic.*

class AppTest implements Serializable, IShellRegistry.IAnand {
	private final stepExe
	Map config

	AppTest(stepExe, config = [:]) {
		this.stepExe = stepExe
		this.config = config
	}

	@Override
	Map app() {
		stepExe.println("--------------------------//Hello")
	}
}
