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
		// ��ñ��ָ��ļ����ļ����������ָ�ɵ�С�ļ���������Ŀ¼��
		File parentFile = inFile.getParentFile();

		// ȡ���ļ��Ĵ�С
		long fileLength = inFile.length();
		if (size <= 0) {
			size = fileLength / 2;
		}
		// ȡ�ñ��ָ���С�ļ�����Ŀ
		int num = (fileLength % size != 0) ? (int) (fileLength / size + 1) : (int) (fileLength / size);
		// ��ű��ָ���С�ļ���
		String[] outFileNames = new String[num];
		// �����ļ����������ָ���ļ�
		FileInputStream in = new FileInputStream(inFile);

		// �������ļ����Ŀ�ʼ�ͽ����±�
		long inEndIndex = 0;
		int inBeginIndex = 0;

		// ����Ҫ�ָ����Ŀ����ļ�
		for (int outFileIndex = 0; outFileIndex < num; outFileIndex++) {
			// ����ǰnum - 1��С�ļ�����С��Ϊָ����size
			File outFile = new File(parentFile, DBNAME + outFileIndex + SUFFIX);
			// ����С�ļ��������
			FileOutputStream out = new FileOutputStream(outFile);
			// �������±����size
			inEndIndex += size;
			inEndIndex = (inEndIndex > fileLength) ? fileLength : inEndIndex;
			// ���������ж�ȡ�ֽڴ洢���������
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
