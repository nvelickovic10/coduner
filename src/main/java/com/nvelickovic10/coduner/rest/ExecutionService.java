package com.nvelickovic10.coduner.rest;

import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvelickovic10.coduner.mongo.ExecutionEntity;
import com.nvelickovic10.coduner.mongo.ExecutionRepository;

@Service
public class ExecutionService {
	
	@Autowired
	private ExecutionRepository repository;
	
	private ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
	
	public ExecutionEntity save(ExecutionBody execution) {
		ExecutionEntity entity = new ExecutionEntity(execution);
		return repository.save(entity);
	}
	
	public Optional<ExecutionEntity> getById(String id) {
		return repository.findById(id);
	}
	
	public String execute(ExecutionBody execution) {
		ExecutionEntity entity = save(execution);
		String code = String.format("function a() {%s}; a();", execution.getCodeString());
		try {
			Object res = engine.eval(code);
			entity.setResult(res);
			repository.save(entity);
			System.out.println(entity);
			return res.toString();
		} catch (ScriptException e) {
			e.printStackTrace();
			return null;
		}
	}
}
