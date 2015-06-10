package com.virgil.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ctrip.pay.base.businessmodel.SubmitpaymentinfoResponseEntity;

/**
 * Created by liuwujing on 15/6/8.
 */
public class DBUtil {

    public static void updateTable(List<String> sqlList) {
        Connection con = DBConnect.createConnection(DBConnect.Type.DATA);
        try {
            Statement st = con.createStatement();
            for (String sql : sqlList) {
                st.executeUpdate(sql);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteAllTable() {
        Connection con = DBConnect.createConnection(DBConnect.Type.DATA);
        try {
            DatabaseMetaData m_DBMetaData = con.getMetaData();
            ResultSet tableRet = m_DBMetaData.getTables(null, "%", "%", new String[]{"TABLE"});
            List<String> tableList = new ArrayList<>();
            while (tableRet.next()) {
                tableList.add(tableRet.getString("TABLE_NAME"));
            }
            Statement st = con.createStatement();
            for (String temp : tableList) {
                st.executeUpdate("DROP TABLE IF EXISTS " + temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBaffleData(SubmitpaymentinfoResponseEntity responseEntity) {
        if (responseEntity != null && responseEntity.getBean() != null) {

        }

    }
}
