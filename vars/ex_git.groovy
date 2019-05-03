import org.stepRegistry.ContextRegistry

def call(Map pipelineParams) {
    ContextRegistry.registerDefaultContext(this)
    ContextRegistry.getContext().getGithubExecutor().plainClone(pipelineParams)
}
