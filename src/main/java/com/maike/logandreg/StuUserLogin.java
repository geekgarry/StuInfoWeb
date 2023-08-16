package com.maike.logandreg;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;

import com.maike.odbconnect.DbConnection;

public class StuUserLogin {
	/*String driver="com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/mytestdb?useSSL=true";
	String user = "root";
	String pwd = "147258cjj";*/
	DbConnection dbo=null;
	Connection conn;
	/*public StuUserLogin(){
		this.conn = DbConnection.getConnection();//DriverManager.getConnection(url, user, pwd);
	}*/
	public ArrayList<HashMap<String, Object>> getSet(String sql) throws ClassNotFoundException{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		//Class.forName(driver);
		try {
			dbo=new DbConnection();
			conn = dbo.getConnection();
			//conn=DriverManager.getConnection(url, user, pwd);
			stmt =conn.prepareStatement(sql);//createStatement();
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			while (rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
		
	}
}
