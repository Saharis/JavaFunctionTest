package com.virgil.http;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Created by liuwujing on 15/5/7.
 */
public class PropertyTest {
    public static void main(String[] args){
        opProperties();
    }
    private static void opProperties(){
        Properties p=new Properties();
        try {
            InputStream in=new BufferedInputStream(new FileInputStream("/Users/liuwujing/Documents/DBConfig.properties"));

            p.load(in);
            p.getProperty("url");
            OutputStream out=new FileOutputStream("/Users/liuwujing/Documents/DBConfig.properties");
            p.put("testkey","testvalue");
            p.setProperty("key1","value1");
            p.store(out,"");
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){

        }
    }
    private static void opConfig(){
        
    }
}
