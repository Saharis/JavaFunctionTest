package com.virgil.excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Created by liuwujing on 15/2/5.
 */
public class Test {
    public static void main(String[] args) {
        Workbook excel = new XSSFWorkbook();
        Sheet sheet = excel.createSheet();
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("test info");
        try {
            FileOutputStream out = new FileOutputStream("/Users/liuwujing/Documents/hssf.xlsx");
            excel.write(out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
