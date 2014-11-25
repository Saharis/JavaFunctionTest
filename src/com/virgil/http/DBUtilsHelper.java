package com.virgil.http;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.virgil.util.FileUtil;


public class DBUtilsHelper {

    private static String dirverClassName = "com.mysql.jdbc.Driver";
    private static String url = null;
    private static String user = "";
    private static String password = "";

    static {
        Properties pro = FileUtil.readProperties(DBUtilsHelper.class.getResource("").getPath() + "DBConfig.properties");
        if (pro != null) {
            url = pro.getProperty("url");
            user = pro.getProperty("user");
            password = pro.getProperty("password");
        } else {
            url = null;
            user = "";
            password = "";
        }
    }

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
        }
        return conn;
    }

}
