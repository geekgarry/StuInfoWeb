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

import com.maike.dao.CourseScoreDao;
import com.maike.entity.StuGrade;
import com.maike.odbconnect.DbConnection;
import com.maike.util.SecurityUtil;

public class CourseScoreDaoImpl implements CourseScoreDao{

	@Override
	public List<HashMap<String, Object>> listcoursescore() {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		String sql1="select * from stugrade";
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

	@Override
	public List<HashMap<String, Object>> findcoursescorebyother(String typeString, String keyString) {
		// TODO Auto-generated method stub
		List<HashMap<String, Object>> list=new ArrayList<HashMap<String,Object>>();
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql0 = "select * from stugrade where realname like ? order by create_time asc";
		String sql1 = "select * from stugrade where id like ? order by create_time asc";
		String sql2 = "select * from stugrade where stugrade like ? order by create_time asc";
		String sql3 = "select * from stugrade where address like ? order by create_time asc";
		String sql4 = "select * from stugrade where gender=? order by create_time asc";
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			switch (typeString) {
			case "0":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql0);
				pstmt.setString(1, "%"+keyString+"%");
				break;
			case "1":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql1);
				pstmt.setString(1, "%"+keyString+"%");
				break;
			case "2":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql2);
				pstmt.setString(1, "%"+keyString+"%");
				break;
			case "3":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql3);
				pstmt.setString(1, "%"+keyString+"%");
				break;
			case "4":
				pstmt = DbConnection.getPreparedStatemnt(conn, sql4);
				pstmt.setString(1, "%"+keyString+"%");
				break;
			}
			rs=pstmt.executeQuery();
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
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			DbConnection.close(conn,pstmt,rs);
		}
		return list;
	}

	@Override
	public int addcoursescore(StuGrade stuGrade) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlString="insert into stugrade(stuid,subjectid,subjectscore,examtype,yearofstudy,examteam,create_time)values(?,?,?,?,?,?,?)";
		int flag=0;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sqlString);
			pstmt.setString(1, stuGrade.getStuid());
			pstmt.setString(2, stuGrade.getSubjectid());
			pstmt.setString(3, stuGrade.getSubjectscore());
			pstmt.setString(4, stuGrade.getExamtype());
			pstmt.setString(5, stuGrade.getYearofstudy());
			pstmt.setString(6, stuGrade.getExamteam());
			pstmt.setString(7, stuGrade.getCreate_time());
			flag=pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public int deletecoursescore(String id) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlString="delete from stugrade where id=?";
		int flag=0;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sqlString);
			pstmt.setString(1, id);
			flag=pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

	@Override
	public int updatecoursescore(StuGrade stuGrade) {
		// TODO Auto-generated method stub
		DbConnection dbo=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		String sqlString="update stusubject set stuid=?, subjectid=?,subjectscore=?,examtype=?,yearofstudy=?, examteam=? where id=?";
		int flag=0;
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=DbConnection.getPreparedStatemnt(conn, sqlString);
			pstmt.setString(1, stuGrade.getStuid());
			pstmt.setString(2, stuGrade.getSubjectid());
			pstmt.setString(3, stuGrade.getSubjectscore());
			pstmt.setString(4, stuGrade.getExamtype());
			pstmt.setString(5, stuGrade.getYearofstudy());
			pstmt.setString(6, stuGrade.getExamteam());
			pstmt.setString(7, stuGrade.getId());
			flag=pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
		}finally {
			DbConnection.close(conn,pstmt);
		}
		return flag;
	}

}
