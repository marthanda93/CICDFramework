import org.stepRegistry.ContextRegistry

def call(String template, String parameter, String output = '') {
	ContextRegistry.registerDefaultContext(this)
    ContextRegistry.getContext().jinTemplate(template, parameter, output).executor()
}