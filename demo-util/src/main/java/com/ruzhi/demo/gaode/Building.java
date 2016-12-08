package com.ruzhi.demo.gaode;

import java.io.Serializable;

public class Building implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;

	public String getName() {
		return name;
	}

	public void setName(Object name) {
		Object temp=name;
		if(temp instanceof String){
			this.name = temp.toString();	
		}else{
			this.name = "";
		}
	}

	public String getType() {
		return type;
	}

	public void setType(Object type) {
		Object temp=type;
		if(temp instanceof String){
			this.type = temp.toString();	
		}else{
			this.type = "";
		}
	}
}
