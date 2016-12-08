package com.ruzhi.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JsonUtils {
	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);
	private static ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object object){
		String json = "";
		try {
			json = mapper.writeValueAsString(object);
		} catch (Exception e) {
			log.error("mapper.writeValueAsString error ",e);
		}

		return json;
	}

	public static Object toObject(String string,Class clazz){
		Object object = null;
		try {
			object = mapper.readValue(string, clazz);
		} catch (Exception e) {
			log.error("mapper.readValue error,string="+string,e);
		}
		return object;
	}

	@SuppressWarnings("unchecked")
	public static Map<String,String> toMap(String jsonString) {
		if(jsonString == null || jsonString.isEmpty())
			return null;
		try {
			JSONObject _obj = JSONObject.fromObject(jsonString);
			Map<String,String> resultMap = (Map<String,String>) JSONObject.toBean(_obj, Map.class);
			return resultMap;
		} catch (Exception e) {
		}
		return null;
	}


	public static JSONObject toBean(String jsonString) {
		if(jsonString == null || jsonString.isEmpty())
			return null;
		try {
			return JSONObject.fromObject(jsonString);

		} catch (Exception e) {

		}
		return null;
	}
	public static JSONArray toArray(String jsonString) {
		if(jsonString == null || jsonString.isEmpty())
			return null;
		try {
			return JSONArray.fromObject(jsonString);

		} catch (Exception e) {

		}
		return null;
	}

}
