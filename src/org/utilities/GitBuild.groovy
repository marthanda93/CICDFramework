package org.utilities

// import org.generic.IShellRegistry
import org.stepRegistry.ContextRegistry

class GitBuild implements Serializable {
    private Map _solutionPath

    GitBuild(Map solutionPath) {
        _solutionPath = solutionPath
    }

    void build() {
        // IShellRegistry steps = ContextRegistry.getContext().getShellExecutor()
        // steps.bashShell("echo \"building ${this._solutionPath}...\"")
        ContextRegistry.getContext().getGithubExecutor().plainClone(_solutionPath)
    }
}
