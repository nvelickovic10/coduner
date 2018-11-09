package com.nvelickovic10.coduner.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private ExecutionService executionService;

//	@GetMapping("/")
//	public ExecutionEntity index() {
//		Optional<ExecutionEntity> optEntity = executionService.getById("5be4ceb6fda1131e6043d050");
//		if (optEntity.isPresent()) {
//			return optEntity.get();
//		}
//		return null;
//	}

	@PostMapping(path = "/", consumes = "application/json", produces = "application/json")
	public String execute(@RequestBody ExecutionBody execution) {
		return executionService.execute(execution);
	}

}
