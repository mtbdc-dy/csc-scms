

package gov.gwssi.csc.scms.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.springframework.util.CollectionUtils;





public class ExcelPoiTools {

	public static Vector<Vector<String>> readFile(String fileName)
			throws FileNotFoundException, IOException {


		Vector<Vector<String>> resultVec = new Vector<Vector<String>>();
		// office2007工作区
		Workbook wb = ExcelPoiTools.create(new FileInputStream(fileName));
		// 获得该工作区的第一个sheet
		Sheet sheet = wb.getSheetAt(0);
		// 总共有多少行,从0开始
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			// 取得该行
			Row row = sheet.getRow(i);
			Vector<String> rowVec = new Vector<String>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				if (row.getCell(j) == null) {
					rowVec.add("");
					continue;
				}
				switch (row.getCell(j).getCellType()) {
				case Cell.CELL_TYPE_STRING:
					rowVec.add(row.getCell(j).getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					// 单元格为整数，读取后变成1.0，删除.0
					Double num = row.getCell(j).getNumericCellValue();
					if ((num + "").endsWith(".0")) {
						rowVec.add(num.intValue() + "");
					} else {
						rowVec.add(num + "");
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					rowVec.add(row.getCell(j).getBooleanCellValue() + "");
					break;
				case Cell.CELL_TYPE_BLANK:
				case Cell.CELL_TYPE_ERROR:
				case Cell.CELL_TYPE_FORMULA:
					rowVec.add("");
					break;
				default:
					break;
				}

			}

			if (!CollectionUtils.isEmpty(rowVec)) {
				resultVec.add(rowVec);
			}
		}
		return resultVec;
	}

	public static List<List<String>> readFile2List(String fileName)
			throws FileNotFoundException, IOException{

		List<List<String>> resultVec = new ArrayList<List<String>>();
		// office2007工作区
		Workbook wb = ExcelPoiTools.create(new FileInputStream(fileName));
		// 获得该工作区的第一个sheet
		Sheet sheet = wb.getSheetAt(0);
		// 总共有多少行,从0开始
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			// 取得该行
			Row row = sheet.getRow(i);
			List<String> rowVec = new ArrayList<String>();
			for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
				if (row.getCell(j) == null) {
					rowVec.add("");
					continue;
				}
				switch (row.getCell(j).getCellType()) {
				case Cell.CELL_TYPE_STRING:
					rowVec.add(row.getCell(j).getStringCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					Double num = row.getCell(j).getNumericCellValue();
					if ((num + "").endsWith(".0")) {
						rowVec.add(num.intValue() + "");
					} else {
						rowVec.add(num + "");
					}
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					rowVec.add(row.getCell(j).getBooleanCellValue() + "");
					break;
				case Cell.CELL_TYPE_BLANK:
				case Cell.CELL_TYPE_ERROR:
				case Cell.CELL_TYPE_FORMULA:
					rowVec.add("");
					break;
				default:
					break;
				}

			}

			if (!CollectionUtils.isEmpty(rowVec)) {
				resultVec.add(rowVec);
			}
		}
		return resultVec;
	}

	public static Workbook create(InputStream inp) throws IOException{
		if (!inp.markSupported()) {
			inp = new PushbackInputStream(inp, 8);
		}
		if (POIFSFileSystem.hasPOIFSHeader(inp)) {
			return new HSSFWorkbook(inp);
		}

		throw new IllegalArgumentException("对不起，无法解析此Excel版本！");
	}

}
