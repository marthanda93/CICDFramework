//import org.lib.*
import groovy.transform.Field

@Field dockerRegistry = "https://registry.hub.docker.com"
@Field jinjaFormat = 'yaml'

@groovy.transform.Field opsRepo = [
    url: "git@github.com:marthanda93/ops.git",
    branch: "master",
    credentialsId: "GithubSSh"
]

@groovy.transform.Field deploymentStages = [
	production: ['prod', 'production'],
	pre_production: ['pre-prod', 'preprod', 'pre_prod', 'perf', 'pre_production'],
	staging: ['reldev', 'qa', 'relqa', 'uat', 'staging'],
	development: ['gatedbuild', 'dev', 'development']
]

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
	artifactExtension: 'jar',
	command: 'mvn clean install'
]

@groovy.transform.Field monoRepoPaths = [
	repoName: ["path1", "path2", "path3"]
]

@groovy.transform.Field synonyms = [
	pv: ["pv", "pvs", "persistentvolume", "persistentvolumes"],
	ns: ["namespace", "ns", "names", "nspace"]
]

@groovy.transform.Field standardization = [
	valueofTeamplate: '{{ application }}/{{ deploymentStages }}/templateParameter/{{ kubeKind }}',
	artifact: '',
	namespace: 'poc{{ deploymentStage }}',
	deployment: '',
	service: '',
	configmap: '',
	secret: ''
]