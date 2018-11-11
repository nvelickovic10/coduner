package com.nvelickovic10.coduner.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

public class ExecutionDetails {
	public static class Request {
		private String id;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}
	}

	@JsonInclude(value = Include.NON_NULL)
	public static class Response {
		private String id;
		private String codeString;
		private boolean error;
		private long totalCompileTime = -1;
		private long totalRunTime = -1;
		private long totalNodeTime = -1;
		private long totalRequestTime = -1;
		private long totalDeserializeTime = -1;
		private long totalBootTime = -1;

		private double compileTimeMs;
		private double totalRunTimeMs;
		private double totalNodeTimeMs;
		private double totalRequestTimeMs;
		private double totalDeserializeTimeMs;
		private double totalBootTimeMs;

		private String result;

		public Response(ExecutionEntity executionEntity) {
			id = executionEntity.getId();
			codeString = executionEntity.getCodeString();
			error = executionEntity.isError();
			totalCompileTime = executionEntity.getTotalCompileTime();
			compileTimeMs = totalCompileTime / 1e6;
			totalRunTime = executionEntity.getTotalRunTime();
			totalRunTimeMs = totalRunTime / 1e6;
			totalNodeTime = executionEntity.getTotalNodeTime();
			totalNodeTimeMs = totalNodeTime / 1e6;
			totalRequestTime = executionEntity.getTotalRequestTime();
			totalRequestTimeMs = totalRequestTime / 1e6;
			totalDeserializeTime = executionEntity.getTotalDeserializeTime();
			totalDeserializeTimeMs = totalDeserializeTime / 1e6;
			totalBootTime = executionEntity.getTotalBootTime();
			totalBootTimeMs = totalBootTime / 1e6;
			result = executionEntity.getResult();
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

		public long getTotalCompileTime() {
			return totalCompileTime;
		}

		public void setTotalCompileTime(long totalCompileTime) {
			this.totalCompileTime = totalCompileTime;
		}

		public long getTotalRunTime() {
			return totalRunTime;
		}

		public void setTotalRunTime(long totalRunTime) {
			this.totalRunTime = totalRunTime;
		}

		public long getTotalNodeTime() {
			return totalNodeTime;
		}

		public void setTotalNodeTime(long totalNodeTime) {
			this.totalNodeTime = totalNodeTime;
		}

		public long getTotalRequestTime() {
			return totalRequestTime;
		}

		public void setTotalRequestTime(long totalRequestTime) {
			this.totalRequestTime = totalRequestTime;
		}

		public long getTotalDeserializeTime() {
			return totalDeserializeTime;
		}

		public void setTotalDeserializeTime(long totalDeserializeTime) {
			this.totalDeserializeTime = totalDeserializeTime;
		}

		public long getTotalBootTime() {
			return totalBootTime;
		}

		public void setTotalBootTime(long totalBootTime) {
			this.totalBootTime = totalBootTime;
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

		public double getTotalBootTimeMs() {
			return totalBootTimeMs;
		}

		public void setTotalBootTimeMs(double totalBootTimeMs) {
			this.totalBootTimeMs = totalBootTimeMs;
		}

		public Object getResult() {
			return result;
		}

		public void setResult(String result) {
			this.result = result;
		}
	}
}
