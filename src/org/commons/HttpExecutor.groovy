package org.commons

import org.generic.IHttpRegistry
import org.generic.IMissingObject
import org.generic.CommonUtilities

class HttpExecutor implements IHttpRegistry, IMissingObject, Serializable {
    private _steps

    HttpExecutor(steps) {
        this._steps = steps
    }

    @Override
    Object httpPost(Map parameter, Object payload, String flag = 'insecure') {
        /**
            payload = [
                customHeaders: [Authorization:"Bearer Gtoken", bob:"two"]
                payload: ''
                url: "https://api.github.com/repositories"
            ]
        */

        Object response

        if(CommonUtilities.mapValidation(parameter.customHeaders) && parameter.customHeaders.containsKey('Authorization')) {
            _steps.withCredentials([_steps.string(credentialsId: parameter.customHeaders.get('Authorization').split(" ")[1], variable: 'maskToken')]) {
                parameter.customHeaders.Authorization = "${parameter.customHeaders.get('Authorization').split(" ")[0]} ${_steps.maskToken}"

                parameter.customHeaders = parameter.customHeaders.cHeader()

// httpRequest 
// customHeaders: [
//     [maskValue: true, name: 'Authorization', value: 'Bearer ']
// ], 
// requestBody: '{"apiVersion":"v1","kind":"Namespace","metadata":{"name":"anand","labels":{"environment":"dev","app":"name"}}}', 
// responseHandle: 'NONE', 
// url: 'https://104.197.4.139/api/v1/namespaces', 
// validResponseCodes: '100:500'

def a = """${payload}"""
_steps.println a
_steps.println a.getClass()


                response = _steps.httpRequest(
                    acceptType: _steps.globalPipelineSetting.httpVars.acceptType,
                    contentType: _steps.globalPipelineSetting.httpVars.contentType,
                    httpMode: 'POST',
                    consoleLogResponseBody: true,
                    customHeaders: parameter.customHeaders,
                    requestBody: "\'${payload}\'",
                    ignoreSslErrors: true,
                    url: parameter.url
                )
            }
        }

        response = _steps.readJSON text: response.content

        if(response.data.size() > 0) {
            return response
        } else {
            return false
        }


        if(CommonUtilities.mapValidation(payload)) {
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

    Object httpDsl(String httpMethod, Map payload) {
        /**
            payload = [
                credentialId: "toekn Gtoken",
                customHeaders: [Authorization:"toekn Gtoken", bob:"two"]
                url: "https://api.github.com/repositories"
            ]
        */

        if(CommonUtilities.stringValidation(httpMethod) && httpMethod in ["GET", "POST"]) {
            if(payload.containsKey('customHeaders')) {
                return _steps.httpRequest(
                    acceptType: _steps.globalPipelineSetting.httpVars.acceptType,
                    contentType: _steps.globalPipelineSetting.httpVars.contentType,
                    httpMode: httpMethod,
                    consoleLogResponseBody: _steps.globalPipelineSetting.httpVars.consoleLogResponseBody,
                    customHeaders: payload.customHeaders,
                    url: payload.url
                )
            } else {
                return _steps.httpRequest(
                    acceptType: _steps.globalPipelineSetting.httpVars.acceptType,
                    contentType: _steps.globalPipelineSetting.httpVars.contentType,
                    httpMode: httpMethod,
                    consoleLogResponseBody: _steps.globalPipelineSetting.httpVars.consoleLogResponseBody,
                    url: payload.url
                )
            }
        }
    }
    
    @Override
    Map getRequest(Map payload) {
        Object response

        if(CommonUtilities.mapValidation(payload.customHeaders) && payload.customHeaders.containsKey('Authorization')) {
            _steps.withCredentials([_steps.string(credentialsId: payload.customHeaders.get('Authorization').split(" ")[1], variable: 'maskToken')]) {
                payload.customHeaders.Authorization = "Authorization ${_steps.maskToken}"

                payload.customHeaders = payload.customHeaders.cHeader()
                response = httpDsl("GET", payload)
            }
        } else {
            response = httpDsl("GET", payload)
        }

        response = _steps.readJSON text: response.content

        if(response.data.size() > 0) {
            return response
        } else {
            return false
        }
    }

    @Override
    Map postRequest(Map payload) {
        Object response

        if(CommonUtilities.mapValidation(payload.customHeaders) && payload.customHeaders.containsKey('Authorization')) {
            _steps.withCredentials([_steps.string(credentialsId: payload.customHeaders.get('Authorization').split(" ")[1], variable: 'maskToken')]) {
                payload.customHeaders.Authorization = "Authorization ${_steps.maskToken}"

                payload.customHeaders = payload.customHeaders.cHeader()
                response = httpDsl("GET", payload)
            }
        } else {
            response = httpDsl("GET", payload)
        }

        response = _steps.readJSON text: response.content

        if(response.data.size() > 0) {
            return response
        } else {
            return false
        }
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
        _steps.error "METHODMISSING HttpExecutor: Caught missing method: $name"
    }
}
