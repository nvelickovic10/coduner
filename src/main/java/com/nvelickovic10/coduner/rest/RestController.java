package com.nvelickovic10.coduner.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.nvelickovic10.coduner.rest.model.ExecutionBody;
import com.nvelickovic10.coduner.service.ExecutionService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private ExecutionService executionService;

	@GetMapping("/")
	public List<ExecutionBody> index() {
		return executionService.getAllExecutions();
	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public ExecutionBody execute(@RequestBody ExecutionBody execution) {
		return executionService.execute(execution);
	}

}
