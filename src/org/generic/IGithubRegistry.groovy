package org.generic

interface IGithubRegistry {
	Boolean cloneExecutor(Boolean dependencyRepo = false)	//false, true, patch, diff
	String cloneWithDirectory(Map appParam)
	Boolean pullRequestClone(Map appParam)
}
