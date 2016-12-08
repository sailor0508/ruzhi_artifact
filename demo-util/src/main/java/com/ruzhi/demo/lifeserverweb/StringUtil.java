/*
 * Copyright 2013 etao.com All right reserved. This software is the
 * confidential and proprietary information of etao.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with etao.com .
 */
package com.ruzhi.demo.lifeserverweb;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 *
 * @author wb-guofengyun 2013-11-4 上午11:27:52
 */
public class StringUtil extends StringUtils {
    public static final String INCLUDE_ZH_GBK = "gbk";
    public static final String INCLUDE_ENGLISH = "en";
    public static final String INCLUDE_NUMBER = "num";
    /**
     * 将字符串转化为map，比如str=“id:123;name:xiaoqiang”
     *
     * @param str
     * @param splitStr1 所有键值对分隔符(;)
     * @param splitStr2 键和值分隔符(:)
     * @return
     */
    public static Map<String, String> getStr2Map(String str, String splitStr1, String splitStr2) {
        if (StringUtils.isNotBlank(str)) {
            String[] a = str.split(splitStr1);
            if (a != null && a.length > 0) {
                Map<String, String> map = new HashMap<String, String>();
                for (String s : a) {
                    if (StringUtils.isNotBlank(s)) {
                        String[] b = s.split(splitStr2);
                        if (b != null && b.length >= 1) {
                            String value = (b.length == 2 ? b[1] : "");
                            map.put(b[0], value.trim());
                        }
                    }
                }
                return map;
            }
        }
        return null;
    }

    /**
     * 去掉字符串中非字母或数字的字符
     *
     * @param s
     * @return s为空返回""
     */
    public static String getStrOnlyNumOrLetter(String s) {
        String str = "";
        if (StringUtils.isNotEmpty(s)) {
            Pattern p = Pattern.compile("[a-zA-Z0-9]*");
            Matcher m = p.matcher(s);
            while (m.find()) {
                str += m.group();
            }
        }
        return str;
    }

    /**
     * 版本比较
     *
     * @param a (exp:1.0.0)
     * @param b (exp:2.0.0)
     * @param includeEqual 是否包含相等
     * @return includeEqual?(a>=b?true:false):(a>b?true:false)
     */
    public static boolean VersionComparer(String a, String b, boolean includeEqual) {
        if (a == null || b == null) {
            return false;
        }
        String regex = "^([0-9]+\\.)+([0-9]+)$";
        if (!a.matches(regex) || !b.matches(regex)) {
            return false;
        }
        if (a.equals(b)) {
            return includeEqual;
        }
        String as[] = a.split("\\.");
        String bs[] = b.split("\\.");
        int i = 0;
        while (as.length > i && bs.length > i) {
            int ai = Integer.parseInt(as[i]);
            int bi = Integer.parseInt(bs[i]);
            if (ai > bi) {
                return true;
            }
            if (ai < bi) {
                return false;
            }
            i++;
        }
        while (as.length > i) {
            int ai = Integer.parseInt(as[i]);
            if (ai > 0) {
                return true;
            }
            i++;
        }
        return false;
    }
    /**
     * 过滤掉所有的html标签
     * @return
     */
    /*public static String filterHtmlTag(String html){
    	 try {
    	 StringBuffer sb=new StringBuffer();
    	 DOMFragmentParser parser = new DOMFragmentParser();
         HTMLDocument document = new HTMLDocumentImpl();
         DocumentFragment fragment = document.createDocumentFragment();
         InputSource is = new InputSource(new StringReader(html));
         is.setEncoding("GBK");
		 parser.parse(is, fragment);
         getText(sb,fragment);
         return sb.toString();
    		} catch (SAXException e) {
    		} catch (IOException e) {
    		}
    		return null;
    }*/
    private static void getText(StringBuffer sb, Node node) {
        if (node.getNodeType() == Node.TEXT_NODE) {
            sb.append(node.getNodeValue());//取得结点值，即开始与结束标签之间的信息
        }
        NodeList children = node.getChildNodes();
        if ( children != null ) {
            int len = children.getLength();
            for ( int i = 0; i < len; i++ ) {
                getText(sb, children.item(i));//递归遍历DOM树
            }
        }
    }

    /**
     * 检查字符串是否全是汉字 GBK编码 add by dangkang 2015-03
     * @param str 被检查字符串
     * @return boolean
     */
    public static boolean checkGBKOnly(String str) {
        char[] chars = str.toCharArray();
        boolean isGBK = false;
        for (int i = 0; i < chars.length; i++) {
            byte[] bytes = ("" + chars[i]).getBytes();
            if (bytes.length != 2) {
                isGBK = false;
                break;
            }
            int[] ints = new int[2];
            ints[0] = bytes[0] & 0xff;
            ints[1] = bytes[1] & 0xff;
            if (ints[0] >= 0x81 && ints[0] <= 0xFE && ints[1] >= 0x40 && ints[1] <= 0xFE) {//第一个字节从128开始 第二个字节从64开始
                isGBK = true;
            } else {
                isGBK = false;
            }
        }

        return isGBK;
    }

    public static boolean checkEnglishOnly(String str) {
        boolean isEnglish = false;
        String regex = "^[a-zA-Z]+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        isEnglish = match.matches();
        return isEnglish;
    }

    public static boolean checkOnlyInList(String str, List<String> includeStr) {
        boolean isOnly = false;
        char[] chars = str.toCharArray();
        if (CollectionUtils.isEmpty(includeStr)) {
            return isOnly;
        }
        String regex = "";
        if (includeStr.contains(INCLUDE_ENGLISH)) {
            regex += "a-zA-Z";
        }
        if (includeStr.contains(INCLUDE_NUMBER)) {
            regex += "0-9";
        }
        if (includeStr.contains(INCLUDE_ZH_GBK)) {
            regex += "\u4081-\ufefe";
        }
        regex = "^[" + regex + "]+?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(str);
        isOnly = match.matches();
        return isOnly;
    }
}
