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

import com.maike.dao.TeacherCourseDao;
import com.maike.daoimpl.TeacherCourseDaoImpl;
import com.maike.entity.TeacherCourse;
import com.maike.util.IdUtils;
import com.maike.util.TeacheCourseId;

/**
 * Servlet implementation class AddTeachCourse
 */
@WebServlet("/AddTeachCourse")
public class AddTeachCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherCourseDao dao=new TeacherCourseDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTeachCourse() {
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
		//TeacheCourseId id=new TeacheCourseId();
		TeacherCourse teacherCourse=new TeacherCourse();
		String teacherid=request.getParameter("teacherid");
		String subjectnames=request.getParameter("subjectnames");
		String majors=request.getParameter("majors");
		//String classid=request.getParameter("classid");
		String courseteam=request.getParameter("courseteam");
		String teachclass=majors;//+classid;
		String idStr=IdUtils.getInstance().getUID();//String.valueOf(id.getNewEquipmentNo("", ""));
		//String subjectnames=request.getParameter("subjectnames");
		//String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		teacherCourse.setId(idStr);
		teacherCourse.setTeachid(teacherid);
		teacherCourse.setSubjectid(subjectnames);
		teacherCourse.setClassofteach(teachclass);
		teacherCourse.setCourseteam(courseteam);
		teacherCourse.setCreate_time(date);
		if(dao.addteachercourse(teacherCourse)>0) {
			//response.sendRedirect("addstuinfo.html");
			out.print("1");
		}else{
			out.print("0");
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
}
