import org.utilities.HttpBuild
import org.stepRegistry.ContextRegistry

def call(String solutionPath) {
    ContextRegistry.registerDefaultContext(this)

    def httpbuilder = new HttpBuild(solutionPath)
    httpbuilder.build()
}
