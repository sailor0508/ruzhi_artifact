package com.ruzhi.demo.lifeserverweb;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 人民币单位转换   分->元   元->分
 * 分用long，元用double
 * @author yurui.wzw
 *
 */
public class MUtil {

	/**
	 * 将分转换成元
	 * @param cent
	 * @return
	 */
	public static BigDecimal c2y_big(Long cent) {
		if (cent == null)
			return null;

		return new BigDecimal(cent).movePointLeft(2);
	}
	/**
	 * 将分转换成元
	 * @param cent
	 * @return
	 */
	public static String c2y_str(Long cent) {
		BigDecimal bd = c2y_big(cent);
		if(bd != null) {
			return bd.toString();
		}
		return "";
	}

	/**
	 *
	 * @param cent
	 * @param precision 精度  小数点后保留多少位，= -1 小数点后抹掉最后的0,  大于等于2 == 2，四舍五入处理
	 * @return
	 */
	public static String c2y(Long cent, int precision) {
		if (cent == null) {
			cent = new Long(0L);
		}
		String patt = "0.00";
		if(-1 == precision) {
			if(cent%10 == 0) {
				patt = "0.0";
			}if(cent%100 == 0) {
				patt = "0";
			}
			DecimalFormat df = new DecimalFormat(patt);
			return df.format(cent/100.0);
		}
		if(1 == precision) {
			patt = "0.0";
		}else if(0 == precision) {
			patt = "0";
		}
		DecimalFormat df = new DecimalFormat(patt);
		return df.format(cent/100.0);
	}
	/**
	 * 将分转换成元
	 * @param cent
	 * @return
	 */
	public static double c2y(Long cent) {
		BigDecimal bd = c2y_big(cent);
		if(bd != null) {
			return bd.doubleValue();
		}
		return 0.00;
	}

	/**
	 * 由元转换成分
	 * @param yuan
	 * @return
	 */
    /*public static long y2c(double yuan) {
        return new Money(yuan).getCent();
    }*/

	/**
	 * BigDecimal 转 long类型
	 * @param bd
	 * @return
	 */
	public static long y2c(BigDecimal bd) {
		if(bd == null) {
			return 0;
		}
		long dv = new BigDecimal(bd.doubleValue()*100).longValue();
		return dv;
	}

	/**
	 * 分to元 强制保留两位小数
	 *
	 * */
	public static String c2y00(Long cent) {
		double c2y = c2y(cent);
		String ro = c2y+"";
		String ro2 = ro.replace(".", "-");
		String[] split = ro2.split("-");
		String l = split[1];
		if(l == null){
			return ro;
		}
		if(l.length() == 1){
			ro += "0";
		}
		return ro;
	}

    /*public static void main(String[] args) {
		System.out.println(y2c(11L));
		System.out.println(c2y_str(11L));
		System.out.println(c2y(null));
		//System.out.println(convertToCent(10.2353253));
		//BigDecimal bd = new BigDecimal(1L);
		System.out.println(c2y(5L));
	}*/
}
