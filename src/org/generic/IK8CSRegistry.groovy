package org.generic

interface IK8CSRegistry {
	Boolean create(Object k8Param)
	Boolean collectConfig()
	Boolean healthCheck()
}