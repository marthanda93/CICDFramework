import org.lib.*
import groovy.transform.Field

@Field appUrl = ""

@groovy.transform.Field svn = [
        method: "GIT",
        appUrl: "${appUrl}",
        branch: "master"
]
