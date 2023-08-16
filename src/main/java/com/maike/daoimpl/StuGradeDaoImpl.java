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

import com.maike.dao.StuGradeDao;
import com.maike.entity.StuGrade;
import com.maike.entity.UniversalPage;
import com.maike.odbconnect.DbConnection;
import com.maike.util.SecurityUtil;

public class StuGradeDaoImpl implements StuGradeDao{

	@Override
	public List<HashMap<String, Object>> selectStuGrade(StuGrade grade,int page, int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from stugrade where stuid=? and examteam=? LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, grade.getStuid());
			pstmt.setString(2, grade.getExamteam());
			pstmt.setInt(3, (page-1)*pagesize);
			pstmt.setInt(4, pagesize);
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
	public List<HashMap<String, Object>> selectStuGradebyclass(StuGrade grade, int page, int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select ss.subjectname,ss.credit,ss.subjectype,ss.subjectproperty,sg.subjectscore,sg.examtype,sg.examteam,sg.create_time from stugrade sg,stusubject ss where sg.subjectid=ss.id and sg.stuid=? and sg.examteam=? order by sg.create_time desc LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, grade.getStuid());
			pstmt.setString(2, grade.getExamteam());
			pstmt.setInt(3, (page-1)*pagesize);
			pstmt.setInt(4, pagesize);
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
	public UniversalPage universalPage(StuGrade grade, int page, int pagesize) {
		// TODO Auto-generated method stub
		UniversalPage universalPage=new UniversalPage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrow(grade);
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		universalPage.setCurrentpage(page);
		universalPage.setEverypagesize(pagesize);
		universalPage.setTotalrows(total);
		universalPage.setTotalpagesize(PageCount);
		universalPage.setList(selectStuGradebyclass(grade,page, pagesize));
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
	public int totalrow(StuGrade grade) {
		// TODO Auto-generated method stub
		int total = 0; // 总记录数
		//int PageCount = 0; // 页码总数
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select count(*) from stugrade where stuid=? and examteam=?";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, grade.getStuid());
			pstmt.setString(2, grade.getExamteam());
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
	public ArrayList<HashMap<String, Object>> ListStuGrade(StuGrade grade) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from stugrade";
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
	public StuGrade listStuGrade(StuGrade grade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<HashMap<String, Object>> selectStuGrade(String type, StuGrade grade, int page, int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from stugrade LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setInt(1, (page-1)*pagesize);
			pstmt.setInt(2, pagesize);
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
	public int updatestuGrade(StuGrade grade) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		String sql="update stugrade set subjectscore=?,examtype=?,examteam=?,create_time=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, grade.getSubjectscore());
			pstmt.setString(2, grade.getExamtype());
			pstmt.setString(3, grade.getExamteam());
			pstmt.setString(4, grade.getCreate_time());
			pstmt.setString(5, grade.getId());
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
	public int insertstuGrade(StuGrade grade) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		int flag=0;
		String sql="insert into stugrade(id,stuid,subjectid,subjectscore,examtype,examteam,create_time)values('"+grade.getId()+
				"','"+grade.getStuid()+"','"+grade.getSubjectid()+"','"+grade.getSubjectscore()+"','"+grade.getExamtype()+"','"+grade.getExamteam()+"','"+grade.getCreate_time()+"')";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			/*pstmt=conn.prepareStatement(sql);*/
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			//pstmt.setString(1, grade.getSubjectid());
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
	public int deletestuGrade(StuGrade grade) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		int flag=0;
		String sql="delete from stugrade where id='"+grade.getId()+"'";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			flag=pstmt.executeUpdate();
		}catch(SQLException e) {
			flag=0;
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

}
