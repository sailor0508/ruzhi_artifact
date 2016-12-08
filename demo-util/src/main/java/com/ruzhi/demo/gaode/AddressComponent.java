/**
 * 
 */
package com.ruzhi.demo.gaode;

import java.io.Serializable;

/**
 * @author longjia.zt
 *
 */
public class AddressComponent implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String province;
	private String city;
	private String citycode;
	private String district;
	private String adcode;
	private String township;
	private Neighborhood neighborhood;
	private Building building;
	private StreetNumber streetNumber;
	public String getProvince() {
		return province;
	}
	public void setProvince(Object province) {
		Object temp=province;
		if(temp instanceof String){
			this.province = temp.toString();	
		}else{
			this.province = "";
		}
 	}
	public String getCity() {
		return city;
	}
	public void setCity(Object city) {
		Object temp=city;
		if(temp instanceof String){
			this.city = temp.toString();	
		}else{
			this.city = "";
		}
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(Object citycode) {
		Object temp=citycode;
		if(temp instanceof String){
			this.citycode = temp.toString();	
		}else{
			this.citycode = "";
		}
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(Object district) {
		Object temp=district;
		if(temp instanceof String){
			this.district = temp.toString();	
		}else{
			this.district = "";
		}
	}
	public String getAdcode() {
		return adcode;
	}
	public void setAdcode(Object adcode) {
		Object temp=adcode;
		if(temp instanceof String){
			this.adcode = temp.toString();	
		}else{
			this.adcode = "";
		}
	}
	public String getTownship() {
		return township;
	}
	public void setTownship(Object township) {
		Object temp=township;
		if(temp instanceof String){
			this.township = temp.toString();	
		}else{
			this.township = "";
		}
	}
	public Neighborhood getNeighborhood() {
		return neighborhood;
	}
	public void setNeighborhood(Neighborhood neighborhood) {
		this.neighborhood = neighborhood;
	}
	public Building getBuilding() {
		return building;
	}
	public void setBuilding(Building building) {
		this.building = building;
	}
	public StreetNumber getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(StreetNumber streetNumber) {
		this.streetNumber = streetNumber;
	}
}
