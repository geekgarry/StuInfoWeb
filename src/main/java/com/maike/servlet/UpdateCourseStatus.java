package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maike.dao.TeacherCourseDao;
import com.maike.daoimpl.TeacherCourseDaoImpl;
import com.maike.entity.TeacherCourse;

/**
 * Servlet implementation class UpdateCourseStatus
 */
@WebServlet("/UpdateCourseStatus")
public class UpdateCourseStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherCourseDao dao=new TeacherCourseDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCourseStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#getServletInfo()
	 */
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null; 
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
		TeacherCourse teacherCourse=new TeacherCourse();
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		//String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		teacherCourse.setId(id);
		teacherCourse.setCoursestatus(status);
		if(dao.updateStatus(teacherCourse)>0) {
			out.print("1");
		}else{
			out.print("0");
			//out.print(stuid+realname+stugrade+identityid+politicsstatus+borndatetime+gender+address);
		}
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

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
