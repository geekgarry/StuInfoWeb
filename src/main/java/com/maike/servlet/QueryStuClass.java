package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.maike.dao.StuDao;
import com.maike.daoimpl.StuUserDaoImpl;
import com.maike.entity.StuUser;



/**
 * Servlet implementation class QueryStuClass
 */
@WebServlet("/QueryStuClass")
public class QueryStuClass extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuDao dao=new StuUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QueryStuClass() {
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
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("userid");
		StuUser stuUser=dao.selectStuUser(id);
		//List<ShopOrder> list = dao.getAllOrder(id);
		//List<ShopUser> list = dao.getAllShopUser();
		//request.setAttribute("admin", stuUser);
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/updateadminuser.jsp");
		
		//dispatcher.forward(request, response);
		Gson gson=new Gson();
		String json=gson.toJson(stuUser);
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

}
