package com.maike.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.maike.daoimpl.StuUserDaoImpl;
import com.maike.entity.StuUser;
import com.maike.entity.Teacher;
import com.maike.entity.UniversalPage;

public interface StuDao {
	//查询所有的学生用户
	List<HashMap<String, Object>> selectStuUSer(int page,int pagesize);
	//根据班级号查询
	List<HashMap<String, Object>> selectStuUSerbyclass(StuUser user,int page,int pagesize);
	UniversalPage universalPage(StuUser user,int page,int pagesize);
	//查询同意班级的人数
	int totalrow(StuUser user);
	//根据username查询用户
	ArrayList<HashMap<String, Object>> selectStuUSer(String username);
	//根据学号查询学生
	StuUser selectStuUser(String stuid);
	//根据学号查询学生
	List<HashMap<String, Object>> selectStuUser(String type,String ketString,int page,int pagesize);
	//通过学号修改学生信息
	int updatestuinfo(StuUser stu);
	//添加新的学生信息
	int insertstuinfo(StuUser user);
	//删除学生信息
	int deletestuinfo(String stuid);
	//更新审核状态
	int updateauditstatus(String auditstatus,String id);
	//分页查询的接口
	UniversalPage universalPage(int page,int pagesize);
	UniversalPage universalPage(String typeString,String keyString,int page,int pagesize);
	int totalrow();
	//查询学生的所有班级
	List<StuUser> liststuclass(StuUser user);
}
