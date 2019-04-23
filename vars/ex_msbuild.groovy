import org.commons.ShellExecutor
import org.stepRegistry.ContextRegistry

def call(String solutionPath) {
    ContextRegistry.registerDefaultContext(this)

    def msbuilder = new ShellExecutor()
    this.println "---------------//1"
    msbuilder.bashShell(solutionPath)
    this.println "---------------//10"
}
