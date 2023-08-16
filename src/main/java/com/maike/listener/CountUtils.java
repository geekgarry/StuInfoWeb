package com.maike.listener;

public class CountUtils {
	private static int loginCount;
	public static void add() {
		loginCount++;
	}
	public static void subtract() {
		loginCount--;
	}
	public static int getLoginCount() {
		return loginCount;
	}
}
