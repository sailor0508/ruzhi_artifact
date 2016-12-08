package com.ruzhi.demo.util;


import org.apache.commons.lang.StringUtils;

import java.util.HashSet;
import java.util.Set;

public class SplitUtil {

	public static Set<String> toSet(String s, String split) {
		if(StringUtils.isBlank(s)) {
			return new HashSet<String>();
		}
		String eles [] = s.split(split);
		if(null == eles) {
			return new HashSet<String>();
		}
		Set<String> st = new HashSet<String>();
		for(String ele : eles) {
			st.add(ele);
		}
		return st;
	}
}
