package org.stepRegistry

import org.generic.IHttpRegistry
import org.generic.IShellRegistry
import org.generic.IGithubRegistry
import org.generic.IDockerRegistry


import org.generic.IContext

import org.commons.*

class DefaultContext implements IContext, Serializable {
    private _steps

    DefaultContext(steps) {
        this._steps = steps
    }

    @Override
    IHttpRegistry getHttpExecutor() {
        return new HttpExecutor(this._steps)
    }

    @Override
    IShellRegistry getShellExecutor() {
        return new ShellExecutor(this._steps)
    }

    @Override
    IGithubRegistry getGithubExecutor() {
        return new GithubExecutor(this._steps)
    }

    @Override
    IDockerRegistry getDockerExecutor() {
        return new DockerExecutor(this._steps)
    }
}
