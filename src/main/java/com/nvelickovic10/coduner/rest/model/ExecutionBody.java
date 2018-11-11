package com.nvelickovic10.coduner.rest.model;

import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

public class ExecutionBody {
	private String id;
	private String codeString;
	private boolean error;
	private String result;
	private long executionTime = -1;

	public ExecutionBody() {
	}

	public ExecutionBody(String codeString) {
		this.codeString = codeString;
	}

	public ExecutionBody(ExecutionEntity executionEntity) {
		this.id = executionEntity.getId();
		this.codeString = executionEntity.getCodeString();
		this.error = executionEntity.isError();
		this.result = executionEntity.getResult();
		this.executionTime = executionEntity.getRunTime();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodeString() {
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
}
