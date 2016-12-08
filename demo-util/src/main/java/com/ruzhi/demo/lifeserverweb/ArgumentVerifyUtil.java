package com.ruzhi.demo.lifeserverweb;

import org.slf4j.Logger;

/**
 *
 * @description:参数校验
 * @author 展醉
 * @email zhanzui.ldh@taobao.com
 * @date 2012-8-30 下午5:07:10
 */
public class ArgumentVerifyUtil {

	/**
	 * 校验对象为空(String除外)
	 * @param obj
	 * @param errorMsg
	 * @param methodName
	 */
	public static void notNull(Object obj,String errorMsg,Logger logger){
		if(obj == null){
			logger.error("( "+errorMsg+" ) | 输入参数对象不能为空");
			throw new IllegalArgumentException(errorMsg);
		}
	}

	/**
	 * 校验字符串不能为空
	 * @param str
	 * @param errorMsg
	 * @param logger
	 */
	public static void notBlank(String str,String errorMsg,Logger logger){
		if(str == null || "".equals(str.trim())){
			logger.error("( "+errorMsg+" ) | 输入参数String不能为空");
			throw new IllegalArgumentException(errorMsg);
		}
	}

	/**
	 * @description 校验数字不大于零
	 * @param num 需校验的数值
	 * @param errorMsg 出错信息
	 * @param methodName 涉及方法名
	 */
	public static void notZero(long num,String errorMsg,Logger logger){
		if(num <= 0){
			logger.error("( "+errorMsg+" ) | 输入参数必须大于0");
			throw new IllegalArgumentException(errorMsg);
		}
	}

	/**
	 * @description 校验数字小于等于0
	 * @param num
	 *            需校验的数值
	 * @param errorMsg
	 *            出错信息
	 */
	public static void notNegative(long num, String errorMsg, Logger logger) {
		if (num < 0) {
			logger.error("( " + errorMsg + " ) | 输入参数必须大于等于0 )");
			throw new IllegalArgumentException(errorMsg);
		}
	}

	/**
	 * 检查参数长度不超过界限值
	 * @param argsLength
	 * @param limit
	 * @param errorMsg
	 */
	public static void notLongerThan(int argsLength, int limit, String errorMsg,Logger logger) {
		if (argsLength > limit) {
			logger.error("( " + errorMsg + " ) | 参数长度大于"+limit+" )");
			throw new IllegalArgumentException(errorMsg);
		}
	}
}
