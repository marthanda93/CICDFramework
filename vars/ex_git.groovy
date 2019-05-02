import org.utilities.GitBuild
import org.stepRegistry.ContextRegistry

def call(Map pipelineParams) {
    ContextRegistry.registerDefaultContext(this)

    def httpbuilder = new GitBuild(pipelineParams)
    httpbuilder.build()
}
