package com.ruzhi.demo.lifeserverweb;


import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 各种格式的编码加码工具类.
 *
 * @author sky
 *
 */
public class EncoderUtil {

	private static final String UTF8_URL_ENCODING = "UTF-8";
	private static final String GBK_URL_ENCODING = "GBK";

	/**
	 * URL 编码, Encode默认为GBK.
	 */
	public static String URLEncode(String input) {
		if(StringUtils.isEmpty(input)){
			return "";
		}

		try {
			return URLEncoder.encode(input, GBK_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}
	/**
	 * URL 编码, Encode为UTF8.
	 */
	public static String URLEncodeUTF8(String input){
		if(StringUtils.isEmpty(input)){
			return "";
		}

		try {
			return URLEncoder.encode(input, UTF8_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode默认为GBK.
	 */
	public static String URLDecode(String input) {
		if(StringUtils.isEmpty(input)){
			return "";
		}

		try {
			return URLDecoder.decode(input, GBK_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}

	/**
	 * URL 解码, Encode为UTF8.
	 */
	public static String URLDecodeUTF8(String input) {
		if(StringUtils.isEmpty(input)){
			return "";
		}

		try {
			return URLDecoder.decode(input, UTF8_URL_ENCODING);
		} catch (UnsupportedEncodingException e) {
			throw new IllegalArgumentException("Unsupported Encoding Exception", e);
		}
	}
	/**
	 *进行unicode编码
	 * @param str
	 * @return
	 */
	public static String toUnicode(String str){
		char[]arChar=str.toCharArray();
		int iValue=0;
		String uStr="";
		for(int i=0;i<arChar.length;i++){
			iValue=(int)str.charAt(i);
			if(iValue<=256){
				uStr+="\\u00"+Integer.toHexString(iValue);
			}else{
				uStr+="\\u"+Integer.toHexString(iValue);
			}
		}
		return uStr;
	}
	/**
	 *进行unicode解码
	 * @param str
	 * @return
	 */
	public static String decodeUnicode(String dataStr ) {
		int start = 0;
		int end = 0;
		final StringBuffer buffer = new StringBuffer();
		while( start > -1 ) {
			end = dataStr.indexOf( "\\u", start + 2 );
			String charStr = "";
			if( end == -1 ) {
				charStr = dataStr.substring( start + 2, dataStr.length() );
			} else {
				charStr = dataStr.substring( start + 2, end);
			}
			System.out.println(end + ":" +charStr);
			char letter = (char) Integer.parseInt( charStr, 16 );
			buffer.append( new Character( letter ).toString() );
			start = end;
		}
		return buffer.toString();
	}
	public static void main(String[] args) {
		System.out.println(URLEncode("港式"));
	}
}