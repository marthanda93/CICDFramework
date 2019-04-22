package org.stepRegistry

import org.generic.IStepExecutor

interface IContext {
    IStepExecutor getStepExecutor()
}
