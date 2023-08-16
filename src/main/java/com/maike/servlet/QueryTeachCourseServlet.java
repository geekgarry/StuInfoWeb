package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.maike.dao.TeacherCourseDao;
import com.maike.daoimpl.TeacherCourseDaoImpl;

/**
 * Servlet implementation class QueryTeachCourseServlet
 */
@WebServlet("/QueryTeachCourseServlet")
public class QueryTeachCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherCourseDao dao=new TeacherCourseDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryTeachCourseServlet() {
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
		String id=request.getParameter("id");
		//查询类型
		//String typestr=request.getParameter("typestr");
		List<HashMap<String, Object>> list=dao.queryTeacherCourse(id);
		/*将list集合装换成json对象*/
		//String json=JSON.toJSONString("sgvs");
		Gson gson=new Gson();
		String json=gson.toJson(list);
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
