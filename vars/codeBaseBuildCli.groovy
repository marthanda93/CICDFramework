import org.stepRegistry.ContextRegistry

def call(String builTool, Object buildParam = false) {
	ContextRegistry.registerDefaultContext(this)

	switch (builTool.toLowerCase()) {
		case globalSharedLibrary.synonyms.mvn:
			ContextRegistry.getContext().getMavenExecutor().mvnBuild()
			break
		default:
			error "BuilTool: ${className} Undefined!"
			break
	}
}