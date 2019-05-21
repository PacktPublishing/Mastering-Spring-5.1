package com.mastering.spring.dynamic.scripting;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JavaScriptCode {

	public static void main(String[] args) throws ScriptException {
		final ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
		final ScriptEngine scriptEngine = scriptEngineManager.getEngineByName( "Nashorn");
		final Bindings bindings = scriptEngine.createBindings();
		bindings.put( "str", "Let's pass from Java to JavaScript" );		         
		scriptEngine.eval( " print(str)", bindings );
	}

}
