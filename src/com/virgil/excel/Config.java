package com.virgil.excel;

public class Config {

	//索引页的字段
	public static final String XLS_SERVICE_CODE="Service Code";
	public static final String XLS_SERVICE_NAME="Service Name";
	public static final String XLS_SERVICE_DESC="Remark";
	
	//报文的字段
	public static final String MESSAGE_NAME="NAME";
	public static final String MESSAGE_SHORT_NAME="Short Name";
	public static final String MESSAGE_TYPE="Type";
	public static final String MESSAGE_METADATA="MetaData";
	public static final String MESSAGE_REMARK="Remark";
	
	//MetaData的用得到的类型
	public static final String METADATA_LIST="List";
	public static final String METADATA_IGNORE="Ignore";
	public static final String METADATA_NULLABLECLASS="NullableClass";
	
	//默认从Excel列1（0开始计数）的内容开始读取
	public static final int COLUMN_NUM_FIELD=1;
	
	//输入的用例编号名称
	public static final String CASE_NO="CASE_NO";
	
	public static final String SPECAIL_FIELD_SERVICEVERSION="ServiceVersion";
	//Excel文件输出目录
	public static String EXCEL_EXPORT_PATH="D:/temp/";
}
