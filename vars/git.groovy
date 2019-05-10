import org.stepRegistry.ContextRegistry

def call(Map pipelineParams, String cloneType = 'plain') {
	println('-------//1')
    ContextRegistry.registerDefaultContext(this)
    println('-------//2')
    ContextRegistry.getContext().getGithubExecutor().cloneExecutor(pipelineParams, cloneType)
    ContextRegistry.getContext().getMavenExecutor().extendedBuild()
}
