package com.maike.dao;

import java.util.HashMap;
import java.util.List;

import com.maike.entity.CoursePage;
import com.maike.entity.StuSubject;

public interface CourseDao {
	//查询所有科目
	List<HashMap<String, Object>> listcourse(String typestr,String keystr,int page,int pagesize);
	//获取表的总记录数pagesize=每一页的条数
	CoursePage coursePage(String typestr,String keystr,int page,int pagesize);
	int totalrow();
	//根据类型查询科目
	List<HashMap<String, Object>> findcoursebyother(String typestr,String keystr,int page,int pagesize);
	//查询所有
	List<HashMap<String, Object>> findcoursebyother();
	//添加新的科目
	int addcourse(StuSubject stusubject);
	//删除一个旧的科目
	int deletecourse(String id);
	//更新旧的科目信息
	int updatecourse(StuSubject stusubject);
}
