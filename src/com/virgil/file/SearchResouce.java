package com.virgil.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.virgil.excel.ExcelWriter;
import com.virgil.util.StringUtil;

/**
 * Created by liuwujing on 15/2/5.
 */
public class SearchResouce {
    public static void main(String[] args) {
        SearchResouce sr = new SearchResouce();
        sr.start();
    }

    private static String PATH_CODE = "/Users/liuwujing/Documents/SVN/Android/BRANCH_CTRIP_6.3_PAY2/Ctrip_Wireless_View/src/ctrip/base/logical/component/commonview/pay";
    private static String PATH_CODE2 = "/Users/liuwujing/Documents/SVN/Android/BRANCH_CTRIP_6.3_PAY2/CTPay/src/ctrip/android/pay";
    private static String PATH_RES = "/Users/liuwujing/Documents/SVN/Android/BRANCH_CTRIP_6.3_PAY2/Ctrip_Wireless_View/res";
    private static String PATTERN_R_RESOURCE = "R\\.[0-9a-zA-Z\\_\\.]*\\.[0-9a-zA-Z\\_\\.]*";
    private static String PATH_WRITE = "/Users/liuwujing/Documents/Resource.xlsx";
    private HashSet<String> resHashSet = new HashSet<>();
    private HashMap<String, List<String>> dataFile = new HashMap<>();
    private List<String> layoutList = new ArrayList<>();
    private Workbook excel = null;

    private void start() {
        read(PATH_CODE);
        read(PATH_CODE2);
        writeCodeRes(dataFile);
        if (excel != null) {
            ExcelWriter.writeWorkBookToFile(PATH_WRITE, excel);
        }
    }

    private void read(String path) {
        if (!StringUtil.emptyOrNull(path)) {
            File file = new File(path);
            File[] fileList = file.listFiles();
            for (File tempFile : fileList) {
                if (tempFile.isDirectory()) {
                    read(tempFile.getPath());
                } else {
                    if (tempFile.getName().endsWith(".java")) {
                        dataFile.put(tempFile.getName(), readWithScanner(tempFile.getPath()));
                    } else if (tempFile.getName().endsWith(".xml")) {

                    }
                }
            }
        }
    }

    private void initLayoutRes() {

    }

    private void writeCodeRes(HashMap<String, List<String>> data) {

        if (data != null) {
            excel = new XSSFWorkbook();
            Sheet sheet = excel.createSheet();
            int row_index = 0;
            Iterator<Map.Entry<String, List<String>>> iter = data.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, List<String>> item = iter.next();
                sheet.createRow(row_index++).createCell(0).setCellValue(item.getKey());

                Iterator<String> valueIte = item.getValue().iterator();
                while (valueIte.hasNext()) {
                    String valueSt = valueIte.next();
                    Row valueRow = sheet.createRow(row_index++);
                    valueRow.createCell(1).setCellValue(valueSt);
                    valueRow.createCell(2).setCellValue(getRInfo(valueSt, "\\..*\\.").get(0).replace(".", ""));

                }
            }
        }

    }

    private List<String> readWithScanner(String filePath) {
        //Create the file object
        File fileObj = new File(filePath);
        ArrayList<String> infoList = new ArrayList<>();
        try (

                //Scanner object for reading the file
                Scanner scnr = new Scanner(fileObj)
        ) {
            //Reading each line of file using Scanner class
            while (scnr.hasNextLine()) {
                String strLine = scnr.nextLine();
                //print dataFile on console
                List<String> info = getRInfo(strLine, PATTERN_R_RESOURCE);
                for(String temp:info){
                    if (!StringUtil.emptyOrNull(temp) && !temp.contains(".id.")) {
                        if (resHashSet.contains(temp)) {

                        } else {
                            resHashSet.add(temp);
                            if (temp.startsWith("R.layout")) {
                                layoutList.add(temp.replace("R.layout.", "")+".xml");
                            }
                            infoList.add(temp);
                        }
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return infoList;
    }

    private List<String> getRInfo(String info, String pattern) {
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(info);
        List<String> sb = new ArrayList<>();
        while (m.find()) {
            sb.add(m.group());
        }
        return sb;
    }
}
