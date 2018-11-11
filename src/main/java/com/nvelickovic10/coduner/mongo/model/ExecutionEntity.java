package com.nvelickovic10.coduner.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nvelickovic10.coduner.rest.model.ExecutionBody;

@Document(collection = "execution")
public class ExecutionEntity {

	@Id
	private String id;
	private String codeString;
	private boolean error;
	private String result;
	private long compileTime = -1;
	private long runTime = -1;
	private long totalNodeTime = -1;
	private long totalRequestTime = -1;
	private long totalBootTime = -1;
	private boolean active;

	public ExecutionEntity() {
	}

	public ExecutionEntity(ExecutionBody execution) {
		this.codeString = execution.getCodeString();
		this.active = true;
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

	public String getResult() {
		return result;
	}

	public void setResult(String res) {
		this.result = res;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public long getCompileTime() {
		return compileTime;
	}

	public void setCompileTime(long compileTime) {
		this.compileTime = compileTime < 0 ? -1 : compileTime;
	}

	public long getRunTime() {
		return runTime;
	}

	public void setRunTime(long executionTime) {
		this.runTime = executionTime < 0 ? -1 : executionTime;
	}

	public long getTotalNodeTime() {
		return totalNodeTime;
	}

	public void setTotalNodeTime(long totalNodeTime) {
		this.totalNodeTime = totalNodeTime < 0 ? -1 : totalNodeTime;
	}

	public long getTotalRequestTime() {
		return totalRequestTime;
	}

	public void setTotalRequestTime(long totalRequestTime) {
		this.totalRequestTime = totalRequestTime < 0 ? -1 : totalRequestTime;
	}

	public long getTotalBootTime() {
		return totalBootTime;
	}

	public void setTotalBootTime(long totalBootTime) {
		this.totalBootTime = totalBootTime < 0 ? -1 : totalBootTime;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return String.format(
				"ExecutionEntity{id: '%s', codeString: '%s', result: %s, error: %s, compileTime: %s, executionTime: %s, totalNodeTime: %s, totalRequestTime: %s, totalBootTime: %s, active: %s}",
				id, codeString, result, error, compileTime, runTime, totalNodeTime, totalRequestTime, totalBootTime,
				active);
	}
}
