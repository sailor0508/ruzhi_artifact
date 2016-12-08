package com.ruzhi.demo.common;


import org.apache.commons.lang.StringUtils;

public class ParamCheckUtil {

	/**
	 * 归一化
	 * @param paramStr
	 * @return
	 */
	public static String doNormalize(String paramStr){
		if(StringUtils.isBlank(paramStr)) {
			return "";
		}
		String _paramStr = paramStr.trim();

		_paramStr = FullToHalf(_paramStr);

		if(_paramStr.equalsIgnoreCase("null")) {
			return "";
		}
		return _paramStr;
	}

	/**
	 * 全角转半角
	 * @param Halfstr
	 * @return
	 */
	public static String FullToHalf(String Fullstr){
		char c[] = Fullstr.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] == '\u3000') {
				c[i] = ' ';
			} else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
				c[i] = (char) (c[i] - 65248);
			}
		}
		String returnString = new String(c);
		return returnString;
	}

	public static void main(String[] args) {
		String str = "眉州东坡酒楼（大兴绿地店）";
		System.out.println(doNormalize(str));
	}

}
