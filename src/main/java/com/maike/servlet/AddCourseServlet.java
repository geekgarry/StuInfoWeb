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

import com.maike.dao.CourseDao;
import com.maike.daoimpl.CourseDaoImpl;
import com.maike.entity.StuSubject;

/**
 * Servlet implementation class AddCourseServlet
 */
@WebServlet("/AddCourseServlet")
public class AddCourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDao dao=new CourseDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCourseServlet() {
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
		StuSubject subject=new StuSubject();
		String subjectname=request.getParameter("subjectname");//new String(request.getParameter("subjectname").getBytes("iso-8859-1") ,"UTF-8");
		String subjectcredit=request.getParameter("subjectcredit");
		String subjecttype=request.getParameter("subjecttype");
		String subjectproperty=request.getParameter("subjectproperty");
		String subjecthour=request.getParameter("subjecthour");
		String subjectteam=request.getParameter("subjecttime");
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		subject.setSubjectname(subjectname);
		subject.setCredit(subjectcredit);
		subject.setSubjectype(subjecttype);
		subject.setSubjectproperty(subjectproperty);
		subject.setCoursetime(subjecthour);
		subject.setCourseteam(subjectteam);
		subject.setCreate_time(date);
		if(dao.addcourse(subject)>0) {
			out.print("<script>" + 
					"window.onLoad=function linkPage(){" + 
					"setInterval(toPage(),1000);" + 
					"}" + 
					"function toPage(){" + 
					"window.location = 'addCourse.html';" + 
					"}" + 
					"</script>添加成功！如果没有自动返回，请点击<a href='addCourse.html'>返回继续添加</a>");
		}else {
			out.print("添加失败");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
