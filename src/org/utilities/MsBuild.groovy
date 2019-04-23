package org.utilities

import org.generic.IShellRegistry
import org.stepRegistry.ContextRegistry

class MsBuild implements Serializable {
    private String _solutionPath
    IShellRegistry steps

    MsBuild(String solutionPath) {
    	this.steps = ContextRegistry.getContext().getShellExecutor()
        _solutionPath = solutionPath
    }

    void build() {
        steps.bashShell("echo \"building ${this._solutionPath}...\"")
    }
}
