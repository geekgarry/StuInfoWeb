package com.maike.util;

/**
 * 
 * 生成主键的工具类
 * 主键的格式为：当前时间+0~9
 *
 */ 
public class IdUtils { 
	 private static IdUtils instance = null; 
	 private static final long COUNT_SUM = 10; 
	 private static long lastTime = System.currentTimeMillis();
	 private static short currentCount = 0; 
	 private IdUtils() { 
	 } 
	 public static synchronized IdUtils getInstance() { 
		 if (instance == null) { 
			 instance = new IdUtils(); 
			 } 
		 return instance; 
	 } 
	 /**
     * 用来产生唯一id
     * 
     * @return
     */ 
	 public synchronized String getUID() { 
		 if (currentCount == COUNT_SUM) { 
			 boolean done = false; 
			 while (!done) { 
				 long nowTime = System.currentTimeMillis(); 
				 if (nowTime == lastTime) { 
				 try { 
					Thread.sleep(1);// 可以保证当前得到的时间和上一次的时间不一致 
				 } catch (InterruptedException e) { 
						 // TODO Auto-generated catch block 
					e.printStackTrace(); 
				 } continue; 
				 } else { 
					lastTime = nowTime; 
					currentCount = 0; 
					done = true; 
				 } 
			} 
		} 
	return lastTime+ "" + (currentCount++); 
	} 
}
