package com.virgil.fileduplicate;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class DealWithIOS {
	public static final String FOLDER_PATH_ORGIN="D:\\workspace\\CTRIP_WIRELESS_IOS\\CtripWireless_Business\\CtripWireless_Business\\classes\\business\\";
	public static final String[] PATH_LIST={"flight\\","flightCommon\\","intFlight\\"};

	public static WritableWorkbook workbook;
	public static void main(String[] args) {
		ArrayList<FileModel> nameList = new ArrayList<FileModel>();
		for (int i = 0; i < PATH_LIST.length; i++) {
			getFileList(nameList, new File(FOLDER_PATH_ORGIN + PATH_LIST[i]));
		}
		HashMap<String, String> nameMap = new HashMap<String, String>();
		ArrayList<String[]> duList=new ArrayList<String[]>();
		for (FileModel temp : nameList) {
			if (nameMap.containsKey(temp.fileName)) {
				duList.add(new String[]{temp.fileName,temp.filePath,nameMap.get(temp.fileName)});
			} else {
				nameMap.put(temp.fileName, temp.filePath);
			}
		}

		try {
			workbook = Workbook.createWorkbook(new File("D:\\test.xls"));
			WritableSheet summarySheet = workbook.createSheet("重名信息", 0);
			summarySheet.addCell(new Label(0, 0, "文件名"));
			summarySheet.addCell(new Label(1, 0, "路径1"));
			summarySheet.addCell(new Label(1, 0, "路径2"));
			int startRange=0;
			int startColumn=0;
			for(String[] temp:duList){
				startRange++;
				startColumn=0;
				summarySheet.addCell(new Label(startColumn++, startRange, temp[0]));
				summarySheet.addCell(new Label(startColumn++, startRange, temp[1]));
				summarySheet.addCell(new Label(startColumn++, startRange, temp[2]));
			}
			workbook.write();
			workbook.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void getFileList(ArrayList<FileModel> nameList, File file) {
		if (file.isDirectory()) {
			File[] childFile = file.listFiles();
			for (File temp : childFile) {
				getFileList(nameList, temp);
			}
		} else {
			if(file.getName().endsWith(".h")){
				nameList.add(new FileModel(file.getName(), file.getAbsolutePath().replace(FOLDER_PATH_ORGIN, "")));
			}
		}
	}
}
