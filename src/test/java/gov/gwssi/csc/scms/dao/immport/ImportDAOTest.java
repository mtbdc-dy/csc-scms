package gov.gwssi.csc.scms.dao.immport;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.importExcle.ImportDao;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.utils.ExcelPoiTools;
import jxl.read.biff.BiffException;
import org.apache.commons.fileupload.FileItem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.hibernate.annotations.SourceType;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import javax.lang.model.SourceVersion;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private String decodeNull(Object o) {
        if (o == null) {
            return "";
        }
        return o.toString().trim();
    }
    public List<String> check(InputStream fileItem) throws IOException {
        List<String> stringList = new ArrayList<String>();
        InputStream inp = fileItem;
        if (!inp.markSupported()) {
            inp = new PushbackInputStream(inp, 8);
        }
        if (!POIFSFileSystem.hasPOIFSHeader(inp)) {
            stringList.add("校验结果：");
            stringList.add("请检验Excel版本，需为excle 97-2003 工作簿（*.xls）！");
            return stringList;
        }
        //Workbook wb =  new HSSFWorkbook(inp);
        jxl.Workbook wb = null;
        try {
            wb=  jxl.Workbook.getWorkbook(inp);
        } catch (BiffException e) {
            e.printStackTrace();
        }
        // 获得该工作区的第一个sheet

        jxl.Sheet sheet = wb.getSheet(0);
        int maxRows = sheet.getRows();
        int maxColumns = sheet.getColumns();
        if(maxRows<=2||maxColumns>3){
            stringList.add("校验结果：");
            stringList.add("导入的数据文件标题不正确！");
            return stringList;
        }
        String cscNo = "";
        String elcregisteNo = "";
        String checkcscNo = "CSC登记号有空行：";
        String cscNoIsNull = "CSC登记号不存在：";
        String checkElcregisteNo = "学籍电子注册号有空行：";
        //Map<String, String> check = new HashMap<String, String>();
        for (int m = 2; m < sheet.getRows(); m++) {

            cscNo = String.valueOf(decodeNull(sheet.getCell(0, m).getContents()));
            elcregisteNo = String.valueOf(decodeNull(sheet.getCell(1, m).getContents()));
            System.out.println("elcregisteNo=="+elcregisteNo);
            //Student student = studentService.getStudentByCscId(cscNo);
            if ("".equals(cscNo)) {
                // stringList.add("第"+(i+1)+"行CSC登记号不能为空,");
                checkcscNo = checkcscNo+"第"+(m+1)+"行,";
            }
//            if ("".equals(elcregisteNo)) {
//                //stringList.add("第"+(i+1)+"行学籍电子注册号不能为空,");
//                checkElcregisteNo = checkElcregisteNo+"第"+(m+1)+"行,";
//            }



        }
        if(!"CSC登记号有空行：".equals(checkcscNo)){
            stringList.add(checkcscNo);
        }
        if(!"CSC登记号不存在：".equals(cscNoIsNull)){
            stringList.add(cscNoIsNull);
        }
        if(!"学籍电子注册号有空行：".equals(checkElcregisteNo)){
            stringList.add(checkElcregisteNo);
        }
        if(stringList.size()==0){
            stringList.clear();
            stringList.add("成功导入");
            System.out.println(stringList);
            return stringList;
        }else if(stringList.size()>15){
            stringList.clear();
            stringList.add("校验结果：");
            stringList.add("错误信息太多");
            stringList.add("请更正后重新导入！");
            System.out.println(stringList);
            return stringList;
        }else{
            stringList.add("<br/>以上是全部错误");
            System.out.println(stringList);
            return stringList;
        }

    }
    @Test
    public void testcheckExcle() throws Exception {
        InputStream inputStream =  new FileInputStream("C:\\Users\\LiZhiSheng\\Desktop\\V1.0.015(终版)\\工作簿1 - 副本.xls");
        List<String> list = check(inputStream);
        System.out.println("list == "+list);
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
