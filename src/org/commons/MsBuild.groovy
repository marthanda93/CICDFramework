package org.commons

import org.generic.IShellRegistry
import org.stepRegistry.ContextRegistry

class MsBuild implements Serializable {
    private String _solutionPath

    MsBuild(String solutionPath) {
        _solutionPath = solutionPath
    }

    void build() {
        IShellRegistry steps = ContextRegistry.getContext().getStepExecutor()

        steps.bashShell("echo \"building ${this._solutionPath}...\"")
    }
}
