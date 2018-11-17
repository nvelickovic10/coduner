package com.nvelickovic10.coduner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.nvelickovic10.coduner.mongo.ExecutionRepository;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

@Service
public class ReportService {

	@Autowired
	private ExecutionRepository repository;

	@Async("ConcurrentTaskExecutor")
	public void reportExecution(ExecutionEntity executionEntity) {
		long startTime = System.nanoTime();
		executionEntity = repository.save(executionEntity);
//		System.out.println(executionEntity);
//		System.out.println("mongoTime: " + (System.nanoTime() - startTime) / 1e6);
	}
}
