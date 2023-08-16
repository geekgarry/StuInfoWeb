package com.maike.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.maike.entity.Teacher;
import com.maike.entity.TeacherCourse;
import com.maike.entity.UniversalPage;

public interface TeacherCourseDao {
	//添加教师教授的课程
	int addteachercourse(TeacherCourse course);
	//删除教师教授的课程信息
	int deleteteachercourse(TeacherCourse course);
	//更新教师课程完结状态
	int updateStatus(TeacherCourse course);
	//修改教师教授的课程信息
	int updateteachercourse(TeacherCourse course);
	//教师个人查询教授的课程信息（条件查询）
	ArrayList<HashMap<String, Object>> queryteachercourse(String typeString,String keyString,String param,int page,int pagesize);
	UniversalPage universalPage(String typeString,String keyString,String param,int page,int pagesize);
	int totalrow();
	int totalrow(String param);
	int totalrowbyclass(String stuclass);
	ArrayList<HashMap<String, Object>> queryteachercoursebyclass(String stuclass,int page,int pagesize);
	UniversalPage universalPage(String stuclass,int page,int pagesize);
	//查询一条数据，为了修改
	TeacherCourse queryteacher(String id);
	List<HashMap<String, Object>> queryTeacherCourse(String id);
	//查询教授的课程信息（条件查询）
	ArrayList<HashMap<String, Object>> queryteachercourse(String typeString,String keyString,int page,int pagesize);
	UniversalPage universalPage(String typeString,String keyString,int page,int pagesize);
}
