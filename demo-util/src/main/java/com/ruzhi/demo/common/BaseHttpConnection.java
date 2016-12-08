package com.ruzhi.demo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BaseHttpConnection {

	protected static Logger log = LoggerFactory.getLogger(BaseHttpConnection.class);
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	
	/**
	 * 
	 * @param surl
	 * @param charset
	 * @return
	 */
	protected static String executeURL(String surl,String charset) {
		String ret = "";
		if(surl.isEmpty()) {
			return ret;
		}
		String _charset = null;
		if(charset != null && !charset.equals("")) {
			_charset = charset;
		}else {
			_charset = DEFAULT_CHARSET;
		}
		URL l_url = null;
		HttpURLConnection l_connection = null;
		InputStream l_urlStream = null;
		BufferedReader l_reader = null;
		try {
			l_url = new URL(surl);
			l_connection = (HttpURLConnection) l_url.openConnection();
			l_connection.setConnectTimeout(2000);
			l_connection.setReadTimeout(2000);
			l_connection.connect();
			l_urlStream = l_connection.getInputStream();
			l_reader = new BufferedReader(new InputStreamReader(l_urlStream , _charset));
			for (String line = null; (line = l_reader.readLine()) != null;) {
				ret += line;
			}
			l_reader.close();
		}catch(Exception e) {
			log.error("BaseHttpConnection request fail",e);
		}finally {
			if(null != l_connection) {
				try {
					l_connection.disconnect();
				} catch (Exception e) {
					log.error("BaseHttpConnection request fail",e);
				}
			}
			if (l_reader != null) {
				try {
					l_reader.close();
				} catch (Exception e) {
					log.error("BaseHttpConnection request fail",e);
				}
			}
			if (l_urlStream != null) {
				try {
					l_urlStream.close();
				} catch (Exception e) {
					log.error("BaseHttpConnection request fail",e);
				}
			}
		}
		
		return ret;
	}
	
}
