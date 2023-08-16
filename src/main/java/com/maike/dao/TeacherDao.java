package com.maike.dao;

import java.util.HashMap;
import java.util.List;

import com.maike.entity.Teacher;
import com.maike.entity.UniversalPage;

public interface TeacherDao {
	//根据id查询教师
	List<HashMap<String, Object>> queryteacherinfo(String userid);
	//添加教师信息
	int addteacherinfo(Teacher teacher);
	//删除教师信息
	int deleteteacherinfo(Teacher teacher);
	//修改教师信息
	int updateteacherinfo(Teacher teacher);
	//查询教师信息
	List<HashMap<String, Object>> queryteacherinfo(String typeString,String keyString,int page,int pagesize);
	//分页查询的接口
	UniversalPage universalPage(String typeString,String keyString,int page,int pagesize);
	int totalrow();
	//查询一条数据，为了修改
	Teacher queryteacher(String userid);
}
