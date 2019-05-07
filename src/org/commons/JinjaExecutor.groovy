package org.commons

import org.generic.IJinjaRegistry
import org.stepRegistry.ContextRegistry

class JinjaExecutor implements IJinjaRegistry, Serializable {
	private _steps

	JinjaExecutor(_steps) {
		this._steps = _steps
	}

	@Override
	Void build() {
		_step.writeFile file: "nginx.json", text: _step.libraryResource "org.example.param.nginx.json"
		_step.writeFile file: "nginx.j2", text: _step.libraryResource "org.example.template.nginx.j2"
		ContextRegistry.getContext().getShellExecutor().bashShell('ls -l')
		ContextRegistry.getContext().getShellExecutor().bashShell('j2 -f json nginx.j2 nginx.json -o nginx.conf')
		ContextRegistry.getContext().getShellExecutor().bashShell('ls -l')
	}
}