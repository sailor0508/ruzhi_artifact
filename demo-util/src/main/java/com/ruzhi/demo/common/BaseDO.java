package com.ruzhi.demo.common;


import com.ruzhi.demo.exceptions.NoNullFieldStringStyle;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

public class BaseDO implements Serializable {


	private static final long serialVersionUID = 1L;

	private Date gmtCreate;
	private Date gmtModified;

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(Date gmtModified) {
		this.gmtModified = gmtModified;
	}

	/**
	 * 如果字段值为null将不包含在toString中
	 */
	@Override
	public String toString() {
		return (new ReflectionToStringBuilder(this,new NoNullFieldStringStyle()) {
			// 注意这里为了表达上的简洁用了匿名内部类.
			protected boolean accept(Field f) {
				return super.accept(f) && !f.getName().equals("passWord");
			}
		}.toString());
	}
}



