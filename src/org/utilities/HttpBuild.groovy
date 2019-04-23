package org.utilities

import org.generic.IHttpRegistry
import org.stepRegistry.ContextRegistry

class HttpBuild implements Serializable {
    private String _solutionPath

    HttpBuild(String solutionPath) {
        _solutionPath = solutionPath
    }

    void build() {
        IHttpRegistry steps = ContextRegistry.getContext().getHttpExecutor()

        steps.sh("echo \"building ${this._solutionPath}...\"")
    }
}
