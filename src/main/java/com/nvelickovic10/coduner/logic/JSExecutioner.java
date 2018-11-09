package com.nvelickovic10.coduner.logic;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.stereotype.Component;

import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

@Component
public class JSExecutioner {

	private static final String RUNNER_TEMPLATE = "(function(){%s}());";
	private static final String ERROR_TEMPLATE = "{\"error\": \"%s\"}";
	private final ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

	public void execute(ExecutionEntity executionEntity) {
		long startTime = System.nanoTime();
		String code = String.format(RUNNER_TEMPLATE, executionEntity.getCodeString());
		try {
			Object res = engine.eval(code);
			executionEntity.setResult(res);
		} catch (ScriptException e) {
			e.printStackTrace();
			executionEntity.setResult(String.format(ERROR_TEMPLATE, e.getMessage()));
		} finally {
			executionEntity.setExecutionTime(System.nanoTime() - startTime);
		}
	}
}
