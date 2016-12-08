package com.ruzhi.demo.lifeserverweb;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by longjia.zt on 2014/11/29.
 * 进行Http请求的封装。
 */
public class HttpUtil {
   /* public static final Log logger = LogFactory.getLog(HttpUtil.class);
    private static MultiThreadedHttpConnectionManager httpConnectionManager = new MultiThreadedHttpConnectionManager();
    private static HttpClient client = new HttpClient(httpConnectionManager);
    static {
        //每主机最大连接数和总共最大连接数，通过hosfConfiguration设置host来区分每个主机
        client.getHttpConnectionManager().getParams().setDefaultMaxConnectionsPerHost(40);
        client.getHttpConnectionManager().getParams().setMaxTotalConnections(80);
        client.getHttpConnectionManager().getParams().setConnectionTimeout(200);
        client.getHttpConnectionManager().getParams().setSoTimeout(800);
        client.getHttpConnectionManager().getParams().setTcpNoDelay(true);
        client.getHttpConnectionManager().getParams().setLinger(1000);
        //失败的情况下会进行1次尝试,成功之后不会再尝试
        //client.getHttpConnectionManager().getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(1,false));
    }
    public static String  getStringFromHttp(String url,String charSet){
        StringBuilder sb = new StringBuilder();
        HttpMethod httpget = new GetMethod(url);
        try {
            client.executeMethod(httpget);
             BufferedReader l_reader = new BufferedReader(new InputStreamReader(
                    httpget.getResponseBodyAsStream(), "UTF-8"));
            String line = null;
            while ((line = l_reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            logger.warn("httpException,url="+url,e);
        } finally {
            httpget.releaseConnection();
        }
        return sb.toString();
    }*/
}
