import org.stepRegistry.ContextRegistry

def call(Map pipelineParams) {
    ContextRegistry.registerDefaultContext(this)
    
    ContextRegistry.getContext().getGithubExecutor().cloneExecutor(pipelineParams)
    ContextRegistry.getContext().getMavenExecutor().extendedBuild()
}
