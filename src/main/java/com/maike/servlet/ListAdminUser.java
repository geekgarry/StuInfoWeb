package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.maike.dao.AdminUserDao;
import com.maike.daoimpl.AdminUserDaoImpl;

/**
 * Servlet implementation class ListAdminUser
 */
@WebServlet("/ListAdminUser")
public class ListAdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminUserDao dao=new AdminUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListAdminUser() {
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
		List<HashMap<String, Object>> list=dao.selectadminUSer();
		//request.setAttribute("list", list);
		/*RequestDispatcher dispatcher = request.getRequestDispatcher("/listVideo.jsp");
		dispatcher .forward(request, response);*/
		//HashMap<String, Object> map=new HashMap<String,Object>();
		//map.put("list", list);
		/*for(int i=0;i<list.size();i++) {
			HashMap<String, Object> map=new HashMap<String, Object>();
			String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(list.get(i).get("create_time").toString());
			map.replace("create_time", timeStamp);
			list.add(map);
		}*/
		/*将list集合装换成json对象*/
		//String json=JSON.toJSONString(list);
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

	/**
	 * @see HttpServlet#doHead(HttpServletRequest, HttpServletResponse)
	 */
	protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
