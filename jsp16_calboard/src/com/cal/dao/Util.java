package com.cal.dao;

public class Util {
	// 한자리수 -> 두자리수
	public static String isTwo(String msg) {
		return (msg.length()<2)?"0"+msg:msg;
	}
}
