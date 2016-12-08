/**
 *
 */
package com.ruzhi.demo.gaode;

import com.ruzhi.demo.common.BaseDO;
import org.apache.commons.lang.StringUtils;

/**
 * @author longjia.zt
 *  高德地图逆转地址返回对象
 */
public class AnavResponseDO extends BaseDO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String info;
	private Regeocode regeocode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Regeocode getRegeocode() {
		return regeocode;
	}

	public void setRegeocode(Regeocode regeocode) {
		this.regeocode = regeocode;
	}


	public String getAdcode(){
		if(regeocode==null){
			return "";
		}
		AddressComponent addressComponent = regeocode.getAddressComponent();
		if(addressComponent==null){
			return "";
		}
		return addressComponent.getAdcode();
	}

	public String getStreetNumberString(){
		if(regeocode==null){
			return "";
		}
		AddressComponent addressComponent = regeocode.getAddressComponent();
		if(addressComponent==null){
			return "";
		}
		StreetNumber streetNumber = addressComponent.getStreetNumber();
		if(streetNumber==null){
			return "";
		}
		String street=streetNumber.getStreet();
		String number=streetNumber.getNumber();
		if(StringUtils.isEmpty(street)||StringUtils.isEmpty(number)||!StringUtils.isNumeric(number)){
			return "";
		}
		return street+number+"号";
	}

	public String getAddress(){
		if(regeocode==null){
			return "";
		}
		return regeocode.getFormatted_address();
	}

	public String getProv(){
		if(regeocode==null){
			return "";
		}
		AddressComponent addressComponent = regeocode.getAddressComponent();
		if(addressComponent==null){
			return "";
		}
		return addressComponent.getProvince();
	}

	public String getCity(){
		if(regeocode==null){
			return "";
		}
		AddressComponent addressComponent = regeocode.getAddressComponent();
		if(addressComponent==null){
			return "";
		}
		return addressComponent.getCity();
	}

	public String getDistrict(){
		if(regeocode==null){
			return "";
		}
		AddressComponent addressComponent = regeocode.getAddressComponent();
		if(addressComponent==null){
			return "";
		}
		return addressComponent.getDistrict();
	}
}
