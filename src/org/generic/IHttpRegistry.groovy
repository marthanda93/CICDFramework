package org.generic

interface IHttpRegistry {
	Object httpPost(Map payload, String flag = 'insecure')
	
	Map getRequest(Map payload)
	Map postRequest(Map payload)
	Map putRequest(Map payload)
	Map patchRequest(Map payload)
	Map deleteRequest(Map payload)
	Map headRquest(Map payload)
}