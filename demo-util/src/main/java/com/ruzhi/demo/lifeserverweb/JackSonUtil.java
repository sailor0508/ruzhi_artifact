package com.ruzhi.demo.lifeserverweb;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json解析
 * along@taobao.com
 */
public class JackSonUtil {
	private final static Logger log = LoggerFactory.getLogger(JackSonUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public static Object jsonToObject(String json, Class cls) {
		if(StringUtils.isNotEmpty(json)){
			try {
				//允许json串里面的key value不带双引号
				mapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				// 允许制定的object中的属性没有json串中某个key
				mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				return mapper.readValue(json, cls);
			} catch (Exception e) {
				log.error("[JackSonUtil.jsonToObject]-- ERROR!" + json + " " + e.getMessage());
				return null;
			} finally {
			}
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public static List jsonToList(String json, Class cls){
		if(StringUtils.isNotEmpty(json)){
			try {
				//允许json串里面的key value不带双引号
				mapper.configure(org.codehaus.jackson.JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
				// 允许制定的object中的属性没有json串中某个key
				mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);

				return (List) mapper.readValue(json, TypeFactory.collectionType(ArrayList.class,cls));
			} catch (Exception e) {
				log.error("[JackSonUtil.jsonToList]-- ERROR!" + json + " " + e.getMessage());
				return null;
			} finally {
			}
		}

		return null;
	}


	public static String getJson(Object object) {
		String json = null;

		if (object != null) {
			StringWriter sw = new StringWriter();
			try {
				JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
				mapper.writeValue(gen, object);
				gen.close();

				json = sw.toString();
			} catch (Exception e) {
				log.error("JackSonUtil getJson error!" + e.getMessage());
			} finally {
			}
		}

		return json;
	}

	/**
	 * to String List
	 * @param objs
	 * @return
	 */
	public static List<String> toJsonList(List<?> objs) {
		List<String> jsonList = new ArrayList<String>();
		if(objs == null) {
			return null;
		}
		for(Object o : objs) {
			jsonList.add(getJson(o));
		}
		return jsonList;
	}

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("ww","aa,bb,cc");
		map.put("yy","dd,ee,ff");
//        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		Map<String, String> bigmap = new HashMap<String, String>();
		bigmap.put("user", "12354");
//        bigmap.put("comment", list);

		String json = getJson(map);
		bigmap.put("comment", json);

		String jsons = getJson(bigmap);

		System.out.println(jsons);

		//Map<String, String> obj =  (Map<String, String>)jsonToObject("aaa", Map.class);
		//System.out.println(obj.get("ww"));
		//System.out.println("aabbcc");
	}
}

