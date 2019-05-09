import org.stepRegistry.ContextRegistry

def call(Object parameter) {
    ContextRegistry.registerDefaultContext(this)
    


accept parameter syntax:   kubectl object create/delete/help parameter/path of value.yaml


Parameter : list, map, string
Parameter.generateValueYaml = true/false(String, List)
Parameter.prune = true/false(String, List)

map(value.yams parameter)/string(path of value.yaml) -> create or process -> based on parameter need to understand wants to run as dependency or independent


switch (item.toLowerCase()) {
	case _steps.globalPipelineSetting.synonyms.pv:
		call pv class
		break
	case 'parent':
		return cloneWithDirectory(appParam)
		break
	case 'lfs':
		return lfsClone(appParam)
		break
	default:
		_steps.error "ERROR:GitClone: ${cloneType} Undefined Parameter!"
		break
}

    ContextRegistry.getContext().getJinjaExecutor().teamplateProcess(app)
}
