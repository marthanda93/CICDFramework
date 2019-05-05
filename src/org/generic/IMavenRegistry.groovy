package org.generic

interface IMavenRegistry {
	Boolean environmentSetup()
	Boolean tuneMavenBuild()
	Boolean dependencySteup()
	Boolean pruneMavenArtifact()
	Object mvnBuild(String profile, List codebasePaths)
	// profile = settingProfile && repoType = mono/micro
	Boolean extendedBuild(String profile, String repoType, List codebasePaths)
	Void buildStats()
}
