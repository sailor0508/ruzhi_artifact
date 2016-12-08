package com.ruzhi.demo.common;

import com.ruzhi.demo.util.JsonUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URLEncoder;

public class PosxyUtil extends BaseHttpConnection {

	private static Logger log = LoggerFactory.getLogger(PosxyUtil.class);
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
	 * 根据地址信息获得经纬度
	 * @param address
	 * @return
	 */
	private static JSONObject getPos(String address) {
		JSONObject resobj = null;
		String result = null;
		String GeocodingURL = "http://api.map.baidu.com/geocoder?output=json&key=182f2518118886968763c647ed8b917e&address=";
		try {
			GeocodingURL += URLEncoder.encode(address,"UTF-8");
			result = executeURL(GeocodingURL,null);
			resobj = JsonUtils.toBean(result);
		}catch(Exception e) {
			log.error("PosxyUtil.getPos", e) ;
		}

		return resobj;
	}





	/**
	 * gps坐标转阿里云坐标
	 * @param x
	 * @param y
	 * @return
	 */
	protected static JSONObject gps2Mars(double x, double y) {
		JSONObject resobj = null;
		if(x <= 10 || y <= 10)
			return resobj;
		String url = "http://loc1.ditu.aliyun.com/getlocation.html?action=getLocationByGps&posx=";
		try {
			url += x + "&posy=" + y;
			String result = executeURL(url,null);
			resobj = JsonUtils.toBean(result);
		}catch(Exception e) {
			log.error("PosxyUtil.gps2Mars", e) ;
		}
		return resobj;
	}

	/**
	 * 百度坐标转阿里云坐标
	 * @param posx 千万
	 * @param posy 百万
	 * @return
	 */
	public static Posxy baiduPos2AliyunPos(int posx,int posy) {

		double lng = convert(posx);
		double lat = convert(posy);

		String url = "http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x=";
		url += lng + "&y=" + lat;
		JSONObject resobj = null;
		try {
			String result = executeURL(url,null);
			resobj = JsonUtils.toBean(result);
			if(null == resobj || resobj.getInt("error") != 0)
				return null;
			double x1 = Double.parseDouble(Base64.decode(resobj.optString("x")));
			double y1 = Double.parseDouble(Base64.decode(resobj.optString("y")));
			x1 = 2*lng - x1;
			y1 = 2*lat - y1;
			JSONObject marsObj = gps2Mars(x1, y1);
			// 请求可能返回错误信息，转码失败
			if(null == marsObj || !marsObj.optString("result").equalsIgnoreCase("ok"))
				return null;
			x1 = marsObj.optDouble("offsetPosx");
			y1 = marsObj.optDouble("offsetPosy");

			return new Posxy(convert(x1),convert(y1));
		}catch(Exception e) {
			log.error("PosxyUtil.baiduPos2AliyunPos", e) ;
			return null;
		}
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
		requestURL += convert(posy)+","+convert(posx);
		String resultStr = executeURL(requestURL,"gbk");
		JSONObject resobj = JsonUtils.toBean(resultStr);
		if(resobj != null && resobj.containsKey("ad_code")) {
			return resobj.optInt("ad_code");
		}
		//log.info("resultStr is :"+resultStr+"; resobj is :"+resobj.toString());
		return 0;
	}

	public static int getCity(int posx,int posy) {
		int areaCode = getAreaCode(posx, posy);
		if(areaCode > 0) {
			/*CityArea _cityArea = ServiceProvider.getCityAreaService().getEntity(areaCode);
			if(_cityArea != null) {
				return _cityArea.getId();
			}*/
		}
		return 0;
	}

	public static int getCity2(int posx,int posy) {
		int areaCode = getAreaCode(posx, posy);
		log.info("areaCode is: "+areaCode);
		return areaCode;
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

	/**
	 * 根据用户的中心位置和商户的中心位置计算距离，单位（米）
	 * @param usercen_x
	 * @param usercen_y
	 * @param storeposx
	 * @param storeposy
	 * @return
	 */
	public static double getDistance(int usercen_x, int usercen_y,int storeposx, int storeposy) {
		double distance = Double.MAX_VALUE;
		if (storeposx <1 || usercen_x<1) {
			return distance;
		}

		try {
			if (storeposx > 0 && storeposy > 0) {
				if (usercen_x > 0) {
					long squre = (long) Math.abs(storeposx - usercen_x)
							* Math.abs(storeposx - usercen_x)
							+ (long)Math.abs(storeposy - usercen_y)
							* Math.abs(storeposy - usercen_y);
					distance = Math.sqrt(squre);
				}
			}
		} catch (Exception ex) {
			log.error("DistanceUtil getMyDistance error"+"user posx:"+usercen_x+"user posy:"+usercen_y+"store posx:"+storeposx+"store posy:"+storeposy,ex);
		}
		return distance;
	}
	public static double getDistance(double usercen_x, double usercen_y,double storeposx, double storeposy) {
		return getDistance(convert(usercen_x), convert(usercen_y), convert(storeposx), convert(storeposy));
	}


	public static void main(String[] args) {
		//String address = "杭州市华星路99号淘宝小邮局";
		//Posxy obj = baiduPos2AliyunPos(11642000,4000950);
		//System.out.println(obj.toString());

		System.out.println(getAreaCode(12148500, 3125860));

		/*GeocoderDO obj = getGeoDO(address);
		System.out.println(obj.toString());*/

	}
}
