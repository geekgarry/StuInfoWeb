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

import com.maike.dao.AdminUserDao;
import com.maike.entity.AdminUser;
import com.maike.logandreg.AdminUSerLogin;
import com.maike.odbconnect.DbConnection;
import com.maike.util.SecurityUtil;

public class AdminUserDaoImpl implements AdminUserDao{

	@Override
	//查询所有的管理员信息
	public List<HashMap<String, Object>> selectadminUSer() {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from adminuser";
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
	/*管理员登陆用实例*/
	public ArrayList<HashMap<String, Object>> selectadminUSer(String username) {
		// TODO Auto-generated method stub
		String sql="select * from adminuser where username='"+username+"'";
		ArrayList<HashMap<String, Object>> list=new  ArrayList<HashMap<String,Object>>();
		AdminUSerLogin login=new AdminUSerLogin();
		try {
			list=login.getandset(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public AdminUser selectadminUser(String id) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<AdminUser> list = new ArrayList<AdminUser>();
		AdminUser adminUser = new AdminUser();
		String sql = "select * from adminuser where id="+id;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				adminUser.setUsername(rs.getString("username"));
				adminUser.setPassword(rs.getString("password"));
				adminUser.setId(rs.getString("id"));
				//stu.setPassword(rs.getString("password"));
				//stu.setCreate_time(rs.getString("create_time"));
				//list.add(stu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbConnection.close(conn, pstmt, rs);
		}
		
		return adminUser;
	}

	@Override
	//更新管理员信息
	public int updateadmininfo(AdminUser adminUser) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		String sql="update adminuser set username=?,password=?,update_time=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, adminUser.getUsername());
			pstmt.setString(2, adminUser.getPassword());
			pstmt.setString(3, adminUser.getUpdate_time());
			pstmt.setString(4, adminUser.getId());
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
	//插入新的管理员信息
	public int insertadmininfo(AdminUser adminUser) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		int flag=0;
		String sql="insert into adminuser(username,password,create_time,update_time)values('"+adminUser.getUsername()+"',"
				+ "'"+adminUser.getPassword()+"','"+adminUser.getCreate_time()+"','"+adminUser.getUpdate_time()+"')";
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

	@Override
	//删除管理员信息
	public int deleteadminuser(String id) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		int flag=0;
		String sql="delete from adminuser where id="+id;
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
