package com.maike.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.maike.entity.StuGrade;
import com.maike.entity.UniversalPage;

public interface StuGradeDao {
	//查询所有的学生用户
		List<HashMap<String, Object>> selectStuGrade(StuGrade grade,int page,int pagesize);
		//根据班级号查询
		List<HashMap<String, Object>> selectStuGradebyclass(StuGrade grade,int page,int pagesize);
		UniversalPage universalPage(StuGrade grade,int page,int pagesize);
		//查询同意班级的人数
		int totalrow(StuGrade grade);
		//根据username查询用户
		ArrayList<HashMap<String, Object>> ListStuGrade(StuGrade grade);
		//根据学号查询学生成绩
		StuGrade listStuGrade(StuGrade grade);
		//根据学号和其他查询成绩
		List<HashMap<String, Object>> selectStuGrade(String type,StuGrade grade,int page,int pagesize);
		//通过学号修改学生信息
		int updatestuGrade(StuGrade grade);
		//添加新的学生信息
		int insertstuGrade(StuGrade grade);
		//删除学生信息
		int deletestuGrade(StuGrade grade);
}
