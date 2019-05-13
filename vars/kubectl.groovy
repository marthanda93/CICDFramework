import org.stepRegistry.ContextRegistry

def call(String action, String className, Object k8Param = false) {
	ContextRegistry.registerDefaultContext(this)

	switch (className.toLowerCase()) {
		case globalPipelineSetting.synonyms.ns:
			try {
				ContextRegistry.getContext().getK8NameSpaceExecutor()."${action}"(k8Param)
			} catch(e) {
				error "Action: ${action} missing!"
			}
			break
		default:
			error "ERROR:Kubectl: ${className} Undefined!"
			break
	}
}