package com.maike.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TeacheCourseId {
	// 格式化的时间字符串
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");

    // 获取当前时间年月日时分秒毫秒字符串
    private String getNowDateStr() {
        return sdf.format(new Date());
    }

    // 记录上一次的时间，用来判断是否需要递增全局数
    private String now = null;

    /*
     * 生成一个订单号
     */
	public static  String bornCount(String param){
//			String aram="0005"; // 首先查询出那个counter值     
		   int s=Integer.parseInt(param);     
		   ++s;     
		   s=(s==1000?1:s);   //这里将规定最大数字设定为小于1000  如果对生成的数字没有特定要求可以注释掉    我这里没有要求所以进行了注释
		   String reslut=s>=10?(s>=100?s+"":"0"+s):"00"+s; // 计算 转型        
		   System.out.println(reslut); // 然后存到 数据库！ 已测试哈！试试看！     
		   return reslut;
	}
	public synchronized String CreateCount(String param){
		if(param==null){
            param = "0000";
        }
		int s=Integer.parseInt(param);     
		String datastr = getNowDateStr();
        if (datastr.equals(now)) {
        	++s;// 自增
        } else {
            s = 1;
            now = datastr;
        }
        s=(s==10000?1:s);   //这里将规定最大数字设定为小于10000
        String reslut=s>=10?(s>=100?(s>=1000?s+"":"0"+s):"00"+s):"000"+s; // 计算 转型 (s>=10000?s+"":"0"+s)  
        return datastr+reslut;
    }
    /**
     * 生成规则设备编号:设备类型+五位编号（从1开始，不够前补0）
     * 
     * @param equipmentType
     *              设备类型
     * @param equipmentNo
     *              最新设备编号
     * @return
     */
    public synchronized String getNewEquipmentNo(String equipmentType, String equipmentNo){
        String newEquipmentNo = "0001";
        String datastr = getNowDateStr();
        if(equipmentNo != null && !equipmentNo.isEmpty()){
             int newEquipment = Integer.parseInt(equipmentNo) + 1;
             newEquipmentNo = String.format("%04d", newEquipment);
        }
        return datastr+newEquipmentNo;
    }
}
