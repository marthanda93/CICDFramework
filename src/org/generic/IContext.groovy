package org.generic

interface IContext {
    IHttpRegistry getHttpExecutor()
    IShellRegistry getShellExecutor()
    IGithubRegistry getGithubExecutor()
    IDockerRegistry getDockerExecutor()
    IMavenRegistry getMavenExecutor()
    IJinjaRegistry getJinjaExecutor()
    IK8NameSpaceRegistry getK8NameSpaceExecutor()
    IK8CSRegistry getK8ConfigNSecretExecutor()
    IJinTemplate jinTemplate()
}
