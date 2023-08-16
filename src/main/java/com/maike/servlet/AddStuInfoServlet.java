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
 * Servlet implementation class AddStuInfoServlet
 */
@WebServlet("/AddStuInfoServlet")
public class AddStuInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuDao dao=new StuUserDaoImpl();
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStuInfoServlet() {
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
		StuUser user=new StuUser();
		String id=request.getParameter("stuid");
		String name=request.getParameter("name");
		String identityid=request.getParameter("identityid");
		String password=identityid.substring(12);
		String borndatetime=request.getParameter("datetime");
		//String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(datetime);
		//截取出生年份
		Integer birthday=Integer.parseInt(borndatetime.substring(0, 4));
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		//截取当前年份
		Integer currentyear=Integer.parseInt(date.substring(0, 4));
		//当前年份减去出生年份得出年龄
		String age=String.valueOf(currentyear-birthday);
		String gender=request.getParameter("gender");
		String politicsstatus=request.getParameter("politicsstatus");
		String province=request.getParameter("province");
		String city=request.getParameter("city");
		String district=request.getParameter("district");
		String pcd=province+","+city+","+district;
		String stugrade=request.getParameter("stugrade");
		String incollege=request.getParameter("incollege");
		String major=request.getParameter("stuclass");
		String stuclassid=request.getParameter("stuclassid");
		String stuclass=major+stugrade.substring(2)+stuclassid;
		//String address=request.getParameter("address");
		user.setId(id);
		user.setRealname(name);
		user.setIdentity(identityid);
		user.setPassword(password);
		user.setStugrade(stugrade);
		user.setAge(age);
		user.setBorndate(borndatetime);
		user.setCreate_time(date);
		user.setGender(gender);
		user.setPoliticsstatus(politicsstatus);
		user.setAddress(pcd);
		user.setIncollege(incollege);
		user.setStuclass(stuclass);
		if(dao.insertstuinfo(user)>0) {
			//response.sendRedirect("addstuinfo.html");
			out.print("添加成功！继续<a href='addstuinfo.html'>添加</a>");
		}else{
			out.print("<script>alert('失败！fail!');window.location='addstuinfo.html';</script>");
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
