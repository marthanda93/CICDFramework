package org.generic

interface IContext {
    IHttpExecutor getHttpExecutor()
    IShellRegistry getShellExecutor()
}
