package com.nvelickovic10.coduner.performance.boot;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class HTTPBootExecutionerCaller {

	private static final String RUNNER_TEMPLATE = "{\"codeString\":\"%s\"}";
	private final HttpClient httpclient = HttpClients.createDefault();
	private final HttpPost httppost = new HttpPost("http://localhost:3001/");

	public HTTPBootExecutionerCaller() {
		httppost.setHeader("Content-Type", "application/json");
	}

	public BootResponse execute(String codeString) {
		try {
			HttpResponse response = callBootExecutioner(codeString);
			return convertToNodeResponse(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private BootResponse convertToNodeResponse(String jsonString) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.fromJson(jsonString, BootResponse.class);
	}

	private HttpResponse callBootExecutioner(String codeString) throws IOException {
		StringEntity jsonEntity = new StringEntity(String.format(RUNNER_TEMPLATE, codeString),
				ContentType.APPLICATION_JSON);
		httppost.setEntity(jsonEntity);
		return httpclient.execute(httppost);
	}
}
