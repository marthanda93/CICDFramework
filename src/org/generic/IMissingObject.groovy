package org.generic

interface IMissingObject {
	String propertyMissing(String name)
	String methodMissing(String name, Object args)
}