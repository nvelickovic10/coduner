package com.nvelickovic10.coduner.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nvelickovic10.coduner.rest.model.Execution;
import com.nvelickovic10.coduner.rest.model.ExecutionDetails;
import com.nvelickovic10.coduner.service.ExecutionService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private ExecutionService executionService;

	@GetMapping(path = "/details", produces = "application/json")
	public List<ExecutionDetails.Response> getAllExecutionDetails() {
		return executionService.getAllExecutionsDetails();
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public Execution.Response execute(@RequestBody Execution.Request execution) {
		Execution.Response res = new Execution.Response();
		res.setTotalExecutionTimeMs(12.0);
		return res;
//		return executionService.execute(execution);
	}

}
