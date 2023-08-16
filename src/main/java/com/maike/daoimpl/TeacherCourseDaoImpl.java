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

import com.maike.dao.TeacherCourseDao;
import com.maike.entity.TeacherCourse;
import com.maike.entity.UniversalPage;
import com.maike.odbconnect.DbConnection;

public class TeacherCourseDaoImpl implements TeacherCourseDao{

	@Override
	public int addteachercourse(TeacherCourse course) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "insert into teachcourse(id,teacherid,subjectid,courseteam,classofteach,create_time)values(?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, course.getId());
			pstmt.setString(2, course.getTeachid());
			pstmt.setInt(3, Integer.parseInt(course.getSubjectid()));
			pstmt.setString(4, course.getCourseteam());
			pstmt.setString(5, course.getClassofteach());
			pstmt.setString(6, course.getCreate_time());
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
	public int deleteteachercourse(TeacherCourse course) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "delete from teachcourse where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, course.getId());
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
	public int updateteachercourse(TeacherCourse course) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "delete from teachcourse where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, course.getId());
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
	public ArrayList<HashMap<String, Object>> queryteachercourse(String typeString, String keyString,String param,int page,int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		//String sql1="select * from teachcourse order by create_time asc LIMIT ?,?";
		/*String sql1="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname," + 
				"ss.credit,ss.subjectproperty,ss.coursetime,tc.create_time from " + 
				"teachcourse tc inner join teacher t on tc.teacherid=t.id inner join stusubject ss on tc.subjectid=ss.id " + 
				"where t.id=? order by tc.create_time DESC LIMIT ?,?";*/
		String sql1="select tc.id,tc.courseteam,tc.classofteach,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.coursestatus,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.id=? order by tc.create_time DESC LIMIT ?,?";
		String sql2="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.coursestatus,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.id=? and t.incollege like ? order by tc.create_time DESC LIMIT ?,?";
		String sql3="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.coursestatus,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.id=? and ss.subjectname like ? order by tc.create_time DESC LIMIT ?,?";
		String sql4="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.coursestatus,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.id=? and tc.classofteach like ? order by tc.create_time DESC LIMIT ?,?";
		String sql5="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.coursestatus,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.id=? and t.realname like ? order by tc.create_time DESC LIMIT ?,?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			switch (typeString) {
			case "1":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
				pstmt.setString(1, param);
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "2":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql2);
				pstmt.setString(1, param);
				pstmt.setString(2, "%"+keyString+"%");
				pstmt.setInt(3, (page-1)*pagesize);
				pstmt.setInt(4, pagesize);
				break;
			case "3":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql3);
				pstmt.setString(1, param);
				pstmt.setString(2, "%"+keyString+"%");
				pstmt.setInt(3, (page-1)*pagesize);
				pstmt.setInt(4, pagesize);
				break;
			case "4":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql4);
				pstmt.setString(1, param);
				pstmt.setString(2, "%"+keyString+"%");
				pstmt.setInt(3, (page-1)*pagesize);
				pstmt.setInt(4, pagesize);
				break;
			case "5":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql5);
				pstmt.setString(1, param);
				pstmt.setString(2, "%"+keyString+"%");
				pstmt.setInt(3, (page-1)*pagesize);
				pstmt.setInt(4, pagesize);
				break;
			}
			rs=pstmt.executeQuery();
			ResultSetMetaData rsmdData=(ResultSetMetaData)rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				for(int i=1;i<=rsmdData.getColumnCount();i++) {
					map.put(rsmdData.getColumnName(i), rs.getObject(i));
					if(rsmdData.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}
				}
				list.add(map);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return list;
	}
	public UniversalPage universalPage(String typeString, String keyString,String param,int page, int pagesize) {
		// TODO Auto-generated method stub
		UniversalPage universalPage=new UniversalPage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrow(param);
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		universalPage.setCurrentpage(page);
		universalPage.setEverypagesize(pagesize);
		universalPage.setTotalrows(total);
		universalPage.setTotalpagesize(PageCount);
		universalPage.setList(queryteachercourse(typeString,keyString,param,page, pagesize));
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
	public int totalrow() {
		// TODO Auto-generated method stub
		int total = 0; // 总记录数
		//int PageCount = 0; // 页码总数
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select count(*) from teachcourse";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			//pstmt.setString(1, course.getClassofteach());
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
	public int totalrowbyclass(String stuclass) {
		// TODO Auto-generated method stub
		int total = 0; // 总记录数
		//int PageCount = 0; // 页码总数
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select count(*) from teachcourse where classofteach=?";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, stuclass);
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
	
	public TeacherCourse queryteacher(String userid) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		TeacherCourse teacherCourse=new TeacherCourse();
		String sql1="select * from teachcourse where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
			pstmt.setString(1, userid);
			rs=pstmt.executeQuery();
			ResultSetMetaData rsmdData=(ResultSetMetaData)rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				for(int i=1;i<=rsmdData.getColumnCount();i++) {
					map.put(rsmdData.getColumnName(i), rs.getObject(i));
					if(rsmdData.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}
				}
				teacherCourse.setId(map.get("id").toString());
				teacherCourse.setTeachid(map.get("teacherid").toString());
				teacherCourse.setSubjectid(map.get("subjectid").toString());
				teacherCourse.setCourseteam(map.get("courseteam").toString());
				teacherCourse.setClassofteach(map.get("classofteach").toString());
				teacherCourse.setCreate_time(map.get("create_time").toString());
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return teacherCourse;
	}

	@Override
	public ArrayList<HashMap<String, Object>> queryteachercourse(String typeString,String keyString, int page,
			int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		//String sql1="select * from teachcourse order by create_time asc LIMIT ?,?";
		String sql1="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id order by tc.create_time DESC LIMIT ?,?";
		String sql2="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.incollege like ? order by tc.create_time DESC LIMIT ?,?";
		String sql3="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and ss.subjectname like ? order by tc.create_time DESC LIMIT ?,?";
		String sql4="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and tc.classofteach=? order by tc.create_time DESC LIMIT ?,?";
		String sql5="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and t.realname like ? order by tc.create_time DESC LIMIT ?,?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			switch (typeString) {
			case "1":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
				//pstmt.setString(1, keyString);
				pstmt.setInt(1, (page-1)*pagesize);
				pstmt.setInt(2, pagesize);
				break;
			case "2":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql2);
				//pstmt.setString(1, param);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "3":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql3);
				//pstmt.setString(1, param);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "4":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql4);
				//pstmt.setString(1, course.getClassofteach());
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "5":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql5);
				//pstmt.setString(1, param);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			}
			rs=pstmt.executeQuery();
			ResultSetMetaData rsmdData=(ResultSetMetaData)rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				for(int i=1;i<=rsmdData.getColumnCount();i++) {
					map.put(rsmdData.getColumnName(i), rs.getObject(i));
					if(rsmdData.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}
				}
				list.add(map);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return list;
	}

	@Override
	public UniversalPage universalPage(String typeString,String keyString, int page, int pagesize) {
		// TODO Auto-generated method stub
		UniversalPage universalPage=new UniversalPage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrow();
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		universalPage.setCurrentpage(page);
		universalPage.setEverypagesize(pagesize);
		universalPage.setTotalrows(total);
		universalPage.setTotalpagesize(PageCount);
		universalPage.setList(queryteachercourse(typeString,keyString,page, pagesize));
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
	public int updateStatus(TeacherCourse course) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "update teachcourse set coursestatus=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, course.getCoursestatus());
			pstmt.setString(2, course.getId());
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
	public List<HashMap<String, Object>> queryTeacherCourse(String id) {
		// TODO Auto-generated method stub
		/*String sql1="select tc.id,tc.subjectid,tc.teacherid,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname," + 
				"ss.credit,ss.subjectproperty,ss.coursetime,tc.create_time from " + 
				"teachcourse tc inner join teacher t on tc.teacherid=t.id inner join stusubject ss on tc.subjectid=ss.id " + 
				"where tc.id=? order by tc.create_time DESC";*/
		String sql1="select tc.id,tc.subjectid,tc.teacherid,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and tc.id=? order by tc.create_time DESC";
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			ResultSetMetaData rsmdData=(ResultSetMetaData)rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				for(int i=1;i<=rsmdData.getColumnCount();i++) {
					map.put(rsmdData.getColumnName(i), rs.getObject(i));
					if(rsmdData.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}
				}
				list.add(map);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return list;
	}

	@Override
	public int totalrow(String param) {
		// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			int total = 0; // 总记录数
			//int PageCount = 0; // 页码总数
			DbConnection dbo=null;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			String sql = "select count(*) from teachcourse where teacherid=?";
			try {
				dbo=new DbConnection();
				conn=dbo.getConnection();
				pstmt=DbConnection.getPreparedStatemnt(conn, sql);
				pstmt.setString(1, param);
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

	@Override
	public ArrayList<HashMap<String, Object>> queryteachercoursebyclass(String stuclass, int page, int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		//String sql="select * from teachcourse order by create_time asc LIMIT ?,?";
		/*String sql="select tc.id,tc.courseteam,tc.classofteach,tc.coursestatus,t.realname,t.incollege,ss.subjectname," + 
				"ss.credit,ss.subjectproperty,ss.coursetime,tc.create_time from " + 
				"teachcourse tc inner join teacher t on tc.teacherid=t.id inner join stusubject ss on tc.subjectid=ss.id " + 
				"where t.id=? order by tc.create_time DESC LIMIT ?,?";*/
		String sql="select tc.id,tc.courseteam,tc.classofteach,t.realname,t.incollege,ss.subjectname,ss.credit,ss.subjectproperty,"
				+ "ss.coursetime,tc.create_time from teachcourse tc,teacher t,stusubject ss where tc.teacherid=t.id and "
				+ "tc.subjectid=ss.id and tc.classofteach=? order by tc.create_time DESC LIMIT ?,?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, stuclass);
			pstmt.setInt(2, (page-1)*pagesize);
			pstmt.setInt(3, pagesize);
			rs=pstmt.executeQuery();
			ResultSetMetaData rsmdData=(ResultSetMetaData)rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				for(int i=1;i<=rsmdData.getColumnCount();i++) {
					map.put(rsmdData.getColumnName(i), rs.getObject(i));
					if(rsmdData.getColumnName(i).equals("create_time")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
						map.replace("create_time", timeStamp);
					}
				}
				list.add(map);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return list;
	}

	@Override
	public UniversalPage universalPage(String stuclass, int page, int pagesize) {
		// TODO Auto-generated method stub
		UniversalPage universalPage=new UniversalPage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrowbyclass(stuclass);
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		universalPage.setCurrentpage(page);
		universalPage.setEverypagesize(pagesize);
		universalPage.setTotalrows(total);
		universalPage.setTotalpagesize(PageCount);
		universalPage.setList(queryteachercoursebyclass(stuclass,page, pagesize));
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

}
