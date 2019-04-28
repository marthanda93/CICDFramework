package org.commons

import org.generic.IHttpRegistry
import org.generic.IMissingObject
import org.generic.CommonUtilities

class HttpExecutor implements IHttpRegistry, IMissingObject, Serializable {
    private _steps

    HttpExecutor(steps) {
        this._steps = steps
    }

    Object httpDsl(String httpMethod, Map payload, def cred) {
        if(CommonUtilities.stringValidation(httpMethod) && httpMethod == "GET") {
            return _steps.httpRequest(
                acceptType: _steps.globalPipelineSetting.httpVars.acceptType,
                contentType: _steps.globalPipelineSetting.httpVars.contentType,
                httpMode: httpMethod,
                consoleLogResponseBody: _steps.globalPipelineSetting.httpVars.consoleLogResponseBody,
                customHeaders: payload.customHeaders,
                url: payload.url
            )
        }
    }
    
    @Override
    Map getRequest(Map payload) {
        /**
            payload = [
                credentialId: "toekn Gtoken",
                customHeaders: [Authorization:"toekn Gtoken", bob:"two"]
                url: "https://api.github.com/repositories"
            ]
        */

        Object response

        if(CommonUtilities.mapValidation(payload.customHeaders) && payload.customHeaders.containsKey('Authorization')) {
            _steps.withCredentials([_steps.string(credentialsId: payload.customHeaders.get('Authorization').split(" ")[1], variable: 'maskToken')]) {
                payload.customHeaders.Authorization = "Authorization ${_steps.maskToken}"

                payload.customHeaders = payload.customHeaders.cHeader()
                // response = httpDsl("GET", payload)
            }
        }

// start using pointer and refrence of variable

        // try {
                    response = httpDsl("GET", payload)

        // response = _steps.readJSON text: response.content
        // def datasize = response.data.size()
    }

    @Override
    Map postRequest(Map payload) {
        _steps.println("PASS")
        /*_steps.httpRequest(
            acceptType: 'APPLICATION_JSON',
            contentType: 'APPLICATION_JSON',
            httpMode: 'POST',
            requestBody: body,
            consoleLogResponseBody: false,
            url: url
            validResponseContent
            validResponseCodes
            timeout
            ignoreSslErrors
            customHeaders
        )*/
    }

    @Override
    Map putRequest(Map payload) {
        _steps.println("PASS")
    }

    @Override
    Map patchRequest(Map payload) {
        _steps.println("PASS")
    }

    @Override
    Map deleteRequest(Map payload) {
        _steps.println("PASS")
    }

    @Override
    Map headRquest(Map payload) {
        _steps.println("PASS")
    }

    @Override
    String propertyMissing(String name) {
        _steps.error "PROPERTYMISSING HttpExecutor: Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.println """
        Possible solutions: 
            Map getRequest(Map payload)
            Map postRequest(Map payload)
            Map putRequest(Map payload)
            Map patchRequest(Map payload)
            Map deleteRequest(Map payload)
            Map headRquest(Map payload)
        """

        _steps.error "METHODMISSING HttpExecutor: Caught missing method: $name"
    }
}
