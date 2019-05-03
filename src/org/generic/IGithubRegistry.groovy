package org.generic

interface IGithubRegistry {
	Object cloneExecutor(Map appParam, String cloneType)	//false, true, patch, diff
	String cloneWithDirectory(Map appParam)
	Boolean plainClone(Map appParam)
	Boolean extendedClone(Map appParam)
	Boolean lfsClone(Map appParam)
}
