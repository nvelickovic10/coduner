package com.nvelickovic10.coduner.httpclient;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

public class NodeResponseDeserializer implements JsonDeserializer<NodeResponse> {

	@Override
	public NodeResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		NodeResponse responseJSON = new Gson().fromJson(json.getAsJsonObject(), NodeResponse.class);

		// TODO: must be better than this
		responseJSON.result = json.getAsJsonObject().get("result").toString();
		return responseJSON;
	}

}
