package org.generic

interface IShellRegistry {
	protected interface One {
	    String bashShell(String command)
	    String batchScript(String command)
	    String powerShellScript(String command)
	}

	protected interface Two {
		Map app()
	}

    // String propertyMissing(String name)
    // String methodMissing(String name, Object args)
}
