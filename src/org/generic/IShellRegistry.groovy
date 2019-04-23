package org.generic

interface IShellRegistry {
	public interface One {
	    String bashShell(String command)
	    String batchScript(String command)
	    String powerShellScript(String command)
	}

	public interface Two {
		Map app()
	}

    // String propertyMissing(String name)
    // String methodMissing(String name, Object args)
}
