package com.ruzhi.demo.gaode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

;

/**
 *
 * @author longjia.zt
 *
 */
public class GeocodingService {
	public static final Logger logger                   = LoggerFactory.getLogger(GeocodingService.class);
	public static final Logger    poiLog                   = LoggerFactory.getLogger("poiLog");

	private static final int EXPIRE = 1800;
	public static int num;//计算器，用来循环百度的key
	public String gaoDeURL = "http://restapi.amap.com/v3/geocode/regeo?output=json&key=0113a13c88697dcea6a445584d535837&location=%s,%s";
	public String baiduURL = "http://api.map.baidu.com/geocoder/v2/?coordtype=gcj02ll&ak=%s&location=%s,%s&output=json&pois=0";
	public static  Map<Integer,Integer> baiduCity2GaoDe=new HashMap<Integer,Integer>(500);
	public void init(){
		try{
			InputStream resource = GeocodingService.class.getClassLoader().getResourceAsStream("ids.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(resource));
			String line=br.readLine();
			while(line!=null){
				String[] lineWords = line.split(":");
				baiduCity2GaoDe.put(Integer.parseInt(lineWords[0]), Integer.parseInt(lineWords[1]));
				line=br.readLine();
			}
			logger.warn("初始化数据成功，总共初始化数据,size="+baiduCity2GaoDe.size());
		}catch (Exception e) {
			logger.error("======加载文件失败===================：", e);
			System.exit(1);
		}
	}
/*
    *//**
	 * 获取数据
	 * @param x
	 * @param y
	 * @param clientVersionDO
	 * @return
	 *//*
	public LifeServeResultDO<ReversedDeocodDO> getInfoFromPoiForLocateCity(String x, String y, ClientVersionDO clientVersionDO) {
		LifeServeResultDO<ReversedDeocodDO> result=new LifeServeResultDO<ReversedDeocodDO>(true,"");
		ReversedDeocodDO reversedDeocodDO=new ReversedDeocodDO();
		try{
		if(StringUtils..isEmpty(x)||StringUtils..isEmpty(y)||Double.parseDouble(x)<=1||Double.parseDouble(y)<=1){
			result.setSuccess(false);
			result.setErrorMsg("坐标获取失败，请重试。");
			poiLog.warn(String.format("paraHasNotSuccess：x=%s,y=%s,    clientVersionDO=%s", x,y,(clientVersionDO==null?"":clientVersionDO.toString())));
			return result;
		}
		reversedDeocodDO.setX(x);
		reversedDeocodDO.setY(y);

		if(DiamondDateConfig.userGaoDeLocation){
		getResultFromGaoDe(result,reversedDeocodDO);
		if(result.isSuccess()&&result.getResultDO()!=null&&result.getResultDO().getCity()>0){
			putDeocodDO2Tair(reversedDeocodDO,clientVersionDO);
			return result ;
		}
		poiLog.warn(String.format("requestGaoDeFail：x=%s,y=%s,    clientVersionDO=%s，result=%s", x,y,(clientVersionDO==null?"":clientVersionDO.toString()),result));

        }

		if(DiamondDateConfig.useBaiDuLocation){
		getResultFromBaidu(result,reversedDeocodDO);
		if(result.isSuccess()&&result.getResultDO()!=null&&result.getResultDO().getCity()>0){
			putDeocodDO2Tair(reversedDeocodDO,clientVersionDO);
			return result ;
		}
		poiLog.warn(String.format("requestBaiduFail：x=%s,y=%s,    clientVersionDO=%s，result=%s", x,y,(clientVersionDO==null?"":clientVersionDO.toString()),result));
		}

		if(DiamondDateConfig.locationUseCache){
		//从cache获取最后的地址
		getResultFromCache(result,reversedDeocodDO,clientVersionDO);
		  }

		}catch (Exception e) {
			result.setSuccess(false);
			logger.error(String.format("参数异常：x=%s,y=%s,clientVersionDO=%s", x,y,(clientVersionDO==null?"":clientVersionDO.toString())),e);

		}
		return result;
	}

	private void putDeocodDO2Tair(ReversedDeocodDO reversedDeocodDO,ClientVersionDO clientVersionDO) {
		if(clientVersionDO==null){
			return ;
		}
		if(!reversedDeocodDO.isHasAllRequire()){
			return ;
		}
		deviceLocationCache.put(clientVersionDO.getDeviceid(), reversedDeocodDO,EXPIRE);
	}

	private void getResultFromCache(LifeServeResultDO<ReversedDeocodDO> result,ReversedDeocodDO reversedDeocodDO, ClientVersionDO clientVersionDO) {
 		if(clientVersionDO!=null&&StringUtils.isNotBlank(clientVersionDO.getDeviceid())){
			reversedDeocodDO = (ReversedDeocodDO) deviceLocationCache.get(clientVersionDO.getDeviceid());
		}else{
 			poiLog.warn(String.format("request cache Fail=%s,y=%s,    clientVersionDO=%s，result=%s", reversedDeocodDO.getX(),reversedDeocodDO.getY(),(clientVersionDO==null?"":clientVersionDO.toString()),result));
			result.setSuccess(false);
			result.setErrorMsg("缓存中获取用户地址失败。");
			return ;
		}
 		if(reversedDeocodDO!=null){
 			poiLog.warn(String.format("request cache Success：x=%s,y=%s,    clientVersionDO=%s，result=%s", reversedDeocodDO.getX(),reversedDeocodDO.getY(),(clientVersionDO==null?"":clientVersionDO.toString()),result));
 			reversedDeocodDO.setCacheData(true);
 			result.setSuccess(true);
 			result.setResultDO(reversedDeocodDO);
 		}
	}

	private void getResultFromBaidu(LifeServeResultDO<ReversedDeocodDO> result,ReversedDeocodDO reversedDeocodDO) {
		try{

			String url=String.format(baiduURL,DiamondDateConfig.baiduKey.get(getKeyIndex()),reversedDeocodDO.getY(), reversedDeocodDO.getX());
            String json =null;
            if(DiamondDateConfig.useCityOptimize){
                json =HttpUtil.getStringFromHttp(url,"UTF-8");
            }else {
                json = WebUtil.getWebContentWithTimeout(url, "UTF-8", 200, 800);
            }

	    	if(StringUtils.isEmpty(json)){
                logger.info("BaiDuNoResult。。。。。。:");
	    		result.setSuccess(false);
	    		result.setErrorMsg("读取百度返回没有结果。");
	    		return ;
	    	}
	    	 JSONObject jsonObject = JSONObject.parseObject(json);
	    	if(!"0".equals(jsonObject.get("status")+"")){
                logger.error("amap fail,info:"+jsonObject.get("info")+",url:"+url);
                result.setErrorMsg("查询地址失败");
                return ;
	         }

	    	BaiduResponseDO baiduResponseDO = JSONObject.toJavaObject(jsonObject, BaiduResponseDO.class);
	    	reversedDeocodDO.setAddress(baiduResponseDO.getFormatAddr());
	    	setCityAndName(reversedDeocodDO,baiduResponseDO);
	    	result.setResultDO(reversedDeocodDO);
	    	result.setSuccess(true);
		}catch (Exception e) {
			result.setSuccess(false);
			result.setErrorMsg("查询异常");
			logger.error("百度逆地址解析失败：", e);
		}


	}

	private void setCityAndName(ReversedDeocodDO reversedDeocodDO,BaiduResponseDO baiduResponseDO) {
		int cityCode = baiduResponseDO.getTaoBaoCityId();
		if(cityCode<=0){
			//说明不存在城市编码或者城市编码不对
			if(setCityByName(reversedDeocodDO, baiduResponseDO.getCity())){
				return ;
			}
			return ;
		}

		boolean isCity=chinaDivisionManager.checkCityDivision(cityCode);
		if(isCity){
			DivisionVO division = chinaDivisionManager.getDivisionById(cityCode);
			reversedDeocodDO.setCity(division.getDivisionId());
			reversedDeocodDO.setCityName(division.getDivisionAbbName());
			reversedDeocodDO.setCityNamePinyin(HanZi2PinYinUtil.getPinYin(division.getDivisionAbbName()));
			return ;
		}

		if(setCityByName(reversedDeocodDO, baiduResponseDO.getCity())){
			return ;
		}
	}

	private void getResultFromGaoDe(LifeServeResultDO<ReversedDeocodDO> result,ReversedDeocodDO reversedDeocodDO) {
		try{

            String url=String.format(gaoDeURL, "120.123","30.123");
	    	String json=null;
            if(DiamondDateConfig.useCityOptimize){
                json = HttpUtil.getStringFromHttp(url, "UTF-8");
            }else {
                json = WebUtil.getWebContentWithTimeout(url, "UTF-8", 200, 800);
            }
	    	if(StringUtils.isEmpty(json)){
	    		result.setSuccess(false);
	    		result.setErrorMsg("读取高德返回没有结果。");
                logger.info("gaodeNoResult。。。。。。:");
	    		return ;
	    	}
	    	 JSONObject jsonObject = JSONObject.parseObject(json);
	    	if(!"1".equals(jsonObject.get("status"))){
                logger.error("amap fail,info:"+jsonObject.get("info")+",url:"+url);
                result.setSuccess(false);
                result.setErrorMsg("查询地址失败");
                return ;
	         }

	    	AnavResponseDO anavResponseDO = JSONObject.toJavaObject(jsonObject, AnavResponseDO.class);
	    	reversedDeocodDO.setAddress(getAddress(anavResponseDO));
	    	setCityAndName(reversedDeocodDO,anavResponseDO);
	    	result.setResultDO(reversedDeocodDO);
		}catch (Exception e) {
			result.setSuccess(false);
			result.setErrorMsg("查询异常");
			logger.error("高德逆地址解析失败：", e);
		}
	}

	private String getAddress(AnavResponseDO anavResponseDO) {
	  String streetNumberString=anavResponseDO.getStreetNumberString();
      String address;
	  if(StringUtils.isEmpty(streetNumberString)){
      	address=anavResponseDO.getAddress();//获取formatAddress
      }else{
      	String proveFre =anavResponseDO.getProv()+anavResponseDO.getCity()+anavResponseDO.getDistrict();//获取省市区前缀
      	address=proveFre+streetNumberString;
      }
		return address;
	}

	private void setCityAndName(ReversedDeocodDO reversedDeocodDO,AnavResponseDO anavResponseDO) {
		String adCode = anavResponseDO.getAdcode();
		if(StringUtils.isEmpty(adCode)||!StringUtils.isNumeric(adCode)){
			return ;
		}
		boolean isAdCode = chinaDivisionManager.checkRegionDivision(Long.parseLong(adCode));
		if(isAdCode){
			DivisionVO division = chinaDivisionManager.getDivisionById(Long.parseLong(adCode));
			if(division!=null){
				DivisionVO parent = division.getParentDivision();
				reversedDeocodDO.setCity((int)parent.getDivisionId());
				reversedDeocodDO.setCityName(parent.getDivisionAbbName());
				reversedDeocodDO.setCityNamePinyin(HanZi2PinYinUtil.getPinYin(parent.getDivisionAbbName()));
				return ;
			}
		}

		boolean isCityCode=chinaDivisionManager.checkCityDivision(Long.parseLong(adCode));
		if(isCityCode){
			DivisionVO division = chinaDivisionManager.getDivisionById(Long.parseLong(adCode));
			if(division!=null){
				reversedDeocodDO.setCity((int)division.getDivisionId());
				reversedDeocodDO.setCityName(division.getDivisionAbbName());
				reversedDeocodDO.setCityNamePinyin(HanZi2PinYinUtil.getPinYin(division.getDivisionAbbName()));
				return ;
			}
		}
		//如果城市编码有问题
		if(setCityByName(reversedDeocodDO, anavResponseDO.getCity())){
			return ;
		}

		logger.warn("geocodeing 获取不到区域ID=======reversedDeocodDO="+reversedDeocodDO);

	}

	private boolean setCityByName(ReversedDeocodDO reversedDeocodDO,String city) {
		if(StringUtils.isEmpty(city)){
			return false;
		}
		List<DivisionVO> citys = chinaDivisionManager.getDivisionByName(city);
		if(!CollectionUtils.isEmpty(citys)){
			for(DivisionVO divisionVO:citys){
				if(chinaDivisionManager.checkCityDivision(divisionVO.getDivisionId())){
					reversedDeocodDO.setCity((int)divisionVO.getDivisionId());
					reversedDeocodDO.setCityName(divisionVO.getDivisionAbbName());
					reversedDeocodDO.setCityNamePinyin(HanZi2PinYinUtil.getPinYin(divisionVO.getDivisionAbbName()));
					return true;
				}
				//如果是区域
				if(chinaDivisionManager.checkRegionDivision(divisionVO.getDivisionId())){
					DivisionVO division = chinaDivisionManager.getDivisionById(divisionVO.getDivisionId());
					if(division!=null){
						DivisionVO parent = division.getParentDivision();
						reversedDeocodDO.setCity((int)parent.getDivisionId());
						reversedDeocodDO.setCityName(parent.getDivisionAbbName());
						reversedDeocodDO.setCityNamePinyin(HanZi2PinYinUtil.getPinYin(parent.getDivisionAbbName()));
						return true;
					}
				}
			}
		}
		return false;
	}

	*//**
	 * 获取编号
	 * @return
	 *//*
	public static int getKeyIndex(){
		if(num>=DiamondDateConfig.baiduKey.size()){
			num=0;
		}
		int index=num%DiamondDateConfig.baiduKey.size();
		num++;
		return index;

	}*/
}
