import org.stepRegistry.ContextRegistry

def call(String action, String className, Object parameter = false) {
	ContextRegistry.registerDefaultContext(this)

	switch (className.toLowerCase()) {
		case globalPipelineSetting.synonyms.ns:
			println "_____NameSpace______"
			break
		default:
			error "ERROR:Kubectl: ${className} Undefined!"
			break
	}
}