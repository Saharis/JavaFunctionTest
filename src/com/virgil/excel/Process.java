package com.virgil.excel;

import java.util.ArrayList;
import java.util.List;

import com.virgil.excel.model.ServiceModelInExcel;
import com.virgil.excel.model.TempletField;

public class Process {

	public static void main(String[] args) {
		doThis();
	}

	public static void doThis() {
		ArrayList<ServiceModelInExcel> sheetModelList = ExcelReader.getSheetsFromPath("D:/服务接口 - 国内机票V2.xlsx");
		for (ServiceModelInExcel temp : sheetModelList) {
			doThat(temp);
		}
	}

	private static void doThat(final ServiceModelInExcel temp) {
		new Thread(new Runnable() {

			@Override
			public void run() {
				List<TempletField> list = ExcelReader.getAllRequestField(temp.sheet);
				TempleteFieldExporter.writeSheetToExcel(temp, list);
				TempleteFieldExporter.writePropertiesFile(temp, list);
				TempleteFieldExporter.writeFTLFile(temp, list);
			}
		}).start();
	}
}
