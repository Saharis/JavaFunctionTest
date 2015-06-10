package com.virgil.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * DBConnect
 * Created by liuwujing on 15/6/8.
 */
public class DBConnect {

    public static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

    public enum Type {
        CONFIG,
        DATA
    }

    public static HashMap<Type, DBProp> dbMap = new HashMap<>();

    static {
        dbMap.put(Type.CONFIG, new DBProp(Type.CONFIG));
        dbMap.put(Type.DATA, new DBProp(Type.DATA));
        try {
            Class.forName(DRIVER_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public synchronized static Connection createConnection(Type type) {
        DBProp prop = dbMap.get(type);
        Connection conn = null;
        if (prop != null) {
            try {
                conn = DriverManager.getConnection(prop.url, prop.user, prop.password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }

}
