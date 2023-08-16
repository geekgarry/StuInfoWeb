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

import com.maike.dao.AdminUserDao;
import com.maike.daoimpl.AdminUserDaoImpl;
import com.maike.entity.AdminUser;

/**
 * Servlet implementation class UpdateAdminUser
 */
@WebServlet("/UpdateAdminUser")
public class UpdateAdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminUserDao dao=new AdminUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAdminUser() {
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
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		//登陆
		//用哈希表将登陆查询的信息存储然后再添加到list集合中
		request.setCharacterEncoding("utf-8");
		//创建session对象
		//HttpSession session = request.getSession();
		AdminUser admin=new AdminUser();
		String id=request.getParameter("id");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		admin.setId(id);
		admin.setUsername(username);
		admin.setPassword(password);
		admin.setUpdate_time(date);
		if(dao.updateadmininfo(admin)>0) {
			response.sendRedirect("listalladminuser.html");
		}else{
			out.print("修改信息失败！<a href='listalladminuser.html'>返回</a>");
			//out.print(stuid+realname+stugrade+identityid+politicsstatus+borndatetime+gender+address);
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
