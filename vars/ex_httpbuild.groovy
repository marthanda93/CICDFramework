import org.utilities.HttpBuild
import org.stepRegistry.ContextRegistry

def call(Map pipelineParams) {
    ContextRegistry.registerDefaultContext(this)

    def httpbuilder = new HttpBuild(pipelineParams)
    httpbuilder.build()
}
