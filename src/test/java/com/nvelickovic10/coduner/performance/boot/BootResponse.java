package com.nvelickovic10.coduner.performance.boot;

import java.io.Serializable;

public class BootResponse implements Serializable {
	private static final long serialVersionUID = -5887120311369820310L;

	String id;
	String codeString;
	boolean error;
	double runTimeMs;
	double totalExecutionTimeMs;
	double totalMongoTimeMs;
	double totalServiceTimeMs;
	transient String result;

	@Override
	public String toString() {
		return String.format(
				"BootResponse {\n" + "id: %s,\n" + "codeString: %s,\n" + "error: %s,\n" + "runTimeMs: %s,\n"
						+ "totalExecutionTimeMs: %s,\n" + "totalMongoTimeMs: %s,\n" + "totalServiceTimeMs: %s,\n"
						+ "result: %s,\n" + "}",
				id, codeString, error, runTimeMs, totalExecutionTimeMs, totalMongoTimeMs, totalServiceTimeMs, result);
	}
}
