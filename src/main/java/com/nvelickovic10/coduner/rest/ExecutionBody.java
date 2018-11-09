package com.nvelickovic10.coduner.rest;

public class ExecutionBody {
	private String codeString;
	
	public ExecutionBody() {}
	
	public ExecutionBody(String codeString) {
		this.codeString = codeString;
	}

	public String getCodeString() {
		return codeString;
	}

	public void setCodeString(String codeString) {
		this.codeString = codeString;
	}
	
}
