package org.generic

interface IContext {
    IHttpRegistry getHttpExecutor()
    IShellRegistry getShellExecutor()
    IGithubRegistry getGithubExecutor()
    IDockerRegistry getDockerExecutor()
    IMavenRegistry getMavenExecutor()
}
