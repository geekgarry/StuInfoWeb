package com.maike.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CoursePage {
	private int currentpage;
	private int totalpagesize;
	private int everypagesize;
	private int totalrows;
	private int previous;
	private int next;
	private List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getTotalpagesize() {
		return totalpagesize;
	}
	public void setTotalpagesize(int totalpagesize) {
		this.totalpagesize = totalpagesize;
	}
	public int getEverypagesize() {
		return everypagesize;
	}
	public void setEverypagesize(int everypagesize) {
		this.everypagesize = everypagesize;
	}
	public int getTotalrows() {
		return totalrows;
	}
	public void setTotalrows(int totalrows) {
		this.totalrows = totalrows;
	}
	public List<HashMap<String, Object>> getList() {
		return list;
	}
	public void setList(List<HashMap<String, Object>> list) {
		this.list = list;
	}
	public int getPrevious() {
		return previous;
	}
	public void setPrevious(int previous) {
		this.previous = previous;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
}
