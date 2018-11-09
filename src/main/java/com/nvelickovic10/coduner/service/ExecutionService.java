package com.nvelickovic10.coduner.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvelickovic10.coduner.logic.JSExecutioner;
import com.nvelickovic10.coduner.mongo.ExecutionRepository;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;
import com.nvelickovic10.coduner.rest.model.ExecutionBody;

@Service
public class ExecutionService {

	@Autowired
	private JSExecutioner jsExecutioner;

	@Autowired
	private ExecutionRepository repository;
	
	public List<ExecutionBody> getAllExecutions() {
		List<ExecutionEntity> executionEntities = repository.findAll();
		return executionEntities.stream()
	    .map(entity -> new ExecutionBody(entity)).collect(Collectors.toList());
	}

	public ExecutionBody execute(ExecutionBody execution) {
		ExecutionEntity executionEntity = getOrCreateExecutionEntity(execution);
		jsExecutioner.execute(executionEntity);
		executionEntity = repository.save(executionEntity);
		return new ExecutionBody(executionEntity);
	}

	private ExecutionEntity getOrCreateExecutionEntity(ExecutionBody execution) {
		if (execution.getId() == null) {
			return createExecutionEntity(execution);
		}
		
		Optional<ExecutionEntity> optEntity = repository.findById(execution.getId());
		if (optEntity.isPresent()) {
			return optEntity.get();
		} else {
			return createExecutionEntity(execution);
		}
	}
	
	private ExecutionEntity createExecutionEntity(ExecutionBody execution) {
		return new ExecutionEntity(execution);
	}
}
