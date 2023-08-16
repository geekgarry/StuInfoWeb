package com.maike.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.maike.dao.CourseDetailDao;
import com.maike.daoimpl.CourseDetailDaoImpl;
import com.maike.entity.CourseDetail;
import com.maike.util.GenerateNum;

/**
 * Servlet implementation class UploadCourseDetail
 */
@WebServlet("/UploadCourseDetail")
public class UploadCourseDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CourseDetailDao dao=new CourseDetailDaoImpl();
	private String IMAGE_PATH = "file/courseresource/";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadCourseDetail() {
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
		PrintWriter out = response.getWriter();
		//当前系统时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
		//String id=request.getParameter("teachcourseid");
		DiskFileItemFactory factory=new DiskFileItemFactory(); 
		
		ServletFileUpload file = new ServletFileUpload(factory);
		CourseDetail courseDetail = new CourseDetail();
		File realFile=null;
		try {
			List list = file.parseRequest(request); 
			Iterator<ServletFileUpload> it = list.iterator();
			while(it.hasNext()){ 
				
				FileItem  fileItem=(FileItem)it.next(); 
				if(fileItem.isFormField()){
					if("teacherid".equals(fileItem.getFieldName())){
						courseDetail.setTeachid(fileItem.getString("UTF-8"));
					} else if("subjectid".equals(fileItem.getFieldName())){
						courseDetail.setSubjectid(fileItem.getString("UTF-8"));
					} else if("teachcourseid".equals(fileItem.getFieldName())){
						courseDetail.setTeachcourseid(fileItem.getString("UTF-8"));
					} else if("coursecontent".equals(fileItem.getFieldName())){
						courseDetail.setCoursecontent(fileItem.getString("UTF-8"));
					}
				} else { 
					if(fileItem.getName()!=null&&!fileItem.getName().equals("")){
						
						String filename = fileItem.getName();
						String ext = filename.substring(filename.lastIndexOf(".") + 1);
						
						if(!"ziprar7ztarzipxgz".contains(ext)){
							out.println("图片格式必须为：zip、rar、7z、tar、zipx、gz");
							return ;
						}
						if(fileItem.getSize() > 1024 * 1024*100){ // 3 M 
							out.println("图片不能大于100M");
							return ;
						}
						
						String newname = System.currentTimeMillis() + "." + ext;
						
						String str = this.getClass().getResource("/").getPath();
						str = str.replace("WEB-INF/classes/", "").substring(1);
						
						String imagesPath = str + IMAGE_PATH;
						
						File dir = new File(imagesPath);
						if(!dir.exists() && !dir.isDirectory())
							dir.mkdirs();
						
						realFile=new File(imagesPath, newname);
						fileItem.write(realFile);
						
						/*File old = new File(str, courseDetail.getCourseresource());
						if(old.exists())
							old.delete();*///第二次更新文件是的操作代码
						
						courseDetail.setCourseresource(IMAGE_PATH + newname);
						courseDetail.setCreate_time(date);
						courseDetail.setId(GenerateNum.getInstance().GenerateOrder());
					}
				}
			}
			dao.addCourseDetail(courseDetail);
		} catch (FileUploadException e) {
			realFile.delete();
			out.println("上传失败");
			e.printStackTrace();
		} catch (Exception e) {
			realFile.delete();
			out.println("上传失败");
			e.printStackTrace();
		}
		//out.flush(); 
		//out.close();
		/*request.setAttribute("id",courseDetail.getTeachcourseid());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/teacher/listTeachCourse.jsp");
		dispatcher.forward(request, response);*/
		response.sendRedirect("QueryTeachCourse?id="+courseDetail.getTeachcourseid());
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
