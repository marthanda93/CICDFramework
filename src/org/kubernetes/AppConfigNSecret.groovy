AppConfigNSecret

independent module

-> takeconfig from github
-> takeconfig from consul
-> takeconfig from nexus


clone config to local UUID path -> clean

Var/ update: false/true/delete -> if config found want to update or delete
user param: type of config: environment, volume
 


Boolean prun()
Boolean collectConfig()
Boolean healthCheck()	//After creating, this will check is updated or newly created or not


1> clone config
2> collect exiting configmap name list
3> labelling 
4> creating
5> verifying
6> prune