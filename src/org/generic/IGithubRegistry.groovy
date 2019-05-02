package org.generic

interface IGithubRegistry {
	Boolean cloneExecutor(Boolean dependencyRepo)	//false, true, patch, diff
	String cloneWithDirectory(Map appParam)
	Boolean plainClone(Map appParam)
	Boolean extendedClone(Map appParam)
	Boolean pullRequestClone(Map appParam)
}
