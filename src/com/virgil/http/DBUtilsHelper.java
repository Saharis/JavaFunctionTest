package com.virgil.http;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 * @author yq_wu
 * 
 */
public class DBUtilsHelper {

	private static String dirverClassName = "com.mysql.jdbc.Driver";
	// 172.16.150.107 -- 10.168.147.100
//	private static String url = "jdbc:mysql://172.16.150.107:3306/modeldb_52?useUnicode=true&characterEncoding=utf8";
	private static String url = "jdbc:mysql://127.0.0.1:3306/ctripdb_54?useUnicode=true&characterEncoding=utf8";
	private static String user = "root";
	private static String password = "root";
	
	public synchronized static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(dirverClassName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
//			DriverManager.
			conn = DriverManager.getConnection(url, user, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	

}
