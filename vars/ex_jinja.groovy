import org.stepRegistry.ContextRegistry

def call() {
    ContextRegistry.registerDefaultContext(this)
    
    ContextRegistry.getContext().getJinjaExecutor().build()
}
