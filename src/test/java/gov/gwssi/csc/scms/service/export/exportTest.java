package gov.gwssi.csc.scms.service.export;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
        String[] tableName = {"v_sheet1_basic_info" ,
                "v_sheet2_profiles_history" ,
                "v_sheet3_registration_info" ,
                "v_sheet4_discuss" ,
                "v_sheet5_schoolroll" ,
                "v_sheet6_related_address",
                "v_sheet7_accident","v_sheet8_airticket","v_sheet9_grade","v_sheet10_school_fellow"};
        String ids = "csc000000075,csc000000076,csc000000077";
        String id[] = ids.split(",");
        exportService.exportByFilter(tableName, "0",id);

    }
    @Test
    public void testExportservice1() throws Exception {
        ExportService exportService = getBean("exportService");
        String tableName = "v_exp_appropriation" ;
        String ids = "201509079782";
        String id[] = ids.split(",");
        exportService.exportByFilter(tableName,"0", id);

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
        int columnLength[] = {500, 1000, 4500, 4500, 5500, 4500, 4500, 4500};//字段显示宽度
        short excelAlginArray[] = {HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_RIGHT, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER, HSSFCellStyle.ALIGN_CENTER};
        String dir = "C:/jjw";
        String dirTmp = "C:/jjw/tmp";
        int maxJlsl = 1000;//一个excel中显示的最多纪录数，超过时，分多个进行导出，并压缩打包
        es.writeExcel(titleExcel, recordList, hjh, headArray, mergeArray, columnLength, excelAlginArray, dir, dirTmp, maxJlsl);
        //System.out.println("list size::" + inResultObjectList.size());

    }
}
