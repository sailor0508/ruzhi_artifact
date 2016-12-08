package com.ruzhi.demo.common;


import com.ruzhi.demo.exceptions.NoNullFieldStringStyle;
import com.ruzhi.demo.json.CustomDateDeserialize;
import com.ruzhi.demo.json.CustomDateSerializer;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;
import java.util.Date;

public class BaseVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date gmtCreate ;

	@JsonDeserialize(using = CustomDateDeserialize.class)
	@JsonSerialize(using = CustomDateSerializer.class)
	private Date gmtModified ;


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
	 * @author lengda
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, new NoNullFieldStringStyle());
	}

}


