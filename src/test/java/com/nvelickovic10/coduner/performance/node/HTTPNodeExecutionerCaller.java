package com.nvelickovic10.coduner.performance.node;

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
import com.nvelickovic10.coduner.httpclient.NodeResponse;

public class HTTPNodeExecutionerCaller {

	private static final String RUNNER_TEMPLATE = "{\"codeString\":\"%s\"}";
	private final HttpClient httpclient = HttpClients.createDefault();
	private final HttpPost httppost = new HttpPost("http://localhost:3000/");

	public HTTPNodeExecutionerCaller() {
		httppost.setHeader("Content-Type", "application/json");
	}

	public NodeResponse execute(String codeString) {
		try {
			HttpResponse response = callNodeExecutioner(codeString);
			return convertToNodeResponse(EntityUtils.toString(response.getEntity()));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private NodeResponse convertToNodeResponse(String jsonString) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		return gson.fromJson(jsonString, NodeResponse.class);
	}

	private HttpResponse callNodeExecutioner(String codeString) throws IOException {
		StringEntity jsonEntity = new StringEntity(String.format(RUNNER_TEMPLATE, codeString),
				ContentType.APPLICATION_JSON);
		httppost.setEntity(jsonEntity);
		return httpclient.execute(httppost);
	}
}
