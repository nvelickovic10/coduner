package com.nvelickovic10.coduner.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

public class Execution {
	private Execution() {
	}

	@JsonInclude(value = Include.NON_DEFAULT)
	public static class Request {
		private String codeString;

		public String getCodeString() {
			return codeString;
		}

		public void setCodeString(String codeString) {
			this.codeString = codeString;
		}
	}

	@JsonInclude(value = Include.NON_DEFAULT)
	public static class Response {
		private String id;
		private String codeString;
		private boolean error;
		private String message;
		private double runTimeMs = -1;
		private double totalExecutionTimeMs = -1;
		private double totalMongoTimeMs = -1;
		private double totalServiceTimeMs = -1;
		private String result;

		public Response() {
		}

		public Response(ExecutionEntity executionEntity) {
			this.id = executionEntity.getId();
			this.codeString = executionEntity.getCodeString();
			this.error = executionEntity.isError();
			this.message = executionEntity.getMessage();
			this.runTimeMs = executionEntity.getTotalRunTime() / 1e6;
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

		public double getRunTimeMs() {
			return runTimeMs;
		}

		public void setRunTimeMs(double runTimeMs) {
			this.runTimeMs = runTimeMs;
		}

		public double getTotalExecutionTimeMs() {
			return totalExecutionTimeMs;
		}

		public void setTotalExecutionTimeMs(double totalExecutionTimeMs) {
			this.totalExecutionTimeMs = totalExecutionTimeMs;
		}

		public double getTotalMongoTimeMs() {
			return totalMongoTimeMs;
		}

		public void setTotalMongoTimeMs(double totalMongoTime) {
			this.totalMongoTimeMs = totalMongoTime;
		}

		public double getTotalServiceTimeMs() {
			return totalServiceTimeMs;
		}

		public void setTotalServiceTimeMs(double totalServiceTimeMs) {
			this.totalServiceTimeMs = totalServiceTimeMs;
		}

		public String getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}
	}
}
