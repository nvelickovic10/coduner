package com.nvelickovic10.coduner.performance.boot;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nvelickovic10.coduner.rest.model.Execution;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class HTTPBootExecutionerCaller {

	private static final String RUNNER_TEMPLATE = "{\"codeString\":\"%s\"}";
	private final HttpClient httpclient = HttpClients.createDefault();
	private final HttpPost httppost = new HttpPost("http://localhost:3001/");
	private final Logger logger = (Logger) LoggerFactory.getLogger("org.apache.http");
	private double responseTime;

	public HTTPBootExecutionerCaller() {
		logger.setLevel(Level.WARN);
		httppost.setHeader("Content-Type", "application/json");
	}

	public Execution.Response execute(String codeString) {
		try {
			HttpResponse response = callBootExecutioner(codeString);
			return convertToBootResponse(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public double getResponseTime() {
		return responseTime;
	}

	private Execution.Response convertToBootResponse(String jsonString) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.fromJson(jsonString, Execution.Response.class);
	}

	private HttpResponse callBootExecutioner(String codeString) throws IOException {
		StringEntity jsonEntity = new StringEntity(String.format(RUNNER_TEMPLATE, codeString),
				ContentType.APPLICATION_JSON);
		httppost.setEntity(jsonEntity);

		long startTime = System.nanoTime();
		HttpResponse response = httpclient.execute(httppost);
		this.responseTime = (System.nanoTime() - startTime) / 1e6;
		return response;
	}
}
