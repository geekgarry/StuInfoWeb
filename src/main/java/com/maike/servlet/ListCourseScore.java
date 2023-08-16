package com.maike.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.maike.dao.CourseScoreDao;
import com.maike.daoimpl.CourseScoreDaoImpl;

/**
 * Servlet implementation class ListCourseScore
 */
@WebServlet("/ListCourseScore")
public class ListCourseScore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseScoreDao dao=new CourseScoreDaoImpl();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListCourseScore() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
