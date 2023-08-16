package com.maike.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.maike.dao.CourseDetailDao;
import com.maike.entity.CourseDetail;
import com.maike.entity.UniversalPage;
import com.maike.odbconnect.DbConnection;

public class CourseDetailDaoImpl implements CourseDetailDao{

	@Override
	public int addCourseDetail(CourseDetail courseDetail) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "insert into coursedetail(id,subjectid,teachercourseid,teacherid,coursecontent,courseresource,create_time)values(?,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseDetail.getId());
			pstmt.setString(2, courseDetail.getSubjectid());
			pstmt.setString(3, courseDetail.getTeachcourseid());
			pstmt.setString(4, courseDetail.getTeachid());
			//pstmt.setString(5, courseDetail.getCoursehour());
			pstmt.setString(5, courseDetail.getCoursecontent());
			pstmt.setString(6, courseDetail.getCourseresource());
			pstmt.setString(7, courseDetail.getCreate_time());
			flag=pstmt.executeUpdate();
		}catch(SQLException e) {
			flag=0;
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public int deleteCourseDetail(CourseDetail courseDetail) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "delete from coursedetail where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseDetail.getId());
			flag=pstmt.executeUpdate();
		}catch(SQLException e) {
			flag=0;
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public int updatecoursedetail(CourseDetail courseDetail) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "update coursedetail set coursehour=?, coursecontent=?,courseresource=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, courseDetail.getId());
			flag=pstmt.executeUpdate();
		}catch(SQLException e) {
			flag=0;
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public ArrayList<HashMap<String, Object>> querycoursedetail(String typeString, String keyString) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from coursedetail order by create_time desc";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while(rs.next()){
				//StuUser stu = new StuUser();
				/*stu.setId(rs.getString("id"));
				stu.setStugrade(rs.getString("stugrade"));
				stu.setPassword(rs.getString("password"));
				stu.setAge(rs.getString("age"));
				stu.setGender(rs.getString("gender"));
				stu.setRealname(rs.getString("realname"));
				stu.setCreate_time(rs.getString("create_time"));*/
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					if(rsmd.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}else if(rsmd.getColumnName(i).equals("update_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("update_time", timeStamp);
					}
				}
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbConnection.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<HashMap<String, Object>> querycoursedetail(CourseDetail courseDetail, int page, int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		/*String sql="select cd.id,cd.subjectid,cd.teacherid,cd.teachercourseid,cd.coursecontent,cd.courseresource,tc.classofteach,t.realname,t.incollege,ss.subjectname," + 
				"ss.credit,ss.subjectproperty,cd.create_time from coursedetail cd inner join teacher t on " + 
				"cd.teacherid=t.id inner join stusubject ss on cd.subjectid=ss.id INNER JOIN teachcourse tc on " + 
				"cd.teachercourseid=tc.id where cd.teachercourseid=? order by tc.create_time DESC LIMIT ?,?";*/
		String sql = "select cd.id,cd.subjectid,cd.teacherid,cd.teachercourseid,cd.coursecontent,cd.courseresource,tc.classofteach,t.realname,t.incollege,ss.subjectname," + 
				"ss.credit,ss.subjectproperty,cd.create_time from coursedetail cd,teacher t,stusubject ss,"
				+ "teachcourse tc where cd.teacherid=t.id and cd.subjectid=ss.id and cd.teachercourseid=tc.id "
				+ "and cd.teachercourseid=? order by tc.create_time DESC LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, courseDetail.getTeachcourseid());
			pstmt.setInt(2, (page-1)*pagesize);
			pstmt.setInt(3, pagesize);
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while(rs.next()){
				//StuUser stu = new StuUser();
				/*stu.setId(rs.getString("id"));
				stu.setStugrade(rs.getString("stugrade"));
				stu.setPassword(rs.getString("password"));
				stu.setAge(rs.getString("age"));
				stu.setGender(rs.getString("gender"));
				stu.setRealname(rs.getString("realname"));
				stu.setCreate_time(rs.getString("create_time"));*/
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					if(rsmd.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}
				}
				list.add(map);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbConnection.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public UniversalPage universalPage(CourseDetail courseDetail, int page, int pagesize) {
		// TODO Auto-generated method stub
		UniversalPage universalPage=new UniversalPage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrow(courseDetail.getTeachcourseid());
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		universalPage.setCurrentpage(page);
		universalPage.setEverypagesize(pagesize);
		universalPage.setTotalrows(total);
		universalPage.setTotalpagesize(PageCount);
		universalPage.setList(querycoursedetail(courseDetail,page, pagesize));
		if(page==1) {
			universalPage.setPrevious(1);
		}else {
			universalPage.setPrevious(page-1);
		}
		if(page==PageCount) {
			universalPage.setNext(PageCount);
		}else {
			universalPage.setNext(page+1);
		}
		return universalPage;
	}

	@Override
	public int totalrow(String id) {
		// TODO Auto-generated method stub
		int total = 0; // 总记录数
		//int PageCount = 0; // 页码总数
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select count(*) from coursedetail where teachercourseid=?";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
				//PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return total;
	}

}
