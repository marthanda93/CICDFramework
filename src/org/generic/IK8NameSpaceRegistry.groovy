package org.generic

interface IK8NameSpaceRegistry {
	Boolean create(Map k8Param)
	Boolean templateProcess()
	Boolean healthCheck()
}