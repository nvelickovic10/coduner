package com.nvelickovic10.coduner.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nvelickovic10.coduner.httpclient.HTTPNodeExecutionerCaller;
import com.nvelickovic10.coduner.mongo.ExecutionRepository;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;
import com.nvelickovic10.coduner.rest.model.Execution;
import com.nvelickovic10.coduner.rest.model.ExecutionDetails;

@Service
public class ExecutionService {

	@Autowired
	private HTTPNodeExecutionerCaller jsExecutioner;

	@Autowired
	private ExecutionRepository repository;

	public Execution.Response execute(com.nvelickovic10.coduner.rest.model.Execution.Request execution) {
		long serviceStartTime = System.nanoTime();
		long startTime = System.nanoTime();
		ExecutionEntity executionEntity = new ExecutionEntity(execution);
		jsExecutioner.execute(executionEntity);
		double totalExecutionTimeMs = (System.nanoTime() - startTime) / 1e6;

		startTime = System.nanoTime();
		executionEntity = repository.save(executionEntity);
		double totalMongoTimeMs = (System.nanoTime() - startTime) / 1e6;
		System.out.println(executionEntity);

		Execution.Response response = new Execution.Response(executionEntity);
		response.setTotalMongoTimeMs(totalMongoTimeMs);
		response.setTotalExecutionTimeMs(totalExecutionTimeMs);
		response.setTotalServiceTimeMs((System.nanoTime() - serviceStartTime) / 1e6);
		return response;
	}

	public List<ExecutionDetails.Response> getAllExecutionsDetails() {
		List<ExecutionEntity> executionEntities = repository.findAll();
		return executionEntities.stream().map(entity -> new ExecutionDetails.Response(entity))
				.collect(Collectors.toList());
	}
}
