import org.stepRegistry.ContextRegistry

def call(Map app) {
    ContextRegistry.registerDefaultContext(this)
    
    ContextRegistry.getContext().getJinjaExecutor().build()
}
