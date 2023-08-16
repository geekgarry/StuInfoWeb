package com.maike.tst;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OpDbTest {
	private static Connection conn;
	private static PreparedStatement pstmt;
	
	public OpDbTest() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mycoursedb?userSSL=tree";
			String user = "root";
			String pwd = "111111";
			conn = DriverManager.getConnection(url,user,pwd);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("获取连接失败");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("获取连接失败");
		}
		//System.out.println("获取连接成功");
	}
	public static Connection getConnection() {
		System.out.print("数据库连接成功！");
		return conn;
	}
}
