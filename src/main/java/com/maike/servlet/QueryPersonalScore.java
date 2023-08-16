package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.maike.dao.StuGradeDao;
import com.maike.daoimpl.StuGradeDaoImpl;
import com.maike.entity.StuGrade;
import com.maike.entity.UniversalPage;

/**
 * Servlet implementation class QueryPersonalScore
 */
@WebServlet("/QueryPersonalScore")
public class QueryPersonalScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuGradeDao dao=new StuGradeDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryPersonalScore() {
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
		StuGrade stuGrade=new StuGrade();
		//String subjectid=request.getParameter("subjectid");
		String stuid=request.getParameter("stuid");
		//String examtype=request.getParameter("examtype");
		//String coursescore=request.getParameter("coursescore");
		String examteam=request.getParameter("courseteam");
		//String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
		stuGrade.setStuid(stuid);
		stuGrade.setExamteam(examteam);
		// 获取页号 
		//默认 初始值为1 
		int pageNo = 1; //从页面获取页号 数据
		if (request.getParameter("pageNo") != null) { 
			pageNo = Integer.parseInt(request.getParameter("pageNo")); 
		}
		//CoursePage coursePage=new CoursePage();
		//每页显示的数据
		int pagesize=10;
		//从页面获取 name 数据(要查询的姓名) 
		//String keystr = request.getParameter("keystr");
		//查询类型
		//String typestr=request.getParameter("typestr");
		UniversalPage universalPage=dao.universalPage(stuGrade,pageNo, pagesize);
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
