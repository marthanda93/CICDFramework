import org.utilities.MsBuild
import org.stepRegistry.ContextRegistry

def call(String solutionPath) {
    ContextRegistry.registerDefaultContext(this)

    def msbuilder = new MsBuild(solutionPath)
    msbuilder.build()
}
