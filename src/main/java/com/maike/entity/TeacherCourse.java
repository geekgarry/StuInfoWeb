package com.maike.entity;

public class TeacherCourse {
	private String id;
	private String teachid;
	private String subjectid;
	private String courseteam;
	private String classofteach;
	private String create_time;
	private String coursestatus;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTeachid() {
		return teachid;
	}
	public void setTeachid(String teachid) {
		this.teachid = teachid;
	}
	public String getSubjectid() {
		return subjectid;
	}
	public void setSubjectid(String subjectid) {
		this.subjectid = subjectid;
	}
	public String getCourseteam() {
		return courseteam;
	}
	public void setCourseteam(String courseteam) {
		this.courseteam = courseteam;
	}
	public String getClassofteach() {
		return classofteach;
	}
	public void setClassofteach(String classofteach) {
		this.classofteach = classofteach;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCoursestatus() {
		return coursestatus;
	}
	public void setCoursestatus(String coursestatus) {
		this.coursestatus = coursestatus;
	}
}
