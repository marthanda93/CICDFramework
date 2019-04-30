import org.lib.*
import groovy.transform.Field

@Field dockerRegistry = "https://registry.hub.docker.com"

@groovy.transform.Field httpVars = [
	expectedHttpResponse: 200,
	acceptType: "APPLICATION_JSON",
	contentType: "APPLICATION_JSON",
	consoleLogResponseBody: false,
	validResponseCodes: "ok"
]


@groovy.transform.Field kubeApis = [
	namespace: [
		create: "POST::"
	],
]

@groovy.transform.Field maven = [
	
]
