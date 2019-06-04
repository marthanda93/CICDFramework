package org.generic

interface IK8CSRegistry {
	Boolean create(Map k8Param)
	Boolean collectConfig()
	Boolean healthCheck()
}