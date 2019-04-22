package org.generic

interface shellRegistry {
    String bashShell(String command)
    String propertyMissing(String name)
    String methodMissing(String name, Object args)
}
