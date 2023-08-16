package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.maike.dao.CourseDetailDao;
import com.maike.daoimpl.CourseDetailDaoImpl;
import com.maike.entity.CourseDetail;
import com.maike.entity.UniversalPage;

/**
 * Servlet implementation class ListCourseDetailServlet
 */
@WebServlet("/ListCourseDetailServlet")
public class ListCourseDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDetailDao dao=new CourseDetailDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCourseDetailServlet() {
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
		//写入数据  传到页面
		PrintWriter out=response.getWriter();
		//登陆
		//用哈希表将登陆查询的信息存储然后再添加到list集合中
		request.setCharacterEncoding("utf-8");
		CourseDetail courseDetail=new CourseDetail();
		// 获取页号 
		//默认 初始值为1 
		int pageNo = 1; //从页面获取页号 数据
		if (request.getParameter("pageNo") != null) { 
			pageNo = Integer.parseInt(request.getParameter("pageNo")); 
		}
		String teachercourseid=request.getParameter("teachercourseid");
		//String keyString=request.getParameter("keyString");
		courseDetail.setTeachcourseid(teachercourseid);
		//每页显示的数据
		int pagesize=10;
		//从页面获取 name 数据(要查询的姓名) 
		//String keystr = request.getParameter("keystr");
		//查询类型
		//String typestr=request.getParameter("typestr");
		UniversalPage universalPage=dao.universalPage(courseDetail,pageNo,pagesize);
		//当前页   limit?,? 第一个数是从第几行开始查，不包括所在行，第二个数字是每页查询多少条数据
		//数据---->调用业务类的   根据 姓名---当前页页号(2个参数) 获取当前页的用户        
		//参数JSON化---->这里用的是 阿里巴巴 的方法(把一个对象转成JSON) 
		//需要引入fastjson-1.2.13.jar 包 
		//String param = JSON.toJSONString(universalPage);
		Gson gson=new Gson();
		String param=gson.toJson(universalPage);
		//把JSON 写到页面 
		out.print(param); 
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
