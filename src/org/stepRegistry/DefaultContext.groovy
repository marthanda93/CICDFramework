package org.stepRegistry

import org.generic.IHttpRegistry
import org.generic.IShellRegistry
import org.generic.IGithubRegistry
import org.generic.IDockerRegistry
import org.generic.IMavenRegistry
import org.generic.IJinjaRegistry

import org.generic.IContext

class DefaultContext implements IContext, Serializable {
    private _steps

    DefaultContext(steps) {
        this._steps = steps
    }

    @Override
    IHttpRegistry getHttpExecutor() {
        return new org.commons.HttpExecutor(this._steps)
    }

    @Override
    IShellRegistry getShellExecutor() {
        return new org.commons.ShellExecutor(this._steps)
    }

    @Override
    IGithubRegistry getGithubExecutor() {
        return new org.commons.GithubExecutor(this._steps)
    }

    @Override
    IDockerRegistry getDockerExecutor() {
        return new org.commons.DockerExecutor(this._steps)
    }

    @Override
    IMavenRegistry getMavenExecutor() {
        return new org.codebaseBuild.MavenBuild(this._steps)
    }

    @Override
    IJinjaRegistry getJinjaExecutor() {
        return new org.commons.JinjaExecutor(this._steps)
    }

    @Override
    INameSpaceRegistry getK8NameSpaceExecutor() {
        return org.kubernetes.NameSpace(this._steps)
    }
}
