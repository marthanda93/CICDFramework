import org.stepRegistry.ContextRegistry

def call(String action, String className, Object parameter = false) {
	ContextRegistry.registerDefaultContext(this)

	switch (className.toLowerCase()) {
		case globalPipelineSetting.synonyms.ns:
			try {
				ContextRegistry.getContext().getK8NameSpaceExecutor()."create"()
			} catch(e) {
				error "ERROR: Action: ${action} missing!"
			}
			break
		default:
			error "ERROR:Kubectl: ${className} Undefined!"
			break
	}
}