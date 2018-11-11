package com.nvelickovic10.coduner.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

public class ExecutionDetails {
	private ExecutionDetails() {
	}

	@JsonInclude(value = Include.NON_DEFAULT)
	public static class Request {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	@JsonInclude(value = Include.NON_DEFAULT)
	public static class Response {
		private String id;
		private boolean error;
		private double compileTimeMs;
		private double totalRunTimeMs;
		private double totalNodeTimeMs;
		private double totalRequestTimeMs;
		private double totalDeserializeTimeMs;

		public Response(ExecutionEntity executionEntity) {
			id = executionEntity.getId();
			error = executionEntity.isError();
			compileTimeMs = executionEntity.getTotalCompileTime() / 1e6;
			totalRunTimeMs = executionEntity.getTotalRunTime() / 1e6;
			totalNodeTimeMs = executionEntity.getTotalNodeTime() / 1e6;
			totalRequestTimeMs = executionEntity.getTotalRequestTime() / 1e6;
			totalDeserializeTimeMs = executionEntity.getTotalDeserializeTime() / 1e6;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public boolean isError() {
			return error;
		}

		public void setError(boolean error) {
			this.error = error;
		}

		public double getCompileTimeMs() {
			return compileTimeMs;
		}

		public void setCompileTimeMs(double compileTimeMs) {
			this.compileTimeMs = compileTimeMs;
		}

		public double getTotalRunTimeMs() {
			return totalRunTimeMs;
		}

		public void setTotalRunTimeMs(double totalRunTimeMs) {
			this.totalRunTimeMs = totalRunTimeMs;
		}

		public double getTotalNodeTimeMs() {
			return totalNodeTimeMs;
		}

		public void setTotalNodeTimeMs(double totalNodeTimeMs) {
			this.totalNodeTimeMs = totalNodeTimeMs;
		}

		public double getTotalRequestTimeMs() {
			return totalRequestTimeMs;
		}

		public void setTotalRequestTimeMs(double totalRequestTimeMs) {
			this.totalRequestTimeMs = totalRequestTimeMs;
		}

		public double getTotalDeserializeTimeMs() {
			return totalDeserializeTimeMs;
		}

		public void setTotalDeserializeTimeMs(double totalDeserializeTimeMs) {
			this.totalDeserializeTimeMs = totalDeserializeTimeMs;
		}
	}
}
