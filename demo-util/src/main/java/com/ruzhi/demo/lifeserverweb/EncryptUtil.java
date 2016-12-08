package com.ruzhi.demo.lifeserverweb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;

/**
 *
 * @Project: lifekabao-biz
 *
 * @Author: 龙隆
 *
 * @Annotation:加密帮助类
 *
 * @Date:2012-8-30
 *
 */
public class EncryptUtil {

	protected static Logger log = LoggerFactory.getLogger(EncryptUtil.class);

	/**
	 * 将字符串加密成二进制流
	 *
	 * Author: 龙隆
	 *
	 * Last Modification Time: 2012-8-30
	 *
	 * @param data 数据
	 * @param charset 编码
	 * @return 编码串
	 * @throws IOException
	 */
	public static String encrypt(String data,String charset,String signType) {

		if(StringUtil.isEmpty(charset)){
			charset = "utf8";
		}
		//可能md5,sha等算法
		if(StringUtil.isEmpty(signType)){
			signType = "MD5";
		}

		byte[] bytes = null;

		try {
			MessageDigest md = MessageDigest.getInstance(signType);
			bytes = md.digest(data.getBytes(charset));
		} catch (GeneralSecurityException gse) {
			log.error("api sign error: ",gse);
		} catch (UnsupportedEncodingException e) {
			log.error("api sign error, encode unsupported : ",e);
		}

		String md5Str = null;
//		try {
//			//Base64 base64 = new Base64();
//			//md5Str = new String(base64.encode(bytes),charset);
//
//		} catch (UnsupportedEncodingException e) {
//			log.error("api sign error, encode unsupported : ",e);
//		}
		md5Str = byte2hex(bytes);

		return md5Str;
	}


	/**
	 * 二进制转化为16进制
	 *
	 * Author: 龙隆
	 *
	 * Last Modification Time: 2011-9-8
	 *
	 * @param bytes 需要转换的流
	 * @return 16进制字符串
	 */
	private static String byte2hex(byte[] bytes) {
		StringBuilder sign = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String hex = Integer.toHexString(bytes[i] & 0xFF);
			if (hex.length() == 1) {
				sign.append("0");
			}
			sign.append(hex.toLowerCase());
		}
		return sign.toString();
	}

}
