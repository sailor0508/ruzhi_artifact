package com.ruzhi.demo.lifeserverweb;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class WebUtil {
    private static final Log logger = LogFactory.getLog(WebUtil.class);

    public WebUtil() {
    }

    public static String getWebContentWithTimeout(String theURL, String encode, int connTimeout, int requTimeout) {
        String sTotalString = "";
        URL l_url = null;
        HttpURLConnection l_connection = null;
        InputStream l_urlStream = null;
        BufferedReader l_reader = null;

        try {
            l_url = new URL(theURL);
            l_connection = (HttpURLConnection)l_url.openConnection();
            l_connection.setConnectTimeout(connTimeout);
            l_connection.setReadTimeout(requTimeout);
            l_connection.connect();
            l_urlStream = l_connection.getInputStream();
            l_reader = new BufferedReader(new InputStreamReader(l_urlStream, encode));
            short e = 1024;
            char[] buffer = new char[e];
            StringBuffer sb = new StringBuffer();
            boolean readcount = false;

            int readcount1;
            while((readcount1 = l_reader.read(buffer, 0, e)) > 0) {
                sb.append(buffer, 0, readcount1);
            }

            sTotalString = sb.toString();
            l_reader.close();
            l_urlStream.close();
            l_connection.disconnect();
        } catch (Exception var29) {
            if(logger.isWarnEnabled()) {
                logger.warn("error: exception in WebUtil: " + var29.toString() + ":" + theURL);
            }
        } finally {
            if(l_reader != null) {
                try {
                    l_reader.close();
                } catch (Exception var28) {
                    ;
                }
            }

            if(l_urlStream != null) {
                try {
                    l_urlStream.close();
                } catch (Exception var27) {
                    ;
                }
            }

            if(l_connection != null) {
                try {
                    l_connection.disconnect();
                } catch (Exception var26) {
                    ;
                }
            }

        }

        return sTotalString;
    }

    public static Map<String, String> parse2map(String querystring) {
        HashMap map = new HashMap();
        if(StringUtils.isEmpty(querystring)) {
            return map;
        } else {
            String[] params = querystring.split("&");
            String[] arr$ = params;
            int len$ = params.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                String param = arr$[i$];
                if(!StringUtils.isEmpty(param)) {
                    String[] tmps = param.split("=");
                    if(tmps != null && tmps.length == 2 && StringUtils.isNotEmpty(tmps[1])) {
                        map.put(tmps[0], EncoderUtil.URLDecode(tmps[1]));
                    }
                }
            }

            return map;
        }
    }

    public static String replaceParameterValue(String querystring, String parameter, String value) {
        if(StringUtils.isEmpty(parameter)) {
            return "?" + querystring;
        } else {
            Map map = parse2map(querystring);
            String[] params = parameter.split(",");
            String[] values = StringUtils.isEmpty(value)?new String[]{""}:value.split(",");
            int plen = params.length;
            int vlen = values.length;
            if(plen < vlen) {
                return "";
            } else {
                String tmp = "";

                for(int result = 0; result < plen; ++result) {
                    if(result < vlen) {
                        tmp = EncoderUtil.URLDecode(values[result]);
                    } else {
                        tmp = "";
                    }

                    map.put(params[result], tmp);
                }

                StringBuffer var13 = null;
                Iterator iterator = map.entrySet().iterator();

                while(iterator.hasNext()) {
                    Map.Entry entry = (Map.Entry)iterator.next();
                    if(!StringUtils.isEmpty((String)entry.getValue())) {
                        String tmpValue = EncoderUtil.URLEncode((String)entry.getValue());
                        if(var13 == null) {
                            var13 = new StringBuffer((String)entry.getKey() + "=" + tmpValue);
                        } else {
                            var13.append("&").append((String)entry.getKey() + "=" + tmpValue);
                        }
                    }
                }

                if(var13 == null) {
                    return "";
                } else {
                    return "?" + var13.toString();
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(replaceParameterValue((String)null, "a,b,c,d", "1,2,3,4"));
    }
}
