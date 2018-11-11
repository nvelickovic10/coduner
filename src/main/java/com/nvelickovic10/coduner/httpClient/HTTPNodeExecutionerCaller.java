package com.nvelickovic10.coduner.httpClient;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.nvelickovic10.coduner.mongo.model.ExecutionEntity;

@Component
public class HTTPNodeExecutionerCaller {

	private static final String RUNNER_TEMPLATE = "{\"codeString\":\"%s\"}";
	private final HttpClient httpclient = HttpClients.createDefault();
	private final HttpPost httppost = new HttpPost("http://localhost:3000/");

	public HTTPNodeExecutionerCaller() {
		httppost.setHeader("Content-Type", "application/json");
	}

	public void execute(ExecutionEntity executionEntity) {
		long startTime = System.nanoTime();

		try {
			HttpResponse response = callNodeExecutioner(executionEntity.getCodeString());
			Map<String, String> responseJSON = getResponseJSON(response.getEntity());

			executionEntity.setTotalRequestTime(System.nanoTime() - startTime);
			executionEntity.setCompileTime(
					Long.valueOf(responseJSON.get("compileTime")) - Long.valueOf(responseJSON.get("startTime")));
			executionEntity.setRunTime(
					Long.valueOf(responseJSON.get("runTime")) - Long.valueOf(responseJSON.get("startTime")));
			executionEntity.setTotalNodeTime(
					Long.valueOf(responseJSON.get("endTime")) - Long.valueOf(responseJSON.get("startTime")));

			if (response.getStatusLine().getStatusCode() == 200) {
				executionEntity.setResult((String) responseJSON.get("result"));
			} else if (response.getStatusLine().getStatusCode() == 500) {
				executionEntity.setResult(responseJSON.get("stack"));
				executionEntity.setError(true);
			}

		} catch (IOException e) {
			e.printStackTrace();
			executionEntity.setResult("Cannot be processed right now");
			executionEntity.setError(true);
		} finally {
			executionEntity.setTotalBootTime(System.nanoTime() - startTime);
		}
	}

	@SuppressWarnings("unchecked")
	private Map<String, String> getResponseJSON(HttpEntity entity) throws IOException {
		String json = EntityUtils.toString(entity, "UTF-8");
		return new Gson().fromJson(json, Map.class);
	}

	private HttpResponse callNodeExecutioner(String codeString) throws IOException {
		StringEntity jsonEntity = new StringEntity(String.format(RUNNER_TEMPLATE, codeString),
				ContentType.APPLICATION_JSON);
		httppost.setEntity(jsonEntity);
		return httpclient.execute(httppost);
	}
}
