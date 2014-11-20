package com.virgil.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.virgil.excel.model.FieldBreakStack;
import com.virgil.excel.model.ServiceModelInExcel;
import com.virgil.excel.model.StackItem;
import com.virgil.excel.model.TempletField;

public class ExcelReader {
	public ExcelReader() {

	}

	/**
	 * 从传入的文件路径中，读取到这个文件的的所有sheet
	 * 
	 * @param path
	 * @return
	 */
	public static ArrayList<ServiceModelInExcel> getSheetsFromPath(String path) {
		ArrayList<ServiceModelInExcel> servicesSheetList = null;

		try {

			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(new File(path)));

			XSSFSheet sheet_overview = wb.getSheetAt(0);
			XSSFRow row_indexOfOverview = sheet_overview.getRow(0);
			int num_rowNumOfOverview = sheet_overview.getLastRowNum();
			int num_cellOfrow_index = row_indexOfOverview.getLastCellNum();
			int colunm_index_servicecode = -1;
			int colunm_index_servicename = -1;
			int colunm_index_servicedesc = -1;
			for (int i = 0; i < num_cellOfrow_index; i++) {
				if (Config.XLS_SERVICE_CODE.equalsIgnoreCase(row_indexOfOverview.getCell(i).toString())) {
					colunm_index_servicecode = i;
				} else if (Config.XLS_SERVICE_DESC.equalsIgnoreCase(row_indexOfOverview.getCell(i).toString())) {
					colunm_index_servicedesc = i;
				} else if (Config.XLS_SERVICE_NAME.equalsIgnoreCase(row_indexOfOverview.getCell(i).toString())) {
					colunm_index_servicename = i;
				}
			}
			String errorContent = "";
			if (colunm_index_servicecode == -1) {
				errorContent = errorContent + Config.XLS_SERVICE_CODE;
			}
			if (colunm_index_servicename == -1) {
				errorContent = errorContent + Config.XLS_SERVICE_NAME;
			}
			if (colunm_index_servicedesc == -1) {
				errorContent = errorContent + Config.XLS_SERVICE_DESC;
			}
			if (errorContent != null && !"".equals(errorContent)) {
				AlartInfoShow.showInfo("没有找到" + Config.XLS_SERVICE_CODE + "这一列");
				return null;
			} else {
				servicesSheetList = new ArrayList<ServiceModelInExcel>();
				for (int i = 0; i < num_rowNumOfOverview; i++) {
					ServiceModelInExcel model = new ServiceModelInExcel();
					model.serviceCode = getCellContentToString(sheet_overview.getRow(i).getCell(colunm_index_servicecode));
					model.serviceDesc = getCellContentToString(sheet_overview.getRow(i).getCell(colunm_index_servicedesc));
					model.serviceName = getCellContentToString(sheet_overview.getRow(i).getCell(colunm_index_servicename));
					if (model.serviceCode != null) {
						XSSFSheet sheet = wb.getSheet(model.serviceCode);
						if (sheet != null) {
							model.sheet = sheet;
							servicesSheetList.add(model);
						} else {
							continue;
						}
					}
				}

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return servicesSheetList;
	}

	/**
	 * 封装获取Cell转化为String的逻辑
	 * 
	 * @param cell
	 * @return
	 */
	public static String getCellContentToString(XSSFCell cell) {
		String content = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_STRING:
				content = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					content = (cell.getDateCellValue().toString());
				} else {
					content = (cell.getRawValue() + "");
				}
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				break;
			case Cell.CELL_TYPE_FORMULA:
				content = (cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			default:
				break;
			}
		}
		return content;
	}

	/**
	 * 获取MetaData的index
	 * 
	 * @param sheet
	 * @return
	 */
	public static int getMetadataIndex(XSSFSheet sheet) {
		int index = -1;
		if (sheet != null) {
			XSSFRow row = sheet.getRow(0);
			if (row != null) {
				int totalColunmNum = row.getLastCellNum();
				for (int i = 0; i < totalColunmNum; i++) {
					if (Config.MESSAGE_METADATA.equalsIgnoreCase(getCellContentToString(row.getCell(i)))) {
						index = i;
						break;
					} else {
						continue;
					}
				}
			} else {
				// 不做处理
			}
		} else {
			AlartInfoShow.showInfo("getMetadataIndex()传入的参数为null");
		}

		return index;
	}

	/**
	 * 获取Request的总行数
	 * 
	 * @param sheet
	 * @return
	 */
	public static int getToTalRequestRowNum(XSSFSheet sheet) {
		int totalRequestRowNum = -1;
		if (sheet != null) {
			int rowNum = sheet.getLastRowNum();
			for (int i = 0; i < rowNum; i++) {
				String cellContent = getCellContentToString(sheet.getRow(i).getCell(0));
				if (cellContent != null && cellContent.toLowerCase().endsWith("response")) {
					totalRequestRowNum = i - 1;
					break;
				} else {
					continue;
				}
			}
		} else {
			AlartInfoShow.showInfo("getToTalRequestRowNum()传入的参数为null");
		}
		return totalRequestRowNum;
	}

	public static  List<TempletField> getAllRequestField(XSSFSheet sheet) {
		ArrayList<TempletField> templetFieldList = null;
		if (sheet != null) {
			int totalRequestRowNum = getToTalRequestRowNum(sheet);
			int metaDataIndex = getMetadataIndex(sheet);
			if (totalRequestRowNum != -1 && metaDataIndex != -1) {
				templetFieldList = new ArrayList<TempletField>();
				FieldBreakStack stack = new FieldBreakStack();
				int currentColunIndex = Config.COLUMN_NUM_FIELD;

				for (int i = 1; i <= totalRequestRowNum; i++) {
					String metaDataType = getCellContentToString(sheet.getRow(i).getCell(metaDataIndex));

					if (stack.isEmpty()) {
						currentColunIndex = Config.COLUMN_NUM_FIELD;
					} else {
						currentColunIndex = stack.get().columnNum;
					}
					String fieldContent = getCellContentToString(sheet.getRow(i).getCell(currentColunIndex));
					if (fieldContent == null) {
						while (fieldContent == null) {
							TempletField push = stack.get().fieldModel;
							stack.pop();
							if (!stack.isEmpty()) {
								stack.get().fieldModel.listItem.add(push);
							} else {
								templetFieldList.add(push);
							}
							currentColunIndex = currentColunIndex - 1;
							fieldContent = getCellContentToString(sheet.getRow(i).getCell(currentColunIndex));
						}

					}

					if (Config.METADATA_IGNORE.equalsIgnoreCase(metaDataType)) {
						continue;
					} else if (Config.METADATA_LIST.equalsIgnoreCase(metaDataType) || Config.METADATA_NULLABLECLASS.equalsIgnoreCase(metaDataType)) {
						stack.push(new StackItem());
						stack.get().fieldModel.field = getCellContentToString(sheet.getRow(i).getCell(currentColunIndex));
						stack.get().fieldModel.metadata = metaDataType;
						stack.get().fieldModel.listItem = new ArrayList<TempletField>();
						stack.get().columnNum = currentColunIndex + 1;
						continue;
					} else {
						// 读取其他字段
						TempletField filedModel = new TempletField();
						filedModel.metadata = metaDataType;
						filedModel.field = getCellContentToString(sheet.getRow(i).getCell(currentColunIndex));

						if (stack.isEmpty()) {
							templetFieldList.add(filedModel);
						} else {
							stack.get().fieldModel.listItem.add(filedModel);
							continue;
						}

						continue;
					}
				}
				while (!stack.isEmpty()) {
					TempletField push = stack.get().fieldModel;
					stack.pop();
					if (!stack.isEmpty()) {
						stack.get().fieldModel.listItem.add(push);
					} else {
						templetFieldList.add(push);
					}
				}
			} else {
				AlartInfoShow.showInfo("这个sheet没有Request列");
			}

		} else {
			AlartInfoShow.showInfo("getAllRequestField()传入的参数为null");
		}
		return templetFieldList;
	}
}
