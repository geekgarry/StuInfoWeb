package com.maike.servlet;

import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.LineListener;

import com.maike.dao.StuDao;
import com.maike.daoimpl.StuUserDaoImpl;
import com.maike.logandreg.StuUserLogin;

/**
 * Servlet implementation class IsExistStuUser
 */
@WebServlet("/IsExistStuUser")
public class IsExistStuUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuDao dao=new StuUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IsExistStuUser() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		//登陆
		//用哈希表将登陆查询的信息存储然后再添加到list集合中
		request.setCharacterEncoding("utf-8");
		//创建session对象
		//HttpSession session = request.getSession();
		String name=request.getParameter("username");
		String password=request.getParameter("password");
		ArrayList<HashMap<String,Object>> list=dao.selectStuUSer(name);
		/*if(name.equals("")||password.equals("")) {
			out.print("<script>alert('账户密码为空！请重新登录！');window.location='mslogin.html'</script>");
		}*/
		/*if(name.equals("cjj")&&password.equals("123")) {
			request.getRequestDispatcher("cmain.jsp").forward(request, response);
		}else {
			out.print("<script>alert('密码错误！请重新登录！');window.location='login.jsp'</script>");
		}*/
		/*String sql="select * from user where username='"+name+"'";
		ArrayList<HashMap<String, Object>> list=new  ArrayList<HashMap<String,Object>>();
		StuUserLogin login=new StuUserLogin();
		try {
			list=login.getSet(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}*/
		if(list.size()>0)
		{
			//，如果用户名存在，则判断用户密码是否存在
			String pw=list.get(0).get("password").toString();
			if (pw.equals(password)) {
				out.print("1");
			} else {
					//密码错误登陆失败
				out.print("0");
			}
			
		}
		else
		{
			//否则用户名不存在，提醒用户进行下一步操作
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
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
