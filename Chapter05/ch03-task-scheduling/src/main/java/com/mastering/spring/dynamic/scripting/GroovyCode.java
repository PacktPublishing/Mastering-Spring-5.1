package com.mastering.spring.dynamic.scripting;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GroovyCode {

	/// Ranga/MasteringSpring5.1/Code/ch03-task-scheduling/target/classes
	// java -cp ".:/usr/local/opt/groovy/libexec/lib/*"
	/// com.mastering.spring.taskscheduling.dynamic.GroovyCode

	public static void main(String[] args) throws ScriptException {

		final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		final ScriptEngine scriptEngine = scriptEngineManager.getEngineByName("Groovy");
		final Bindings bindings1 = scriptEngine.createBindings();
		bindings1.put("str", "Let's pass from Java to Groovy");
		scriptEngine.eval(" println str", bindings1);
	}

}
