package com.virgil.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.csvreader.CsvWriter;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.virgil.excel.model.ServiceModelInExcel;
import com.virgil.excel.model.TempletField;
import com.virgil.util.FileUtil;
import com.virgil.util.StringUtil;

public class TempleteFieldExporter {

	public static void writeSheetToExcel(ServiceModelInExcel excel, List<TempletField> dataList) {
		try { 
			ArrayList<ServiceModelInExcel> sheetModelList = ExcelReader.getSheetsFromPath("D:/服务接口 - 国内机票V2.xlsx");
			List<TempletField> list = ExcelReader.getAllRequestField(sheetModelList.get(4).sheet);
			ArrayList<String> nodes = new ArrayList<String>();
			nodes.add(Config.CASE_NO);
			for (TempletField temp : list) {
				nodes.addAll(TempleteFieldExporter.cycleTreeNodes(temp, -1, ""));
			}
			String csvFilePath = Config.EXCEL_EXPORT_PATH + excel.serviceName + "Request.csv";  
	         CsvWriter wr =new CsvWriter(csvFilePath,',',Charset.forName("UTF-8"));  
	         String[] contents =new String[nodes.size()];   
	         for(int i=0;i<nodes.size();i++){
	        	 contents[i]=nodes.get(i);
	         }
	         wr.writeRecord(contents);  
	         wr.close();   
        } catch (FileNotFoundException e) { 
            // 捕获File对象生成时的异常 
            e.printStackTrace(); 
        } catch (IOException e) { 
            // 捕获BufferedWriter对象关闭时的异常 
            e.printStackTrace(); 
        } 
	
	}

	public static ArrayList<String> cycleTreeNodes(TempletField field, int suffix, String fatherNode) {
		ArrayList<String> nodes = new ArrayList<String>();
		if (Config.METADATA_LIST.equalsIgnoreCase(field.metadata)) {
			for (TempletField temp : field.listItem) {
				nodes.addAll(cycleTreeNodes(temp, 1, field.field));
			}
			for (TempletField temp : field.listItem) {
				nodes.addAll(cycleTreeNodes(temp, 2, field.field));
			}
		} else if (Config.METADATA_NULLABLECLASS.equalsIgnoreCase(field.metadata)) {
			for (TempletField temp : field.listItem) {
				nodes.addAll(cycleTreeNodes(temp, -1, field.field));
			}
		} else {
			String nodeName = "";
			if (!StringUtil.emptyOrNull(fatherNode)) {
				nodeName = (fatherNode + "_" + field.field);
			} else {
				nodeName = (field.field);
			}
			if (suffix != -1) {
				nodeName = (nodeName + "-" + suffix);
			} else {
				// nodeName=(nodeName);
			}
			nodes.add(nodeName);
		}
		return nodes;
	}

	public static ArrayList<String> cycleTreeNodesWithoutSuffic(TempletField field, String fatherNode) {
		ArrayList<String> nodes = new ArrayList<String>();
		if (Config.METADATA_LIST.equalsIgnoreCase(field.metadata)) {
			for (TempletField temp : field.listItem) {
				nodes.addAll(cycleTreeNodesWithoutSuffic(temp, field.field));
			}
		} else if (Config.METADATA_NULLABLECLASS.equalsIgnoreCase(field.metadata)) {
			for (TempletField temp : field.listItem) {
				nodes.addAll(cycleTreeNodesWithoutSuffic(temp, field.field));
			}
		} else {
			String nodeName = "";
			if (!StringUtil.emptyOrNull(fatherNode)) {
				nodeName = (fatherNode + "_" + field.field);
			} else {
				nodeName = (field.field);
			}
			nodes.add(nodeName);
		}
		return nodes;
	}

	public static void writePropertiesFile(ServiceModelInExcel excel, List<TempletField> dataList) {
		Properties properties = new Properties();
		try {
			ArrayList<String> nodes = new ArrayList<String>();
			for (TempletField temp : dataList) {
				nodes.addAll(cycleTreeNodesWithoutSuffic(temp, ""));
			}
			OutputStream outputStream = new FileOutputStream(Config.EXCEL_EXPORT_PATH + excel.serviceName + "Request.properties");
			for (String temp : nodes) {
				properties.setProperty(temp, "");
			}
			properties.store(outputStream, "author: liuwj@ctrip.com");
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFTLFile(ServiceModelInExcel excel,List<TempletField> dataList){
		FileUtil.writeToFile(TempleteFieldExporter.toJson(dataList).toString(), Config.EXCEL_EXPORT_PATH + excel.serviceName+"Request.ftl", false);

	}

	public static JsonObject toJson(List<TempletField> dataList) {
		JsonObject jsonObject=new JsonObject();
		for (TempletField tempField : dataList) {
			cycleList(jsonObject, tempField, -1, "");
		}
		return jsonObject;
	}

	public static JsonObject cycleList(JsonObject jsonObject, TempletField templetField, int suffix, String fatherNode) {
		if (Config.METADATA_NULLABLECLASS.equalsIgnoreCase(templetField.metadata)) {
			JsonObject jsonClass = new JsonObject();
			for (TempletField temp : templetField.listItem) {
				jsonClass = cycleList(jsonClass, temp, -1, templetField.field);
			}
			jsonObject.add(templetField.field, jsonClass);
		} else if (Config.METADATA_LIST.equalsIgnoreCase(templetField.metadata)) {
			JsonObject jsonClass = new JsonObject();
			for (TempletField temp : templetField.listItem) {
				jsonClass = cycleList(jsonClass, temp, 1, templetField.field);
			}
			JsonObject jsonClass2 = new JsonObject();
			for (TempletField temp : templetField.listItem) {
				jsonClass2 = cycleList(jsonClass2, temp, 2, templetField.field);
			}
			JsonArray array = new JsonArray();
			array.add(jsonClass);
			array.add(jsonClass2);
			jsonObject.add(templetField.field, array);
		} else {
			String fieldResult = "";
			if (StringUtil.emptyOrNull(fatherNode)) {
				fieldResult = templetField.field;
			} else {
				fieldResult = fatherNode + "_" + templetField.field;
			}
			if (suffix != -1) {
				fieldResult = fieldResult + "-" + suffix;
			}
			if(Config.SPECAIL_FIELD_SERVICEVERSION.equalsIgnoreCase(templetField.field)){
				jsonObject.addProperty(templetField.field, "0");
			}else{
				jsonObject.addProperty(fieldResult, "${" + fieldResult + "}");	
			}
		}
		return jsonObject;
	}
}
