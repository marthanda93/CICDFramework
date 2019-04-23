package org.commons

import org.generic.IAnand

class AppTest implements Serializable, IAnand {
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