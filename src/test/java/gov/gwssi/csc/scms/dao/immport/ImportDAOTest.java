package gov.gwssi.csc.scms.dao.immport;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.importExcle.ImportDao;
import gov.gwssi.csc.scms.utils.ExcelPoiTools;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

/**
 * Created by LiZhiSheng on 2015/8/17.
 */
public class ImportDAOTest  //extends UnitTestBase
{
//    @Test
//    public void testImport() throws Exception {
//        ImportDao codeTableDAO = getBean("importDao");
//        FileItem fileItem = null;
//        Vector<Vector<String>> productData= codeTableDAO.doExcelImport(fileItem);
//        System.out.println(productData.size());
//
//    }
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
    @Test
    public void testReadExcle() throws Exception {
//        ImportDao codeTableDAO = getBean("importDao");
//        File fileItem = new File("");
//        Vector<Vector<String>> productData= codeTableDAO.doExcelImport(fileItem);
//        System.out.println(productData.size());
        System.out.println("-----");
        long start = new Date().getTime();
        System.out.println("start="+start);
        Vector<Vector<String>> list = readFile("C:\\Users\\LiZhiSheng\\Desktop\\V1.0.015(终版)\\工作簿1.xls");
        long end = new Date().getTime();
        System.out.println("end="+end);
        long ss = end-start;
        System.out.println("ss-----"+ss);
        System.out.println("list-----"+list.size());
        //System.out.println("list = " + list);
    }
}
