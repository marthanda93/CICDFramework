package org.commons

import org.generic.IHttpRegistry
import org.generic.IMissingObject

class HttpExecutor implements IHttpRegistry, IMissingObject, Serializable {
    private _steps

    HttpExecutor(steps) {
        this._steps = steps
    }

    Closure<Object> httpDsl = { String httpMethod, Map payload ->
        if(CommonValidation.stringValidation(httpMethod) && httpMethod == "GET") {
            response = _steps.httpRequest(
                acceptType: globalPipelineSetting.httpVars.acceptType,
                contentType: globalPipelineSetting.httpVars.contentType,
                httpMode: httpMethod,
                consoleLogResponseBody: globalPipelineSetting.httpVars.consoleLogResponseBody,
                url: payload.url
            )

            return response
        }
    }
    
    @Override
    Map getRequest(Map payload) {
        /**
            payload = [
                auth: authName,
                url: url,

            ]
        */
        def a = httpDsl("GET", payload)
        _steps.println a.status.toInteger()
        _steps.println "-----//aa"

        // try {
        //     if(payload.auth)
        //     _steps.wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "clientToken", var: 'PASSWORD']]]) {
        //         response = _steps.httpRequest(
        //             acceptType: globalPipelineSetting.httpVars.acceptType,
        //             contentType: globalPipelineSetting.httpVars.contentType,
        //             httpMode: 'GET',
        //             consoleLogResponseBody: globalPipelineSetting.httpVars.consoleLogResponseBody,
        //             customHeaders: [[name: 'X-Vault-Token', value: "${clientToken}"]],
        //             url:
        //         )
        //     }
        //     if(response.status.toInteger() != 200) {
        //         _steps.error "failed"
        //     }
        // catch(e) {
        //     _steps.error ""
        // }

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
