package com.maike.logandreg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.maike.odbconnect.DbConnection;

public class AdminUSerLogin {
	DbConnection dbo=null;
	Connection conn;
	public ArrayList<HashMap<String, Object>> getandset(String sql) throws SQLException{
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<HashMap<String, Object>> list=new ArrayList<HashMap<String, Object>>();
		try {
			dbo=new DbConnection();
			conn=dbo.getConnection();
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			ResultSetMetaData rsmd=(ResultSetMetaData)rs.getMetaData();
			while(rs.next()) {
				HashMap<String, Object> map=new HashMap<String, Object>();
				for(int i=1;i<=rsmd.getColumnCount();i++) {
					map.put(rsmd.getColumnName(i), rs.getObject(i));
				}
				list.add(map);
			}
		}catch(Exception e) {
			e.addSuppressed(e);
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
		return list;
	}
}
