package com.maike.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.persistence.Id;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maike.dao.StuDao;
import com.maike.daoimpl.StuUserDaoImpl;

/**
 * Servlet implementation class Deletestuinfobyid
 */
@WebServlet("/Deletestuinfobyid")
public class Deletestuinfobyid extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StuDao dao=new StuUserDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deletestuinfobyid() {
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
		request.setCharacterEncoding("utf-8");
		String stuid=request.getParameter("stuid");
		if(dao.deletestuinfo(stuid)>0) {
			response.sendRedirect("selectstuinfo.html");
		}else {
			out.print("删除失败！<a href='selectstuinfo.html'>返回</a>");
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
