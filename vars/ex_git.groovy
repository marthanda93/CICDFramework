import org.utilities.GitBuild
import org.stepRegistry.ContextRegistry

def call(Map pipelineParams) {
    ContextRegistry.registerDefaultContext(this)

    def gitbuilder = new GitBuild(pipelineParams)
    gitbuilder.build()
}
