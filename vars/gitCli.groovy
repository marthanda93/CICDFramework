import org.stepRegistry.ContextRegistry

def call(Map pipelineParams, String cloneType = 'plain') {
    ContextRegistry.registerDefaultContext(this)
    ContextRegistry.getContext().getGithubExecutor().cloneExecutor(pipelineParams, cloneType)
}
