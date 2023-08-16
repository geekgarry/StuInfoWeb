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

import com.maike.dao.TeacherDao;
import com.maike.entity.CoursePage;
import com.maike.entity.Teacher;
import com.maike.entity.UniversalPage;
import com.maike.odbconnect.DbConnection;

public class TeacherDaoImpl implements TeacherDao{

	@Override
	public int addteacherinfo(Teacher teacher) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "insert into teacher(id,password,realname,gender,identityid,incollege,create_time)values(?,?,?,?,?,?,?)";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getId());
			pstmt.setString(2, teacher.getPassword());
			pstmt.setString(3, teacher.getRealname());
			pstmt.setString(4, teacher.getGender());
			pstmt.setString(5, teacher.getIdentityid());
			pstmt.setString(6, teacher.getIncollege());
			pstmt.setString(7, teacher.getCreate_time());
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
	public int deleteteacherinfo(Teacher teacher) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "delete from teacher where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getId());
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
	public int updateteacherinfo(Teacher teacher) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		//ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "update teacher set password=?, realname=?,gender=?,identityid=?, incollege=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, teacher.getPassword());
			pstmt.setString(2, teacher.getRealname());
			pstmt.setString(3, teacher.getGender());
			pstmt.setString(4, teacher.getIdentityid());
			pstmt.setString(5, teacher.getIncollege());
			pstmt.setString(6, teacher.getId());
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
	public List<HashMap<String, Object>> queryteacherinfo(String typeString, String keyString,int page,int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		String sql1="select * from teacher order by create_time asc LIMIT ?,?";
		String sql2="select * from teacher where incollege like ? order by create_time asc LIMIT ?,?";
		String sql3="select * from teacher where realname like ? order by create_time asc LIMIT ?,?";
		String sql4="select * from teacher where id like ? order by create_time asc LIMIT ?,?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			switch (typeString) {
			case "1":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
				pstmt.setInt(1, (page-1)*pagesize);
				pstmt.setInt(2, pagesize);
				break;
			case "2":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql2);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "3":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql3);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "4":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql4);
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
	public List<HashMap<String, Object>> queryteacherinfo(String userid) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		String sql1="select * from teacher where id=? order by create_time asc";
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
	public UniversalPage universalPage(String typeString, String keyString, int page, int pagesize) {
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
		universalPage.setList(queryteacherinfo(typeString,keyString,page, pagesize));
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
		String sql = "select count(*) from teacher";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
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
	public Teacher queryteacher(String userid) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		Teacher teacher=new Teacher();
		String sql1="select * from teacher where id=?";
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
				teacher.setId(map.get("id").toString());
				teacher.setPassword(map.get("password").toString());
				teacher.setRealname(map.get("realname").toString());
				teacher.setGender(map.get("gender").toString());
				teacher.setIdentityid(map.get("identityid").toString());
				teacher.setIncollege(map.get("incollege").toString());
				teacher.setCreate_time(map.get("create_time").toString());
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return teacher;
	}

}
