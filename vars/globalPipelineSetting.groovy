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

@groovy.transform.Field githubConfig = [
	namespace: [
		user: "Jenkins Robot",
		email: "automation@devops.com",
		timeout: 10
	],
]

@groovy.transform.Field kubeApis = [
	namespace: [
		create: "POST::"
	],
]

@groovy.transform.Field maven = [
	
]

@groovy.transform.Field monoRepoPaths = [
	repoName: ["path1", "path2", "path3"]
]