package com.virgil.jdbc;

import java.io.File;
import java.util.Properties;

import com.virgil.LogException;
import com.virgil.util.FileUtil;
import com.virgil.util.StringUtil;

/**
 * DBProp
 * Created by liuwujing on 15/6/8.
 */
public class DBProp {
    public String url = "";
    public String user = "";
    public String password = "";
    public String driveName = "";

    public DBProp(DBConnect.Type type) {
        init(type);
    }

    private void init(DBConnect.Type type) {
        String propertyName = "";
        switch (type) {
            case DATA:
                propertyName = "dbdata";
                break;
            case CONFIG:
                propertyName = "dbconfig";
                break;
        }
        File file=new File("");
        String path=file.getAbsolutePath()+"/"+"src"+"/";
        String packagePath=this.getClass().getPackage().getName().replace(".", File.separator)+"/";
        String fileName=propertyName+".properties";
        readDBConfigFromPropertyName(path+packagePath+fileName);
    }

    private void readDBConfigFromPropertyName(String name) {
        Properties pro = FileUtil.readProperties(name);
        if (pro != null) {
            this.driveName = pro.getProperty("jdbc.driverName");
            this.url = pro.getProperty("jdbc.url");
            this.user = pro.getProperty("jdbc.user");
            this.password = pro.getProperty("jdbc.password");
        } else {
            throw new LogException("readDBConfigFromPropertyName read null");
        }

        if (StringUtil.emptyOrNull(this.url)) {
            throw new LogException("readDBConfigFromPropertyName url null");
        }

    }
}
