package com.nvelickovic10.coduner.rest.model;

import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

public class ExecutionBody {
	private String id;
	private String codeString;
	private boolean isExecuted;
	private long executionTime = -1;
	private Object result;

	public ExecutionBody() {
	}

	public ExecutionBody(String codeString) {
		this.codeString = codeString;
	}

	public ExecutionBody(ExecutionEntity executionEntity) {
		this.id = executionEntity.getId();
		this.codeString = executionEntity.getCodeString();
		this.isExecuted = executionEntity.getExecutionTime() > -1;
		this.executionTime = executionEntity.getExecutionTime();
		this.result = executionEntity.getResult();
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

	public boolean isExecuted() {
		return isExecuted;
	}

	public void setExecuted(boolean isExecuted) {
		this.isExecuted = isExecuted;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public long getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(long executionTime) {
		this.executionTime = executionTime;
	}
}
