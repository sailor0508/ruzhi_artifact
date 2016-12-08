/**
 *
 */
package com.ruzhi.demo.baidu;

import com.ruzhi.demo.common.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author longjia.zt
 *
 */
public class BaiduResponseDO implements Serializable {
	public static final Logger logger                   = LoggerFactory.getLogger(BaiduResponseDO.class);
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private int status;
	private ResponseResult result;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public ResponseResult getResult() {
		return result;
	}
	public void setResult(ResponseResult result) {
		this.result = result;
	}

	/**
	 * 是百度的城市ID 不是淘宝的 需要转换
	 * @return
	 */
	public int getTaoBaoCityId(){
		if(result==null){
			return 0;
		}
		int cityCode = 0;//result.getCityCode();
		if(cityCode<=0){
			return cityCode;
		}
		Integer v = null;//GeocodingService.baiduCity2GaoDe.get(cityCode);
		if(v==null){
			logger.warn("code no Gao de city:"+cityCode);
			return 0;
		}
		return v;
	}
}
