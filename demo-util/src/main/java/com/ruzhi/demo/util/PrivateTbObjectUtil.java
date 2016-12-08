package com.ruzhi.demo.util;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * there is a Package conflicts when the TBStringUtil class be used,so rewrite it
 */
public class PrivateTbObjectUtil {

	public static long getLong(Object s) {
		long res = 0L;

		if(s == null){
			return res;
		}else{
			try {
				String ss = String.valueOf(s);
				res = Long.parseLong(ss);
			} catch (Exception e) {
				return res;
			}
		}
		return res;
	}

	public static int getInt(Object s) {
		int res = 0;
		if(s == null){
			return res;
		}else{
			try {
				String ss = String.valueOf(s);
				res = Integer.parseInt(ss);;
			} catch (Exception e) {
				return res;
			}
		}
		return res;
	}

	public static String getString(Object s) {
		String  res = "";
		if(s == null){
			return res;
		}else{
			try {
				res = String.valueOf(s);
			} catch (Exception e) {
				return res;
			}
		}
		return res;
	}
	public static Date getDate(Object s) {
		Date res = null;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(s == null){
			return res;
		}else{
			try {
				String ss = String.valueOf(s);
				res = format.parse(ss);
			} catch (Exception e) {
				return res;
			}
		}
		return res;

	}
	public static Map<String, String> getMap(Object s) {
		Map<String, String> res = null;
		if(s == null){
			return res;
		}else{
			try {
				String ss = String.valueOf(s);
				res = parseJSON2MapString(ss);
			} catch (Exception e) {
				return res;
			}
		}
		return res;
	}

	public static Map<String, String> parseJSON2MapString(String jsonStr){
		Map<String, String> map = new HashMap<String, String>();

		JSONObject json = JSONObject.fromObject(jsonStr);
		if(json != null){
			for(Object k : json.keySet()){
				if(json.get(k) != null){
					String v = String.valueOf(json.get(k));
					map.put(k.toString(), v);
				}
			}
		}
		return map;
	}

	/*
	 *是方法public static Map<String, String> parseJSON2MapString(String jsonStr)的扩展性版本
	 */
	public static Map<String, Object> parseJSON2MapObject(String jsonStr){
		Map<String, Object> map = new HashMap<String, Object>();
		//最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for(Object k : json.keySet()){
			Object v = json.get(k);
			//如果内层还是数组的话，继续解析
			if(v instanceof JSONArray){
				List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
				Iterator<JSONObject> it = ((JSONArray)v).iterator();
				while(it.hasNext()){
					JSONObject json2 = it.next();
					list.add(parseJSON2MapObject(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

}