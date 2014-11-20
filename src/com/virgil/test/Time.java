package com.virgil.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.csvreader.CsvWriter;
import com.virgil.excel.ExcelReader;
import com.virgil.excel.TempleteFieldExporter;
import com.virgil.excel.model.ServiceModelInExcel;
import com.virgil.excel.model.TempletField;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Time {
	public int i = 1;

	public static void main(String[] args) {
		try { 
			ArrayList<ServiceModelInExcel> sheetModelList = ExcelReader.getSheetsFromPath("D:/服务接口 - 国内机票V2.xlsx");
			List<TempletField> list = ExcelReader.getAllRequestField(sheetModelList.get(4).sheet);
			ArrayList<String> nodes = new ArrayList<String>();
			for (TempletField temp : list) {
				nodes.addAll(TempleteFieldExporter.cycleTreeNodes(temp, -1, ""));
			}
			String csvFilePath = "c:/test.csv";  
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

	private void doTest() {
		TempletField te = new TempletField();
		te.field = "1";
		te.metadata = "2";
		cycleAddElement(te);
		te.listItem.listIterator();
	}

	private void cycleAddElement(TempletField temp) {
		temp.field = ++i + "";
		temp.metadata = ++i + "";
		temp.listItem = new ArrayList<TempletField>();
		temp.listItem.add(new TempletField());
		while (i < 50) {
			cycleAddElement(temp.listItem.get(0));
		}
	}

	public static void writePropertiesFile(String filename) {
		Properties properties = new Properties();
		try {
			OutputStream outputStream = new FileOutputStream(filename);
			properties.setProperty("username", "myname");
			properties.setProperty("password", "mypassword");
			properties.setProperty("chinese", "中文");
			properties.store(outputStream, "author: liuwj@ctrip.com");
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void opFTL() throws IOException, TemplateException {
		Configuration cfg = new Configuration();
		// 'somePath' should be the the absolute path of the directory of
		// 'template.ftl'
		File f = new File("D:/temp/");
		cfg.setDirectoryForTemplateLoading(new File("D:/temp/"));
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		Template temp = cfg.getTemplate("1.ftl");
		Map root = new HashMap();
		root.put("tripType", "RT");
		root.put("sendTicketCityCode", "SHA");
		// you can run the java class and see the output in this html-file
		System.setOut(new PrintStream(new File("D:/temp/3.ftl")));
		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
		out.flush();
	}
}
