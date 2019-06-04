import org.stepRegistry.ContextRegistry

def call(String action, String className, Object k8Param = false) {
	ContextRegistry.registerDefaultContext(this)

	switch (className.toLowerCase()) {
		case globalPipelineSetting.synonyms.ns:
			if(action in ['create']) {
				ContextRegistry.getContext().getK8NameSpaceExecutor()."${action}"(k8Param)
			} else {
				error "Action: ${action} missing!"
			}
			break
		case globalPipelineSetting.synonyms.cm:
			if(action in ['create']) {
				ContextRegistry.getContext().getK8ConfigNSecretExecutor()."${action}"(k8Param)
			} else {
				error "Action: ${action} missing!"
			}
			break
		default:
			error "ERROR:Kubectl: ${className} Undefined!"
			break
	}
}