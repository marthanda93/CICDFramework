package org.generic

interface IK8CSRegistry {
	def create(Map k8Param)
	Boolean collectConfig()
	Boolean healthCheck()
}