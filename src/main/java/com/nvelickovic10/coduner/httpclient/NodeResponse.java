package com.nvelickovic10.coduner.httpclient;

import java.io.Serializable;

public class NodeResponse implements Serializable {
	private static final long serialVersionUID = -3293597628323158462L;

	String message;
	int code;
	long startTime;
	long compileTime;
	long runTime;
	long endTime;
	transient String result;
}
