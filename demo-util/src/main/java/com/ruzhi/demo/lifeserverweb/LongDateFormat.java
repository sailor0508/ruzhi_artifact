package com.ruzhi.demo.lifeserverweb;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.util.Date;

/**
 * 用于jackson的日期类型转long类型 类非public，只能在本包使用避免污染其他代码
 * @author huakang.hk
 *
 */
class LongDateFormat extends DateFormat {

	/**
	 *
	 */
	private static final long serialVersionUID = -6809042807448786853L;

	@Override
	public StringBuffer format(Date date, StringBuffer toAppendTo,
							   FieldPosition fieldPosition) {
		return new StringBuffer().append(date.getTime());
	}

	@Override
	public Date parse(String source, ParsePosition pos) {
		long time = Long.valueOf(source);
		pos.setIndex(source.length());
		return new Date(time);
	}

}
