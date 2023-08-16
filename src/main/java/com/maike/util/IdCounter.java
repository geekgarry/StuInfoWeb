package com.maike.util;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.UUID;

public class IdCounter {
	private static int totalCount = 0;
	private int customerID;
	public IdCounter(){
		++totalCount;
		customerID = totalCount;
		System.out.println("增加一个");
	}
	public int getCustomerID() {
	DecimalFormat decimalFormat = new DecimalFormat("00000000");
	int count=Integer.parseInt(decimalFormat.format(customerID));
	return count;
	}
	/*
	 * @Desccription 生成不带 - 的uuid
	*/
	public static String getUuid(){
	     return UUID.randomUUID().toString().replace("-", "");
	}
	public static String UUID()
	{
		Random r=new Random();
		        //如生成的随机位数不足6位则自动加零补充
		DecimalFormat g=new DecimalFormat("1000000");
		        //返回时间增量+随机数的序列
		return String.format("%s%s",System.currentTimeMillis(),g.format(r.nextInt(1000000)));
	}
}
