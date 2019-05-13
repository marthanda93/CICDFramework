package org.generic

import hudson.util.RemotingDiagnostics
import java.time.LocalTime

class CommonUtilities implements Serializable {
	static boolean stringValidation(Object word) {
		if (word != null && word != "" && word instanceof String && word.size() > 0) {
			return true
		} else {
			return false
		}
	}

    static boolean listValidation(Object collection) {
        if (collection instanceof java.util.List && collection.size > 0) {
            return true
        } else {
            return false
        }
    }

    static boolean booleanValidation(Object collection) {
        if (collection instanceof Boolean) {
            return true
        } else {
            return false
        }
    }

    static boolean mapValidation(Object collection) {
        if ((collection instanceof java.util.Map || collection instanceof java.util.LinkedHashMap) && collection.size() > 0) {
            return true
        } else {
            return false
        }
    }

    static boolean gitValidation(Object collection) {
    	if(mapValidation(collection) && collection.size() <= 3 && (stringValidation(collection.url) && stringValidation(collection.branch))) {
    		return true
    	} else {
    		return false
    	}
    }

    static Object executeOnMaster(String cmd, String path, Integer timeout = 1000) {
        Object out = new StringBuffer(), err = new StringBuffer()

        Object proc = cmd.execute(null, new File(path))
        proc.consumeProcessOutput(out, err)
        proc.waitForOrKill(4 * timeout)

        if( out.size() > 0 ) return out
        return (err.size() > 0) ? err : "ERROR: Process killed before completing!\n${err}"
    }

    static boolean executeOnWorker(String worker, String cmd) {
        Object slave = Jenkins.instance.slaves.find {slave -> slave.displayName == worker}
        Object channel = slave.getComputer().getChannel()

        println RemotingDiagnostics.executeGroovy( "${cmd}.execute().in.text", channel)
    }
}