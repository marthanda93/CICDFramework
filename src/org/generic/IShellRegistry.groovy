package org.generic

interface IShellRegistry {
    String bashShell(String command)
    String batchScript(String command)
    String powerShellScript(String command)

    // String propertyMissing(String name)
    // String methodMissing(String name, Object args)
}

interface IAnand {
	Int app()
}