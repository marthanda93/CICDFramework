package org.commons

import org.generic.IHttpRegistry

class HttpExecutor implements IHttpRegistry {
    private _steps

    HttpExecutor(steps) {
        this._steps = steps
    }

    @Override
    int sh(String command) {
        this._steps.sh returnStatus: true, script: "${command}"
    }

    @Override
    void error(String message) {
        this._steps.error(message)
    }
}
