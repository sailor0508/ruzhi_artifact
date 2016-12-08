package com.ruzhi.demo.common;

import com.ruzhi.demo.exceptions.NoNullFieldStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Posxy {

	public Posxy(int posx, int posy) {
		super();
		this.posx = posx;
		this.posy = posy;
	}
	private int posx;
	private int posy;
	public int getPosx() {
		return posx;
	}
	public void setPosx(int posx) {
		this.posx = posx;
	}
	public int getPosy() {
		return posy;
	}
	public void setPosy(int posy) {
		this.posy = posy;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, new NoNullFieldStringStyle());
	}
}
