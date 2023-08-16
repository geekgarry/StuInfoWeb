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

import com.maike.dao.StuDao;
import com.maike.daoimpl.StuUserDaoImpl;
import com.maike.entity.StuUser;

/**
 * Servlet implementation class UpdateStuInfo
 */
@WebServlet("/UpdateStuInfo")
public class UpdateStuInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuDao dao=new StuUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateStuInfo() {
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
		StuUser stuUser=new StuUser();
		String stuid=request.getParameter("stuid");
		String realname=request.getParameter("realname");
		String stugrade=request.getParameter("stugrade");
		String identityid=request.getParameter("identityid");
		String politicsstatus=request.getParameter("politicsstatus");
		String borndatetime=request.getParameter("borndatetime");
		String gender=request.getParameter("gender");
		String address=request.getParameter("address");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String district=request.getParameter("district");
		String pcd=province+","+city+","+district;
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		stuUser.setId(stuid);
		stuUser.setRealname(realname);
		stuUser.setStugrade(stugrade);
		stuUser.setIdentity(identityid);
		stuUser.setPoliticsstatus(politicsstatus);
		stuUser.setBorndate(borndatetime);
		stuUser.setGender(gender);
		stuUser.setAddress(address);
		stuUser.setCreate_time(date);
		if(dao.updatestuinfo(stuUser)>0) {
			response.sendRedirect("selectstuinfo.html");
		}else{
			out.print("修改信息失败！<a href='selectstuinfo.html'>返回</a>");
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
