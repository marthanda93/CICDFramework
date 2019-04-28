import org.lib.*
import groovy.transform.Field

@groovy.transform.Field httpVars = [
	expectedHttpResponse: 200,
	acceptType: "APPLICATION_JSON",
	contentType: "APPLICATION_JSON",
	consoleLogResponseBody: true,
	validResponseCodes: "ok"
]
