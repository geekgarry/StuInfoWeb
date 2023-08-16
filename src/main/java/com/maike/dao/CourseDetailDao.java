package com.maike.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.maike.entity.CourseDetail;
import com.maike.entity.UniversalPage;

public interface CourseDetailDao {
	//添加教师的每次课程的相关信息
	int addCourseDetail(CourseDetail courseDetail);
	//删除教师的课程的相关信息
	int deleteCourseDetail(CourseDetail courseDetail);
	//修改教师的授课信息
	int updatecoursedetail(CourseDetail courseDetail);
	//查询教师授课的课程信息
	ArrayList<HashMap<String, Object>> querycoursedetail(String typeString,String keyString);
	List<HashMap<String, Object>> querycoursedetail(CourseDetail courseDetail,int page,int pagesize);
	UniversalPage universalPage(CourseDetail courseDetail,int page,int pagesize);
	int totalrow(String id);
}
