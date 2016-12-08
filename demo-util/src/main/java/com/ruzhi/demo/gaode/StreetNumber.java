package com.ruzhi.demo.gaode;

import java.io.Serializable;

public class StreetNumber implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String street;
	private String number;
	private String location;
	private String direction;
	private String distance;
	public String getStreet() {
		return street;
	}
	public void setStreet(Object street) {
		Object temp=street;
		if(temp instanceof String){
			this.street = temp.toString();	
		}else{
			this.street = "";
		}
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(Object number) {
		Object temp=number;
		if(temp instanceof String){
			this.number = temp.toString();	
		}else{
			this.number = "";
		}
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(Object location) {
		Object temp=location;
		if(temp instanceof String){
			this.location = temp.toString();	
		}else{
			this.location = "";
		}
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(Object direction) {
		Object temp=direction;
		if(temp instanceof String){
			this.direction = temp.toString();	
		}else{
			this.direction = "";
		}
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(Object distance) {
		Object temp=distance;
		if(temp instanceof String){
			this.distance = temp.toString();	
		}else{
			this.distance = "";
		}
	}
}
