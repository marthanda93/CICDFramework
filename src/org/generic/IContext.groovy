package org.generic

interface IContext {
    IShellRegistry getStepExecutor()
    IHttpExecutor getSExecutor()
}
