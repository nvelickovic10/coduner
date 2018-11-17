package com.nvelickovic10.coduner.httpclient;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.LongSerializationPolicy;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

@Component
public class HTTPNodeExecutionerCaller {

	private static final String RUNNER_TEMPLATE = "{\"codeString\":\"%s\"}";
	private final HttpClient httpclient = HttpClients.createDefault();
	private final HttpPost httppost = new HttpPost("http://localhost:3000/");
	private final Logger logger = (Logger) LoggerFactory.getLogger("org.apache.http");

	public HTTPNodeExecutionerCaller() {
		logger.setLevel(Level.WARN);
		httppost.setHeader("Content-Type", "application/json");
	}

	public void execute(ExecutionEntity executionEntity) {
		try {
			long startTime = System.nanoTime();
			HttpResponse response = callNodeExecutioner(executionEntity.getCodeString());
			long requestTime = System.nanoTime() - startTime;

			NodeResponse nodeResponse = convertToNodeResponse(EntityUtils.toString(response.getEntity()));

			executionEntity.setTotalCompileTime(nodeResponse.compileTime - nodeResponse.startTime);
			executionEntity.setTotalRunTime(nodeResponse.runTime - nodeResponse.compileTime);
			executionEntity.setTotalNodeTime(nodeResponse.endTime - nodeResponse.startTime);
			executionEntity.setTotalRequestTime(requestTime);

			executionEntity.setMessage(nodeResponse.message);
			executionEntity.setResult(nodeResponse.result);
			executionEntity.setError(response.getStatusLine().getStatusCode() != 200);
			executionEntity.setTotalDeserializeTime(System.nanoTime() - startTime - requestTime);
		} catch (IOException e) {
			e.printStackTrace();
			executionEntity.setResult("Cannot be processed right now");
			executionEntity.setError(true);
		}
	}

	private NodeResponse convertToNodeResponse(String jsonString) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setLongSerializationPolicy(LongSerializationPolicy.STRING);
		gsonBuilder.registerTypeAdapter(NodeResponse.class, new NodeResponseDeserializer());
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
