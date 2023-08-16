package com.maike.dao;

import java.util.HashMap;
import java.util.List;

import com.maike.entity.StuGrade;

public interface CourseScoreDao {
	//查询所有的成绩
	List<HashMap<String, Object>> listcoursescore();
	//根据条件查询成绩表
	List<HashMap<String, Object>> findcoursescorebyother(String typeString,String keyString);
	//添加新的学生课程成绩
	int addcoursescore(StuGrade stuGrade);
	//删除学生课程成绩
	int deletecoursescore(String id);
	//更新学生课程成绩
	int updatecoursescore(StuGrade stuGrade);
}
