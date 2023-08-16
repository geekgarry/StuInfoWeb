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

import com.maike.dao.StuGradeDao;
import com.maike.daoimpl.StuGradeDaoImpl;
import com.maike.entity.StuGrade;
import com.maike.util.IdCounter;

/**
 * Servlet implementation class AddStudentScore
 */
@WebServlet("/AddStudentScore")
public class AddStudentScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuGradeDao dao=new StuGradeDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudentScore() {
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
		StuGrade stuGrade=new StuGrade();
		String subjectid=request.getParameter("subjectid");
		String stuid=request.getParameter("stuid");
		String examtype=request.getParameter("examtype");
		String coursescore=request.getParameter("coursescore");
		String examteam=request.getParameter("subjecttime");
		//String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		stuGrade.setId(IdCounter.UUID());
		stuGrade.setStuid(stuid);
		stuGrade.setSubjectid(subjectid);
		stuGrade.setExamteam(examteam);
		stuGrade.setExamtype(examtype);
		stuGrade.setSubjectscore(coursescore);
		stuGrade.setCreate_time(date);
		if(dao.insertstuGrade(stuGrade)>0){
			//response.sendRedirect("addstuinfo.html");
			out.print("yes");
		}else{
			out.print("no");
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
