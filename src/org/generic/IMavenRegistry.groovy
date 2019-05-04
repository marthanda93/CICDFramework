package org.generic

interface IMavenRegistry {
	Boolean environmentSetup()
	Boolean tuneMavenBuild()
	Boolean dependencySteup()
	Boolean pruneMavenArtifact()
	// profile = settingProfile && repoType = mono/micro
	Boolean extendedBuild(String profile, Boolean repoType, String codebasePath)
}
