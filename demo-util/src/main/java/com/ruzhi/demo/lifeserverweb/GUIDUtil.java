package com.ruzhi.demo.lifeserverweb;

import java.util.UUID;

/**
 * GUID生成工具类
 * @author along@taobao.com
 * @date 2011-7-26
 */
public class GUIDUtil {

	/**
	 * 产生一个GUID
	 * @return
	 */
	public static String newGUID()
	{
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}
}
