package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.maike.dao.TeacherCourseDao;
import com.maike.daoimpl.TeacherCourseDaoImpl;
import com.maike.entity.UniversalPage;

/**
 * Servlet implementation class ListAllTeachCourse
 */
@WebServlet("/ListAllTeachCourse")
public class ListAllTeachCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherCourseDao dao=new TeacherCourseDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAllTeachCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//登陆
		//用哈希表将登陆查询的信息存储然后再添加到list集合中
		request.setCharacterEncoding("utf-8");
		//创建session对象
		//HttpSession session = request.getSession();
		// 获取页号 
		//默认 初始值为1 
		int pageNo = 1; //从页面获取页号 数据
		if (request.getParameter("pageNo") != null) { 
			pageNo = Integer.parseInt(request.getParameter("pageNo")); 
		}
		String typeString;
		String keyString=request.getParameter("keyStr");
		if(keyString==null||keyString.equals("")) {
			typeString="1";
		}else {
			typeString=request.getParameter("typeStr");
		}
		//CoursePage coursePage=new CoursePage();
		//每页显示的数据
		int pagesize=10;
		//从页面获取 name 数据(要查询的姓名) 
		//String keystr = request.getParameter("keystr");
		//查询类型
		//String typestr=request.getParameter("typestr");
		UniversalPage universalPage=dao.universalPage(typeString,keyString, pageNo, pagesize);
		/*将list集合装换成json对象*/
		//String json=JSON.toJSONString("sgvs");
		Gson gson=new Gson();
		String json=gson.toJson(universalPage);
		out.print(json);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
