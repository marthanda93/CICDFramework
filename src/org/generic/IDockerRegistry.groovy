package org.generic

interface IDockerRegistry {
	Object plainBuild(String path, String image, String registryID = '')
	Boolean dockerHubPush(Object dObject, String credentialID = '', Boolean latest = false)
}
