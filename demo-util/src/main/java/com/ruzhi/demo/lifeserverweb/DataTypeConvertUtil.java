package com.ruzhi.demo.lifeserverweb;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class DataTypeConvertUtil {

	private static Logger log = LoggerFactory.getLogger(DataTypeConvertUtil.class);
	/**
	 * 四舍五入后取整数
	 * @param itemScore
	 * @return
	 */
	public static int itemScore2Int(String itemScore){
		if (StringUtils.isEmpty(itemScore)) {
			return 0;
		}
		double   f   =   Double.valueOf(itemScore);
		BigDecimal   b   =   new   BigDecimal(f);
		int   f1   =   b.setScale(0,   BigDecimal.ROUND_HALF_UP).intValue();
		return f1;
	}
	/**
	 * 最多保留两位有效小数
	 * @param d
	 * @return
	 */
	public static double roundingTwoPoint(double d){
		double rD=0;
		try {
			DecimalFormat df = new DecimalFormat(".##");
			String st = df.format(d);
			if (st.endsWith(".0")) {
				rD = Double.valueOf(st.replace(".0", ""));
			}else{
				rD=Double.valueOf(st);
			}
		} catch (Exception e) {
			log.error("DataTypeConvertUtil.roundingTwoPoint", e);
		}
		return rD;
	}
}
