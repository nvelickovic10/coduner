package com.nvelickovic10.coduner.mongo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nvelickovic10.coduner.rest.model.Execution;

@Document(collection = "execution")
public class ExecutionEntity {

	@Id
	private String id;
	private String codeString;
	@Transient
	private String message;
	private boolean error;
	private long totalCompileTime = -1;
	private long totalRunTime = -1;
	private long totalNodeTime = -1;
	private long totalRequestTime = -1;
	private long totalDeserializeTime = -1;
	private String result;

	public ExecutionEntity() {
	}

	public ExecutionEntity(Execution.Request execution) {
		this.codeString = execution.getCodeString();
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTotalCompileTime() {
		return totalCompileTime;
	}

	public void setTotalCompileTime(long totalCompileTime) {
		this.totalCompileTime = totalCompileTime < 0 ? -1 : totalCompileTime;
	}

	public long getTotalRunTime() {
		return totalRunTime;
	}

	public void setTotalRunTime(long totalrunTime) {
		this.totalRunTime = totalrunTime < 0 ? -1 : totalrunTime;
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

	public long getTotalDeserializeTime() {
		return totalDeserializeTime;
	}

	public void setTotalDeserializeTime(long totalDeserializeTime) {
		this.totalDeserializeTime = totalDeserializeTime;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String res) {
		this.result = res;
	}

	@Override
	public String toString() {
		return String.format(
				"ExecutionEntity{\n" + "id: '%s',\n" + "codeString: '%s',\n" + "error: %s,\n" + "*message: %s,\n"
						+ "compileTime: %s,\n" + "executionTime: %s,\n" + "totalNodeTime: %s,\n"
						+ "totalRequestTime: %s,\n" + "result: %s" + "\n" + "}",
				id, codeString, error, message, totalCompileTime, totalRunTime, totalNodeTime, totalRequestTime,
				result);
	}
}
