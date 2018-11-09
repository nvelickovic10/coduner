package com.nvelickovic10.liaservices.mongo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.nvelickovic10.liaservices.rest.ExecutionBody;

@Document(collection = "execution")
public class ExecutionEntity {

	@Id
	private String id;
	private String codeString;
	private Object result;
	private boolean active;

	public ExecutionEntity(ExecutionBody execution) {
		this.codeString = execution.getCodeString();
		this.result = null;
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

	public Object getResult() {
		return result;
	}

	public void setResult(Object res) {
		this.result = res;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return String.format("ExecutionEntity{id: '%s', codeString: '%s', result: %s, active: %s}", id, codeString,
				result, active);
	}

}
