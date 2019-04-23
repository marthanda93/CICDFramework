package org.commons

import org.generic.IStepExecutor
import org.stepRegistry.ContextRegistry

class MsBuild implements Serializable {
    private String _solutionPath

    MsBuild(String solutionPath) {
        _solutionPath = solutionPath
    }

    void build() {
        IStepExecutor steps = ContextRegistry.getContext().getStepExecutor()

        steps.bashShell("echo \"building ${this._solutionPath}...\"")
    }
}
