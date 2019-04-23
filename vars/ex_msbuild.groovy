import org.stepRegistry.ContextRegistry

def call(String solutionPath) {
    ContextRegistry.registerDefaultContext(this)

    def msbuilder = new ShellExecutor()
    msbuilder.bashShell(solutionPath)
}
