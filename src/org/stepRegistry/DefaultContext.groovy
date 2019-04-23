package org.stepRegistry

import org.generic.IHttpExecutor
import org.generic.IShellRegistry

import org.generic.IContext

import org.commons.StepExecutor
import org.commons.ShellExecutor

class DefaultContext implements IContext, Serializable {
    private _steps

    DefaultContext(steps) {
        this._steps = steps
    }

    @Override
    IHttpExecutor getSExecutor() {
        return new HttpExecutor(this._steps)
    }

    @Override
    IShellRegistry getStepExecutor() {
        return new ShellExecutor(this._steps)
    }

}
