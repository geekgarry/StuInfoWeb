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

import com.maike.dao.TeacherDao;
import com.maike.daoimpl.TeacherDaoImpl;
import com.maike.entity.Teacher;

/**
 * Servlet implementation class AddTeacherInfo
 */
@WebServlet("/AddTeacherInfo")
public class AddTeacherInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao dao=new TeacherDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeacherInfo() {
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
		Teacher teacher=new Teacher();
		String teacherid=request.getParameter("teacherid");
		String realname=request.getParameter("realname");
		String gender=request.getParameter("gender");
		String identityid=request.getParameter("identityid");
		String incollege=request.getParameter("incollege");
		String password=identityid.substring(12);
		//String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		teacher.setId(teacherid);
		teacher.setRealname(realname);
		teacher.setGender(gender);
		teacher.setIdentityid(identityid);
		teacher.setIncollege(incollege);
		teacher.setPassword(password);
		teacher.setCreate_time(date);
		if(dao.addteacherinfo(teacher)>0) {
			//response.sendRedirect("addstuinfo.html");
			out.print("添加成功！继续<a href='addteacher.html'>添加</a>");
		}else{
			out.print("<script>alert('失败！fail!');window.location='addteacher.html';</script>");
		}
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
