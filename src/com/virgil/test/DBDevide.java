package com.virgil.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class DBDevide {
	public static final String SUFFIX = ".db";
	public static final String DBNAME = "ctripdb";

	public static void main(String[] args) throws Exception {
		divide("E:/ctrip.db", 903* 1024);
	}

	public static String[] divide(String fileName, long size) throws Exception {

		File inFile = new File(fileName);
		if (!inFile.exists() || (!inFile.isFile())) {
		}
		// 锟斤拷帽锟斤拷指锟斤拷募锟斤拷锟斤拷募锟斤拷锟斤拷锟斤拷锟斤拷锟斤拷指锟缴碉拷小锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷目录锟斤拷
		File parentFile = inFile.getParentFile();

		// 取锟斤拷锟侥硷拷锟侥达拷小
		long fileLength = inFile.length();
		if (size <= 0) {
			size = fileLength / 2;
		}
		// 取锟矫憋拷锟街革拷锟斤拷小锟侥硷拷锟斤拷锟斤拷目
		int num = (fileLength % size != 0) ? (int) (fileLength / size + 1) : (int) (fileLength / size);
		// 锟斤拷疟锟斤拷指锟斤拷锟叫★拷募锟斤拷锟�
		String[] outFileNames = new String[num];
		// 锟斤拷锟斤拷锟侥硷拷锟斤拷锟斤拷锟斤拷锟斤拷锟街革拷锟斤拷募锟�
		FileInputStream in = new FileInputStream(inFile);

		// 锟斤拷锟斤拷锟斤拷锟侥硷拷锟斤拷锟侥匡拷始锟酵斤拷锟斤拷锟铰憋拷
		long inEndIndex = 0;
		int inBeginIndex = 0;

		// 锟斤拷锟斤拷要锟街革拷锟斤拷锟侥匡拷锟斤拷锟侥硷拷
		for (int outFileIndex = 0; outFileIndex < num; outFileIndex++) {
			// 锟斤拷锟斤拷前num - 1锟斤拷小锟侥硷拷锟斤拷锟斤拷小锟斤拷为指锟斤拷锟斤拷size
			File outFile = new File(parentFile, DBNAME + outFileIndex + SUFFIX);
			// 锟斤拷锟斤拷小锟侥硷拷锟斤拷锟斤拷锟斤拷锟�
			FileOutputStream out = new FileOutputStream(outFile);
			// 锟斤拷锟斤拷锟斤拷锟铰憋拷锟斤拷锟絪ize
			inEndIndex += size;
			inEndIndex = (inEndIndex > fileLength) ? fileLength : inEndIndex;
			// 锟斤拷锟斤拷锟斤拷锟斤拷锟叫讹拷取锟街节存储锟斤拷锟斤拷锟斤拷锟斤拷锟�
			for (; inBeginIndex < inEndIndex; inBeginIndex++) {
				out.write(in.read());
			}
			out.close();
			outFileNames[outFileIndex] = outFile.getAbsolutePath();
		}
		in.close();
		return outFileNames;
	}

}
