package org.commons

import org.generic.IHttpRegistry
import org.generic.IMissingObject

class HttpExecutor implements IHttpRegistry, IMissingObject, Serializable {
    private _steps

    HttpExecutor(steps) {
        this._steps = steps
    }

    @Override
    Map getRequest(Map payload) {
        /**
            payload = [
                auth: authName,
                url: url,

            ]
        */
        _steps.println CommonValidation(_steps).stringValidation(payload.auth)
        /*
        try {
            if(payload.auth)
            _steps.wrap([$class: 'MaskPasswordsBuildWrapper', varPasswordPairs: [[password: "${clientToken}", var: 'PASSWORD']]]) {
                response = _steps.httpRequest(
                    acceptType: globalPipelineSetting.httpVars.acceptType,
                    contentType: globalPipelineSetting.httpVars.contentType,
                    httpMode: 'GET',
                    consoleLogResponseBody: globalPipelineSetting.httpVars.consoleLogResponseBody,
                    customHeaders: [[name: 'X-Vault-Token', value: "${clientToken}"]],
                    url:
                )
            }
            if(response.status.toInteger() != 200) {
                _steps.error "failed"
            }
        catch(e) {
            _steps.error ""
        }

        response = _steps.readJSON text: response.content
        def datasize = response.data.size()
        */
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
        "Caught missing property: $name"
    }

    @Override
    String methodMissing(String name, Object args) {
        _steps.println name
        _steps.println """
            Possible solutions: 
            String bashShell(String command)
            String batchScript(String command)
            String powerShellScript(String command)
        """

        _steps.error "Missing method name is $name"
    }
}
