package com.maike.util;

public class SecurityUtil {
	/**
	 * 正则替换为*号
	 * @param param
	 * @return
	 */
	public static String replaceStr(String param){
		int len=param.length();
		if(len<9){
			return param;
		}
		return param.replaceAll("(.{"+(len<12?3:6)+"})(.*)(.{4})", "$1" + "****" + "$3");
	}
}
