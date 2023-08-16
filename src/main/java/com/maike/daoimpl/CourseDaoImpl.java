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

import com.maike.dao.CourseDao;
import com.maike.entity.CoursePage;
import com.maike.entity.StuSubject;
import com.maike.odbconnect.DbConnection;
import com.maike.util.SecurityUtil;

public class CourseDaoImpl implements CourseDao{

	@Override
	public List<HashMap<String, Object>> listcourse(String typestr,String keystr,int page,int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		String sql1="select * from stusubject order by create_time asc LIMIT ?,?";
		String sql2="select * from stusubject where subjectname like ? order by create_time asc LIMIT ?,?";
		//String sql3="select * from stusubject where realname like ? order by create_time asc LIMIT ?,?";
		//String sql4="select * from stusubject where id like ? order by create_time asc LIMIT ?,?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			switch (typestr) {
			case "1":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
				pstmt.setInt(1, (page-1)*pagesize);
				pstmt.setInt(2, pagesize);
				break;
			case "2":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql2);
				pstmt.setString(1, "%"+keystr+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			/*case "3":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql3);
				pstmt.setString(1, "%"+keystr+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "4":
				pstmt=DbConnection.getPreparedStatemnt(conn, sql4);
				pstmt.setString(1, "%"+keystr+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;*/
			default:
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
	public List<HashMap<String, Object>> findcoursebyother(String typestr, String keystr,int page,int pagesize) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		//List<StuUser> list = new ArrayList<StuUser>();
		ArrayList<HashMap<String, Object>> list =new ArrayList<HashMap<String,Object>>();
		String sql0 = "select * from stusubject where subjectname like ? order by create_time asc LIMIT ?,?";
		String sql1 = "select * from stusubject where subjectype like ? order by create_time asc LIMIT ?,?";
		String sql2 = "select * from stusubject where subjectproperty like ? order by create_time asc LIMIT ?,?";
		String sql3 = "select * from stusubject where courseteam like ? order by create_time asc LIMIT ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			switch (typestr) {
			case "0":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql0);
				pstmt.setString(1, "%"+keystr+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "1":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql1);
				pstmt.setString(1, "%"+keystr+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "2":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql2);
				pstmt.setString(1, "%"+keystr+"%");
				pstmt.setInt(2, (page-1)*pagesize);
				pstmt.setInt(3, pagesize);
				break;
			case "3":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql3);
				pstmt.setString(1, "%"+keystr+"%");
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
					}/*else if(rsmd.getColumnName(i).equals("password")) {
						map.replace("password", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}else if(rsmd.getColumnName(i).equals("identityid")) {
						map.replace("identityid", SecurityUtil.replaceStr(rs.getObject(i).toString()));
					}
					if(rsmd.getColumnName(i).equals("borndate")) {
						String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(rs.getTimestamp(i));
						map.replace("borndate", timeStamp);
					}*/
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
	public int addcourse(StuSubject stusubject) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="insert into stusubject(subjectname,credit,subjectype,subjectproperty,courseteam,coursetime,create_time)values(?,?,?,?,?,?,?)";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, stusubject.getSubjectname());
			pstmt.setString(2, stusubject.getCredit());
			pstmt.setString(3, stusubject.getSubjectype());
			pstmt.setString(4, stusubject.getSubjectproperty());
			pstmt.setString(5, stusubject.getCourseteam());
			pstmt.setString(6, stusubject.getCoursetime());
			pstmt.setString(7, stusubject.getCreate_time());
			flag=pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			flag=0;
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public int deletecourse(String id) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="delete from stusubject where id=?";
		int flag=0;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, id);
			flag=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			flag=0;
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public int updatecourse(StuSubject stusubject) {
		// TODO Auto-generated method stub
		int flag=0;
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sql="update stusubject set subjectname=?, credit=?,subjectype=?,subjectproperty=?, courseteam=?,coursetime=? where id=?";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql);
			pstmt.setString(1, stusubject.getSubjectname());
			pstmt.setString(2, stusubject.getCredit());
			pstmt.setString(3, stusubject.getSubjectype());
			pstmt.setString(4, stusubject.getSubjectproperty());
			pstmt.setString(5, stusubject.getCourseteam());
			pstmt.setString(6, stusubject.getCoursetime());
			pstmt.setString(7, stusubject.getId());
			flag=pstmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			flag=0;
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public CoursePage coursePage(String typestr, String keystr,int page,int pagesize) {
		// TODO Auto-generated method stub
		CoursePage coursepage=new CoursePage();
		int total = 0; // 总记录数
		int PageCount = 0; // 页码总数
		total = totalrow();
		PageCount = total%pagesize==0?total/pagesize:(total/pagesize)+1;//(total - 1) / pagesize + 1;
		coursepage.setCurrentpage(page);
		coursepage.setEverypagesize(pagesize);
		coursepage.setTotalrows(total);
		coursepage.setTotalpagesize(PageCount);
		coursepage.setList(listcourse(typestr,keystr,page, pagesize));
		if(page==1) {
			coursepage.setPrevious(1);
		}else {
			coursepage.setPrevious(page-1);
		}
		if(page==PageCount) {
			coursepage.setNext(PageCount);
		}else {
			coursepage.setNext(page+1);
		}
		return coursepage;
	}
	public int totalrow() {
		int total = 0; // 总记录数
		//int PageCount = 0; // 页码总数
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql = "select count(*) from stusubject";
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
	public List<HashMap<String, Object>> findcoursebyother() {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		String sql1="select * from stusubject order by create_time asc";
		//String sql3="select * from stusubject where realname like ? order by create_time asc LIMIT ?,?";
		//String sql4="select * from stusubject where id like ? order by create_time asc LIMIT ?,?";
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sql1);
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

}
