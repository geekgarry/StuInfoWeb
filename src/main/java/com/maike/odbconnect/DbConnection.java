package com.maike.odbconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	private static Connection conn;
	private static PreparedStatement pstmt;

	public DbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/stuinfo?useSSL=true&useUnicode=true&characterEncoding=utf-8";
			String user = "root";//数据库名称stuinfo
			String pwd = "123456";
			conn = DriverManager.getConnection(url,user,pwd);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public Connection getConnection() {
		//System.out.println("获取连接成功");
		return conn;
	}

	/*public static PreparedStatement getStatemnt(Connection conn,String sql) {
		try {
			pstmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}*/
	public static PreparedStatement getPreparedStatemnt(Connection conn,String sql) {
		try {
			pstmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}

	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭连接！");
		}
	}
	//关闭陈述状态
	public static void close(Statement stmt){
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("关闭陈述状态");
		}
	}
	//关闭结果集
	public static void close(ResultSet rs) {
		try {
			if(rs!=null)
				rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs,Statement stmt) {
		close(rs);
		close(stmt);
	}
	public static void close(Connection conn,Statement stmt) {
		close(conn);
		close(stmt);
	}
	public static void close(Connection conn,ResultSet rs) {
		close(rs);
		close(conn);
	}
	public static void close(Connection conn,Statement stmt,ResultSet rs) {
		close(conn);
		close(stmt);
		close(rs);
	}
}
