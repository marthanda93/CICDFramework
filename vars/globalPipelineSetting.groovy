import org.lib.*
import groovy.transform.Field

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

@Field dockerRegistry = "https://registry.hub.docker.com"