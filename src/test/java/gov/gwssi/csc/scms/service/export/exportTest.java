package gov.gwssi.csc.scms.service.export;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.export.ExportDAO;
import gov.gwssi.csc.scms.service.insurance.InsuranceService;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chen on 2015/8/3.
 */
public class exportTest extends UnitTestBase {


    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testExportservice() throws Exception {
        ExportService exportService = getBean("exportService");
        InsuranceService insuranceService = super.getBean("insuranceService");
        String tablename = "v_scholarship_lastyear";
        String ids = "2015073000000000032,2015073000000000033,2015073000000000034";
        exportService.exportByfilter(tablename, ids);

    }

    @Test
    public void testExportDAO() throws Exception {//查询导出配置信息test
        ExportDAO exportDAO = getBean("exportDAO");
        InsuranceService insuranceService = super.getBean("insuranceService");
        String tablename = "v_scholarship_lastyear";
        String ids = "2015073000000000032,2015073000000000033,2015073000000000034";
        String id[] = ids.split(",");
        String idins = "";
        for (int i = 0; i < id.length; i++) {
            idins = idins + "'" + id[i] + "',";
        }
        List exportList = exportDAO.getExportList(tablename);
        List seachList = exportDAO.getSeachList(tablename);//除标题行外
        String sql = "select ";//查询语句
        String headArray[][] = new String[1][seachList.size()];
        String headArray1[][] = new String[1][seachList.size()];
        String titleExcel = "";//excel标题
        int columnLength[] = new int[seachList.size()];//字段显示宽度
        for (int i = 0; i < exportList.size(); i++) {//全部配置信息
            HashMap map = (HashMap) exportList.get(i);
            if ((map.get("TITLE").toString()).equals("0")) {//excel大标题
                titleExcel = map.get("COLCH").toString();
            }
        }
        for (int i = 0; i < seachList.size(); i++) {//非标题行
            HashMap map = (HashMap) seachList.get(i);
            headArray[0][i] = map.get("COLCH").toString();
            headArray1[0][i] = map.get("COLEN").toString();
            columnLength[i] = Integer.parseInt(map.get("WIDTH").toString());
            sql += map.get("COLEN").toString() + ',';
        }
        sql = sql.substring(0, sql.length() - 1) + " from " + tablename + " where 1=1 ";
        sql = sql + " and id in(" + idins.substring(0, idins.length() - 1) + ")";
        List resultList = exportDAO.getListbysql(sql);
        List<String[]> recordList = new ArrayList<String[]>();//结果集展示数组
        for (int i = 0; i < resultList.size(); i++) {
            HashMap map = (HashMap) resultList.get(i);
            String result[] = new String[headArray1[0].length];
            for (int k = 0; k < headArray1[0].length; k++) {
                String colen = headArray1[0][k].toUpperCase();//map中区分大小写问题
                if (map.get(colen) != null) {
                    result[k] = map.get(colen).toString();
                } else {
                    result[k] = "";
                }
            }
            recordList.add(result);
        }


        System.out.println("exportList.size----" + exportList.size());


    }

    @Test
    public void testExportDAO1() throws Exception {//动态表头，合并行和列
        ExportDAO exportDAO = getBean("exportDAO");
        InsuranceService insuranceService = super.getBean("insuranceService");
        String tablename = "v_scholarship_lastyear1";
        String ids = "2015073000000000032,2015073000000000033,2015073000000000034";
        String id[] = ids.split(",");
        String idins = "";
        for (int i = 0; i < id.length; i++) {
            idins = idins + "'" + id[i] + "',";
        }
        List exportList = exportDAO.getExportList(tablename);
        List seachList = exportDAO.getSeachList(tablename);//除标题行外
        List headarrayList97 = exportDAO.getHeadarrayList97(tablename);//所有97标题行
        List headarrayList98 = exportDAO.getHeadarrayList98(tablename);//所有97标题行
        List mergeList = exportDAO.getMergeList(tablename);//需要合并的标题
        String sql = "select ";//查询语句
        int headarrayint = exportDAO.getHeadarrayInt(tablename);//动态标题行数
        String headArray[][] = new String[headarrayint][seachList.size()];//需要显示的标题行
        String headArray1[][] = new String[1][seachList.size()];//查询的字段，英文，和数据库对应
        String titleExcel = "";//excel标题
        int columnLength[] = new int[seachList.size()];//字段显示宽度
        int mergeArray[][];
        if (mergeList.size() > 0) {//存在需要合并的行或者列
            mergeArray = new int[mergeList.size()][4];
        } else {
            mergeArray = null;
        }
        if (headarrayint == 2) {//98,99
            for (int i = 0; i < mergeList.size(); i++) {//标题行合并，构造合并数组mergeArray
                HashMap map = (HashMap) mergeList.get(i);
                int zb = Integer.parseInt(map.get("SEQ").toString());
                int mergec = 1;
                int mergel = 1;
                if (map.get("MERGECOLUMN") != null) {
                    mergec = Integer.parseInt(map.get("MERGECOLUMN").toString());
                } else if (map.get("MERGELINE") != null) {
                    mergel = Integer.parseInt(map.get("MERGELINE").toString());
                }
                int[] temp = {0, zb, mergel - 1, zb + mergec - 1};
                mergeArray[i] = temp;
            }
            for (int i = 0; i < headarrayList98.size(); i++) {//TITLE=98,构造标题行数组headArray
                HashMap map = (HashMap) headarrayList98.get(i);
                if (map.get("COLCH") != null) {
                    headArray[0][i] = map.get("COLCH").toString();
                } else {
                    headArray[0][i] = "";
                }
            }
        } else if (headarrayint == 3) {
            for (int i = 0; i < mergeList.size(); i++) {//构造合并数组mergeArray
                HashMap map = (HashMap) mergeList.get(i);
                int zb = Integer.parseInt(map.get("SEQ").toString());
                int mergec = 1;
                int mergel = 1;
                if (map.get("MERGECOLUMN") != null) {
                    mergec = Integer.parseInt(map.get("MERGECOLUMN").toString());
                }
                if (map.get("MERGELINE") != null) {
                    mergel = Integer.parseInt(map.get("MERGELINE").toString());
                }
                if (map.get("TITLE").toString().equals("97")) {
                    int[] temp = {0, zb, mergel - 1, zb + mergec - 1};
                    mergeArray[i] = temp;
                }
                if (map.get("TITLE").toString().equals("98")) {
                    int[] temp = {1, zb, 1 + mergel - 1, zb + mergec - 1};
                    mergeArray[i] = temp;

                }
            }

            for (int i = 0; i < headarrayList97.size(); i++) {//TITLE=97,构造标题行数组headArray
                HashMap map = (HashMap) headarrayList97.get(i);
                if (map.get("COLCH") != null) {
                    headArray[0][i] = map.get("COLCH").toString();
                } else {
                    headArray[0][i] = "";
                }
            }
            for (int i = 0; i < headarrayList98.size(); i++) {//TITLE=98,构造标题行数组headArray
                HashMap map = (HashMap) headarrayList98.get(i);
                if (map.get("COLCH") != null) {
                    headArray[1][i] = map.get("COLCH").toString();
                } else {
                    headArray[1][i] = "";
                }
            }
        }

        for (int i = 0; i < exportList.size(); i++) {//全部配置信息
            HashMap map = (HashMap) exportList.get(i);
            if ((map.get("TITLE").toString()).equals("0")) {//excel大标题
                titleExcel = map.get("COLCH").toString();
            }
        }
        for (int i = 0; i < seachList.size(); i++) {//title=99
            HashMap map = (HashMap) seachList.get(i);
            headArray[headarrayint - 1][i] = map.get("COLCH").toString();//TITLE=99,构造标题行数组headArray
            headArray1[0][i] = map.get("COLEN").toString();
            columnLength[i] = Integer.parseInt(map.get("WIDTH").toString());
            sql += map.get("COLEN").toString() + ',';
        }
        sql = sql.substring(0, sql.length() - 1) + " from " + tablename + " where 1=1 ";
        sql = sql + " and id in(" + idins.substring(0, idins.length() - 1) + ")";
        List resultList = exportDAO.getListbysql(sql);
        List<String[]> recordList = new ArrayList<String[]>();//结果集展示数组
        for (int i = 0; i < resultList.size(); i++) {
            HashMap map = (HashMap) resultList.get(i);
            String result[] = new String[headArray1[0].length];
            for (int k = 0; k < headArray1[0].length; k++) {
                String colen = headArray1[0][k].toUpperCase();//map中区分大小写问题
                if (map.get(colen) != null) {
                    result[k] = map.get(colen).toString();
                } else {
                    result[k] = "";
                }
            }
            recordList.add(result);
        }

        String hjh[] = null;// {"合计","3","5","6","7","8","9","10"};


//导出excel
        ExcelExportUtil es = new ExcelExportUtil();
        short excelAlginArray[] = {HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER};
        String dir = "C:/jjw";
        String dirTmp = "C:/jjw/tmp";
        int maxJlsl = 1000;//一个excel中显示的最多纪录数，超过时，分多个进行导出，并压缩打包
        es.writeExcel(titleExcel, recordList, hjh, headArray, mergeArray, columnLength, excelAlginArray, dir, dirTmp, maxJlsl);

    }

    @Test
    public void testExportList() throws Exception {//导出列表test
        String hjh[] = null;// {"合计","3","5","6","7","8","9","10"};
        List<String[]> recordList = new ArrayList<String[]>();
        String result1[] = {"1", "测试a", "测试1", "bmx", "23", "7x", "$20000", "&df"};
        String result2[] = {"2", "测试a", "测试2", "bmx", "23", "7x", "$20000", "&ffsssf"};
        String result3[] = {"3", "测试a", "测试3", "bmx", "23", "7x", "$20000", "&ffsssf"};
        String result4[] = {"4", "测试a", "测试4", "bmx", "23", "7x", "*&%^~~`", "&ffsssf"};
        recordList.add(result1);
        recordList.add(result2);
        recordList.add(result3);
        recordList.add(result4);
        String headArray[][] = {
                {"序号", "a", "b", "", "c", "d", "", ""},
                {"", "a1", "b1", "b2", "c1", "d1", "d3", ""},
                {"", "", "", "b3", "", "d2", "", ""}
        };

        int mergeArray[][] =
                {
                        {0, 0, 2, 0},
                        {0, 2, 0, 3},
                        {0, 5, 0, 7},
                        {1, 1, 2, 1},
                        {1, 2, 2, 2},
                        {1, 4, 2, 4},
                        {1, 6, 2, 7}
                };


//导出excel
        ExcelExportUtil es = new ExcelExportUtil();
        String titleExcel = "测试动态表头";
        int columnLength[] = {3500, 4500, 4500, 4500, 5500, 4500, 4500, 4500};//字段显示宽度
        short excelAlginArray[] = {HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER};
        String dir = "C:/jjw";
        String dirTmp = "C:/jjw/tmp";
        int maxJlsl = 1000;//一个excel中显示的最多纪录数，超过时，分多个进行导出，并压缩打包
        es.writeExcel(titleExcel, recordList, hjh, headArray, mergeArray, columnLength, excelAlginArray, dir, dirTmp, maxJlsl);
        //System.out.println("list size::" + inResultObjectList.size());

    }
}