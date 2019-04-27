package org.utilities

import org.generic.IHttpRegistry
import org.stepRegistry.ContextRegistry

class HttpBuild implements Serializable {
    private Map _pipelineParams

    HttpBuild(Map _pipelineParams) {
        this._pipelineParams = _pipelineParams
    }

    void build() {
    	ContextRegistry.getContext().getHttpExecutor().getRequest(_pipelineParams)
    }
}
