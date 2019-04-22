package org.generic

interface shellRegistry {
    String bashShell(String command)
    String propertyMissing(String name)
    static String $static_methodMissing(String name, Object args)
}
