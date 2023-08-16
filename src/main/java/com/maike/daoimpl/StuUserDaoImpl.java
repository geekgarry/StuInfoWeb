package com.maike.daoimpl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.maike.dao.StuDao;
import com.maike.entity.StuUser;
import com.maike.entity.UniversalPage;
import com.maike.logandreg.StuUserLogin;
import com.maike.odbconnect.DbConnection;
import com.maike.util.SecurityUtil;

public class StuUserDaoImpl implements StuDao{

	@Override
	public List<HashMap<String, Object>> selectStuUSer(int page,int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from user LIMIT ?,?";
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
					}else if(rsmd.getColumnName(i).equals("password")) {
						map.replace("password", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("identityid")) {
						map.replace("identityid", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("borndate")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp(i));
						map.replace("borndate", timeStamp);
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
	public StuUser selectStuUser(String stuname) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		List<StuUser> list = new ArrayList<StuUser>();
		StuUser stu = new StuUser();
		String sql = "select * from user where id="+stuname;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				stu.setId(rs.getString("id"));
				stu.setStugrade(rs.getString("stugrade"));
				//stu.setPassword(rs.getString("password"));
				stu.setBorndate(rs.getString("borndate"));
				stu.setStuclass(rs.getString("stuclass"));
				stu.setStugrade(rs.getString("stugrade"));
				stu.setIncollege(rs.getString("incollege"));
				stu.setGender(rs.getString("gender"));
				stu.setRealname(rs.getString("realname"));
				stu.setIdentity(rs.getString("identityid"));
				stu.setPoliticsstatus(rs.getString("politicsstatus"));
				stu.setAddress(rs.getString("address"));
				//stu.setCreate_time(rs.getString("create_time"));
				list.add(stu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbConnection.close(conn, pstmt, rs);
		}
		
		return stu;
	}
	@Override
	public int updatestuinfo(StuUser stu) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		String sql="update user set stugrade=?,identityid=?,politicsstatus=?,gender=?,borndate=?,realname=?,address=?,create_time=? where id=?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, stu.getStugrade());
			pstmt.setString(2, stu.getIdentity());
			pstmt.setString(3, stu.getPoliticsstatus());
			pstmt.setString(4, stu.getGender());
			pstmt.setString(5, stu.getBorndate());
			pstmt.setString(6, stu.getRealname());
			pstmt.setString(7, stu.getAddress());
			pstmt.setString(8, stu.getCreate_time());
			pstmt.setString(9, stu.getId());
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
	public int insertstuinfo(StuUser user) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		int flag=0;
		String sql="insert into user(id,password,stuclass,stugrade,incollege,identityid,politicsstatus,gender,borndate,realname,address,create_time)values('"+user.getId()+"',"
				+ "'"+user.getPassword()+"','"+user.getStuclass()+"','"+user.getStugrade()+"','"+user.getIncollege()+"','"+user.getIdentity()+"','"+user.getPoliticsstatus()+"','"+user.getGender()+"','"
				+user.getBorndate()+"',"+ "'"+user.getRealname()+"','"+user.getAddress()+"','"+user.getCreate_time()+"')";
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
	/*学生登陆用实例*/
	public ArrayList<HashMap<String, Object>> selectStuUSer(String username) {
		// TODO Auto-generated method stub
		String sql="select * from user where id='"+username+"'";
		ArrayList<HashMap<String, Object>> list=new  ArrayList<HashMap<String,Object>>();
		StuUserLogin login=new StuUserLogin();
		try {
			list=login.getSet(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<HashMap<String, Object>> selectStuUser(String type, String keyString,int page,int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql0 = "select * from user order by create_time asc LIMIT ?,?";
		String sql1 = "select * from user where realname like ? order by create_time asc LIMIT ?,?";
		String sql2 = "select * from user where id like ? order by create_time asc LIMIT ?,?";
		String sql3 = "select * from user where stugrade like ? order by create_time asc LIMIT ?,?";
		String sql4 = "select * from user where address like ? order by create_time asc LIMIT ?,?";
		String sql5 = "select * from user where gender=? order by create_time asc LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			switch (type) {
			case "0":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql0);
				//pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(1, (page-1)*pagesize);
				pstmt.setInt(2, pagesize);
				break;
			case "1":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql1);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "2":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql2);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "3":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql3);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "4":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql4);
				pstmt.setString(1, "%"+keyString+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "5":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql5);
				if(keyString.equals("男")||keyString=="男") {
					pstmt.setString(1, "1");
				}else if(keyString.equals("女")||keyString=="女"){
					pstmt.setString(1, "0");
				}
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			}
			rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while(rs.next()){
				HashMap<String, Object> map=new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					if(rsmd.getColumnName(i).equals("create_time")) {
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
					map.replace("create_time", timeStamp);
					}else if(rsmd.getColumnName(i).equals("password")) {
						map.replace("password", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("identityid")) {
						map.replace("identityid", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}
					if(rsmd.getColumnName(i).equals("borndate")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp(i));
						map.replace("borndate", timeStamp);
					}
				}
				list.add(map);
			}
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			
		}
		return list;
	}

	@Override
	public int deletestuinfo(String stuid) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		int flag=0;
		String sql="delete from user where id="+stuid;
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
	public int updateauditstatus(String auditstatus, String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UniversalPage universalPage(int page, int pagesize) {
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
		universalPage.setList(selectStuUSer(page, pagesize));
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
	public UniversalPage universalPage(String typeString,String keyString,int page, int pagesize) {
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
		universalPage.setList(selectStuUser(typeString, keyString, page, pagesize));
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
		String sql = "select count(*) from user";
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
	public List<StuUser> liststuclass(StuUser user) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<StuUser> list =new ArrayList<StuUser>();
		String sql = "select stuclass from user where incollege='"+user.getIncollege()+"' group by stuclass";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			//pstmt.setString(1, user.getIncollege());
			rs = pstmt.executeQuery();
			//ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while(rs.next()){
				StuUser stu = new StuUser();
				/*stu.setId(rs.getString("id"));
				stu.setStugrade(rs.getString("stugrade"));
				stu.setPassword(rs.getString("password"));
				stu.setAge(rs.getString("age"));
				stu.setGender(rs.getString("gender"));
				stu.setCreate_time(rs.getString("create_time"));*/
				//stu.setId(rs.getString("id"));
				//stu.setRealname(rs.getString("realname"));
				stu.setStuclass(rs.getString("stuclass"));
				//stu.setIncollege(rs.getString("incollege"));
				/*HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
					if(rsmd.getColumnName(i).equals("create_time")) {
					String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(rs.getTimestamp(i));
					map.replace("create_time", timeStamp);
					}else if(rsmd.getColumnName(i).equals("password")) {
						map.replace("password", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("identityid")) {
						map.replace("identityid", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("borndate")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp(i));
						map.replace("borndate", timeStamp);
					}
				}*/
				list.add(stu);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DbConnection.close(conn, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<HashMap<String, Object>> selectStuUSerbyclass(StuUser user, int page, int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql = "select * from user where stuclass=? LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			pstmt = DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, user.getStuclass());
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
					}else if(rsmd.getColumnName(i).equals("password")) {
						map.replace("password", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("identityid")) {
						map.replace("identityid", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("borndate")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp(i));
						map.replace("borndate", timeStamp);
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
	public UniversalPage universalPage(StuUser user, int page, int pagesize) {
		// TODO Auto-generated method stub
		UniversalPage universalPage=new UniversalPage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrow(user);
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		universalPage.setCurrentpage(page);
		universalPage.setEverypagesize(pagesize);
		universalPage.setTotalrows(total);
		universalPage.setTotalpagesize(PageCount);
		universalPage.setList(selectStuUSerbyclass(user, page, pagesize));
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
	public int totalrow(StuUser user) {
		// TODO Auto-generated method stub
		int total = 0; // 总记录数
		//int PageCount = 0; // 页码总数
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select count(*) from user where stuclass=?";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, user.getStuclass());
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
