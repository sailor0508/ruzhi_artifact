package com.ruzhi.demo.util;

import net.sf.json.JSONObject;

public class PosxyUtil extends BaseHttpConnection {

	//系数
	protected static final int coefficient = 100000;
	//阿里云接口 根据坐标得到区域id
	protected static final String ALIYUN_AREACODE_URL = "http://recode.ditu.aliyun.com/dist_query?f=0&l=";

	public static double convert(int posxy) {
		return new Double(posxy) / coefficient;
	}
	public static int convert(double posxy) {
		return new Double(posxy * coefficient).intValue();
	}

	/**
	 * 根据经纬度 计算区域id
	 * @param posx
	 * @param posy
	 * @return
	 */
	public static int getAreaCode(int posx,int posy) {
		if(posx <= 0 || posy <= 0)
			return 0;
		String requestURL = ALIYUN_AREACODE_URL;
		requestURL += convert(posy) + "," + convert(posx);
		String resultStr = executeURL(requestURL,"gbk");
		JSONObject resobj = JsonUtils.toBean(resultStr);
		if(resobj != null && resobj.containsKey("ad_code")) {
			return resobj.optInt("ad_code");
		}
		return 0;
	}

	/**
	 * 根据经纬度计算城市id
	 * @param posx
	 * @param posy
	 * @return
	 */
	public static int getCityId(double posx, double posy) {
		if(posx <= 0 || posy <= 0)
			return 0;
		String requestURL = ALIYUN_AREACODE_URL;
		requestURL += posy + "," + posx;
		String resultStr = executeURL(requestURL, "gbk");
		JSONObject resobj = JsonUtils.toBean(resultStr);
		if(resobj != null && resobj.containsKey("ad_code")) {
			int areacode = resobj.optInt("ad_code");
			return areacode/100*100;
		}

		return 0;
	}

}
