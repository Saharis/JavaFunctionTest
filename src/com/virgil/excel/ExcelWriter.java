package com.virgil.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;

import com.virgil.util.LogUtil;
import com.virgil.util.StringUtil;

/**
 * Created by liuwujing on 15/2/5.
 */
public class ExcelWriter {
    public static void writeWorkBookToFile(String path,Workbook excel){
        if(StringUtil.emptyOrNull(path)){
            LogUtil.logErrorInConsle("EMPTY path");
            return ;
        }
        if(excel==null){
            LogUtil.logErrorInConsle("null excel");
            return ;
        }
        try {
            FileOutputStream out = new FileOutputStream(path);
            excel.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
