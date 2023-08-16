package com.maike.entity;

public class StuSubject {
	private String id;
	private String subjectname;
	private String credit;
	private String subjectype;
	private String subjectproperty;
	private String courseteam;
	private String coursetime;
	private String create_time;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectname() {
		return subjectname;
	}
	public void setSubjectname(String subjectname) {
		this.subjectname = subjectname;
	}
	public String getCredit() {
		return credit;
	}
	public void setCredit(String credit) {
		this.credit = credit;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getCourseteam() {
		return courseteam;
	}
	public void setCourseteam(String courseteam) {
		this.courseteam = courseteam;
	}
	public String getCoursetime() {
		return coursetime;
	}
	public void setCoursetime(String coursetime) {
		this.coursetime = coursetime;
	}
	public String getSubjectype() {
		return subjectype;
	}
	public void setSubjectype(String subjectype) {
		this.subjectype = subjectype;
	}
	public String getSubjectproperty() {
		return subjectproperty;
	}
	public void setSubjectproperty(String subjectproperty) {
		this.subjectproperty = subjectproperty;
	}
}
