import org.stepRegistry.ContextRegistry

def call(String action, String className, Object parameter = false) {
	ContextRegistry.registerDefaultContext(this)

	switch (className.toLowerCase()) {
		case _steps.globalPipelineSetting.synonyms.ns:
			println "_____NameSpace______"
			break
		default:
			_steps.error "ERROR:GitClone: ${cloneType} Undefined Parameter!"
			break
	}
}