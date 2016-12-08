/**
 * 
 */
package com.ruzhi.demo.gaode;

import java.io.Serializable;

/**
 * @author longjia.zt
 *
 */
public class Regeocode  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String formatted_address;
	private AddressComponent addressComponent;

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(Object formatted_address) {
		if(formatted_address instanceof String){
			this.formatted_address = formatted_address.toString();	
		}else{
			this.formatted_address = "";
		}
	}

	public AddressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(AddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}
}
