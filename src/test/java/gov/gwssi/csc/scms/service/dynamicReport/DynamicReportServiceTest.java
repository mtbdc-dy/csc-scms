package gov.gwssi.csc.scms.service.dynamicReport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Report;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.ReportBody;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.ReportHead;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.CellRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.ConfigurationRepository;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangzishi on 15/8/31.
 */
public class DynamicReportServiceTest extends UnitTestBase {

//    @Test
//    public void testGetAllConfigurationsByFilter() throws Exception {
//        DynamicReportService dynamicReportService = getBean(DynamicReportService.class);
//        Filter filter = new Filter();
//        filter.setTitle("大");
//        Page<ReportConfiguration> reportConfigurations = dynamicReportService.getAllConfigurationsByFilter(filter);
//        System.out.println("reportConfigurations = " + reportConfigurations);
//    }
//
//    @Test
//    public void test() {
//        ConfigurationRepository configurationRepository = getBean(ConfigurationRepository.class);
//        CellRepository cellRepository = getBean(CellRepository.class);
//
//        configurationRepository.deleteAll();
//        String seqName = "SEQ_D_CFG";
//        Configuration config = new Configuration();
//        config.setTitle("test report");
//
//        config.setId(configurationRepository.newId(seqName));
//        configurationRepository.save(config);
//        List<Cell> cells = new ArrayList<Cell>();
//        Cell cell1 = new Cell(configurationRepository.newId(seqName), 1,1,"lady");
//        Cell cell2 = new Cell(configurationRepository.newId(seqName), 1,1,"gentleman");
//        Cell cell3 = new Cell(configurationRepository.newId(seqName), 1,1,"body");
//        cells.add(cell1);
//        cells.add(cell2);
//        cells.add(cell3);
//
//        cell1.setConfig(config);
//        cell2.setConfig(config);
//        cell3.setConfig(config);
//        cellRepository.save(cells);
////        config.setCells(cells);
//
//
//
//        configurationRepository.findAll();
//    }

    @Test
    public void testGenerateSQL() throws Exception {
        String configJSON = "{\n" +
                "  \"title\": \"测试报表\",\n" +
                "  \"description\": \"测试报表描述\",\n" +
                "  \"accessState\": \"1\",\n" +
                "  \"joinConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"4\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }],\n" +
                "  \"whereConditions\": [{\n" +
                "    \"lParenthese\": \"\",\n" +
                "    \"table\": \"4\",\n" +
                "    \"column\": \"34\",\n" +
                "    \"operator\": \"8\",\n" +
                "    \"condition\": \"12\",\n" +
                "    \"rParenthese\": \"\",\n" +
                "    \"logic\": \"\"\n" +
                "  }],\n" +
                "  \"groupConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"21\"\n" +
                "  }],\n" +
                "  \"orderConditions\": [],\n" +
                "  \"selectConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"20\",\n" +
                "    \"calculateType\": \"1\",\n" +
                "    \"level\": 1,\n" +
                "    \"sumColumn\": true\n" +
                "  }, {\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"18\",\n" +
                "    \"calculateType\": \"1\",\n" +
                "    \"level\": 2,\n" +
                "    \"sumColumn\": true\n" +
                "  }],\n" +
                "  \"rawConfig\": \"{\\\"accessState\\\":\\\"1\\\",\\\"joinConditions\\\":[{\\\"table\\\":{\\\"id\\\":\\\"3\\\",\\\"tableEn\\\":\\\"V_D_BASIC_INFO\\\",\\\"tableCh\\\":\\\"学校－基本信息\\\",\\\"describe\\\":\\\"appr－经费属性\\\",\\\"columns\\\":[{\\\"id\\\":\\\"18\\\",\\\"columnEn\\\":\\\"GENDER\\\",\\\"columnCh\\\":\\\"性别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"genders\\\",\\\"table\\\":null},{\\\"id\\\":\\\"19\\\",\\\"columnEn\\\":\\\"ATTR\\\",\\\"columnCh\\\":\\\"经费属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"fundAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"20\\\",\\\"columnEn\\\":\\\"CONTINENT\\\",\\\"columnCh\\\":\\\"大洲\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents\\\",\\\"table\\\":null},{\\\"id\\\":\\\"21\\\",\\\"columnEn\\\":\\\"COUNTRY\\\",\\\"columnCh\\\":\\\"国籍\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents.countries\\\",\\\"table\\\":null},{\\\"id\\\":\\\"22\\\",\\\"columnEn\\\":\\\"REMARK\\\",\\\"columnCh\\\":\\\"批注\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"23\\\",\\\"columnEn\\\":\\\"PROJECTATTR\\\",\\\"columnCh\\\":\\\"留学项目属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"24\\\",\\\"columnEn\\\":\\\"PROJECTTYPE\\\",\\\"columnCh\\\":\\\"留学项目类别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"25\\\",\\\"columnEn\\\":\\\"PROJECTNAME\\\",\\\"columnCh\\\":\\\"留学项目名称\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes.projectNames\\\",\\\"table\\\":null},{\\\"id\\\":\\\"26\\\",\\\"columnEn\\\":\\\"DISPATCH\\\",\\\"columnCh\\\":\\\"派遣途径\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"dispatches\\\",\\\"table\\\":null},{\\\"id\\\":\\\"27\\\",\\\"columnEn\\\":\\\"BIRTHDAY\\\",\\\"columnCh\\\":\\\"出生日期\\\",\\\"dataType\\\":\\\"TIMESTAMP(6)\\\",\\\"inputType\\\":\\\"日期\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"28\\\",\\\"columnEn\\\":\\\"TRAVELTYPE\\\",\\\"columnCh\\\":\\\"国际旅费\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"travelTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"29\\\",\\\"columnEn\\\":\\\"CHINESENAME\\\",\\\"columnCh\\\":\\\"中文姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"30\\\",\\\"columnEn\\\":\\\"ANNUAL\\\",\\\"columnCh\\\":\\\"年度\\\",\\\"dataType\\\":\\\"NUMBER\\\",\\\"inputType\\\":\\\"数值\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"31\\\",\\\"columnEn\\\":\\\"PASSPORTNAME\\\",\\\"columnCh\\\":\\\"护照姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}]},\\\"joinType\\\":{\\\"id\\\":\\\"1\\\",\\\"joinEn\\\":\\\"join\\\",\\\"joinCh\\\":\\\"等值关联\\\"}},{\\\"table\\\":{\\\"id\\\":\\\"4\\\",\\\"tableEn\\\":\\\"SCMS_GRADE\\\",\\\"tableCh\\\":\\\"学校－成绩信息\\\",\\\"describe\\\":null,\\\"columns\\\":[{\\\"id\\\":\\\"32\\\",\\\"columnEn\\\":\\\"ANNUAL\\\",\\\"columnCh\\\":\\\"年度\\\",\\\"dataType\\\":\\\"NUMBER\\\",\\\"inputType\\\":\\\"数值\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"33\\\",\\\"columnEn\\\":\\\"TERM\\\",\\\"columnCh\\\":\\\"学期\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"34\\\",\\\"columnEn\\\":\\\"GRADE\\\",\\\"columnCh\\\":\\\"成绩\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}]},\\\"joinType\\\":{\\\"id\\\":\\\"1\\\",\\\"joinEn\\\":\\\"join\\\",\\\"joinCh\\\":\\\"等值关联\\\"}}],\\\"whereConditions\\\":[{\\\"condition\\\":\\\"12\\\",\\\"operator\\\":{\\\"id\\\":\\\"8\\\",\\\"operator\\\":\\\"NOT LIKE\\\",\\\"operatorEn\\\":\\\"notlike\\\",\\\"operatorCh\\\":\\\"不相似于\\\"},\\\"table\\\":{\\\"id\\\":\\\"4\\\",\\\"tableEn\\\":\\\"SCMS_GRADE\\\",\\\"tableCh\\\":\\\"学校－成绩信息\\\",\\\"describe\\\":null,\\\"columns\\\":[{\\\"id\\\":\\\"32\\\",\\\"columnEn\\\":\\\"ANNUAL\\\",\\\"columnCh\\\":\\\"年度\\\",\\\"dataType\\\":\\\"NUMBER\\\",\\\"inputType\\\":\\\"数值\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"33\\\",\\\"columnEn\\\":\\\"TERM\\\",\\\"columnCh\\\":\\\"学期\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"34\\\",\\\"columnEn\\\":\\\"GRADE\\\",\\\"columnCh\\\":\\\"成绩\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}]},\\\"column\\\":{\\\"id\\\":\\\"34\\\",\\\"columnEn\\\":\\\"GRADE\\\",\\\"columnCh\\\":\\\"成绩\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}}],\\\"groupConditions\\\":[{\\\"table\\\":{\\\"id\\\":\\\"3\\\",\\\"tableEn\\\":\\\"V_D_BASIC_INFO\\\",\\\"tableCh\\\":\\\"学校－基本信息\\\",\\\"describe\\\":\\\"appr－经费属性\\\",\\\"columns\\\":[{\\\"id\\\":\\\"18\\\",\\\"columnEn\\\":\\\"GENDER\\\",\\\"columnCh\\\":\\\"性别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"genders\\\",\\\"table\\\":null},{\\\"id\\\":\\\"19\\\",\\\"columnEn\\\":\\\"ATTR\\\",\\\"columnCh\\\":\\\"经费属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"fundAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"20\\\",\\\"columnEn\\\":\\\"CONTINENT\\\",\\\"columnCh\\\":\\\"大洲\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents\\\",\\\"table\\\":null},{\\\"id\\\":\\\"21\\\",\\\"columnEn\\\":\\\"COUNTRY\\\",\\\"columnCh\\\":\\\"国籍\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents.countries\\\",\\\"table\\\":null},{\\\"id\\\":\\\"22\\\",\\\"columnEn\\\":\\\"REMARK\\\",\\\"columnCh\\\":\\\"批注\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"23\\\",\\\"columnEn\\\":\\\"PROJECTATTR\\\",\\\"columnCh\\\":\\\"留学项目属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"24\\\",\\\"columnEn\\\":\\\"PROJECTTYPE\\\",\\\"columnCh\\\":\\\"留学项目类别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"25\\\",\\\"columnEn\\\":\\\"PROJECTNAME\\\",\\\"columnCh\\\":\\\"留学项目名称\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes.projectNames\\\",\\\"table\\\":null},{\\\"id\\\":\\\"26\\\",\\\"columnEn\\\":\\\"DISPATCH\\\",\\\"columnCh\\\":\\\"派遣途径\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"dispatches\\\",\\\"table\\\":null},{\\\"id\\\":\\\"27\\\",\\\"columnEn\\\":\\\"BIRTHDAY\\\",\\\"columnCh\\\":\\\"出生日期\\\",\\\"dataType\\\":\\\"TIMESTAMP(6)\\\",\\\"inputType\\\":\\\"日期\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"28\\\",\\\"columnEn\\\":\\\"TRAVELTYPE\\\",\\\"columnCh\\\":\\\"国际旅费\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"travelTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"29\\\",\\\"columnEn\\\":\\\"CHINESENAME\\\",\\\"columnCh\\\":\\\"中文姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"30\\\",\\\"columnEn\\\":\\\"ANNUAL\\\",\\\"columnCh\\\":\\\"年度\\\",\\\"dataType\\\":\\\"NUMBER\\\",\\\"inputType\\\":\\\"数值\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"31\\\",\\\"columnEn\\\":\\\"PASSPORTNAME\\\",\\\"columnCh\\\":\\\"护照姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}]},\\\"column\\\":{\\\"id\\\":\\\"21\\\",\\\"columnEn\\\":\\\"COUNTRY\\\",\\\"columnCh\\\":\\\"国籍\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents.countries\\\",\\\"table\\\":null},\\\"orderType\\\":{\\\"id\\\":\\\"0\\\",\\\"orderCh\\\":\\\"不排序\\\"}}],\\\"selectConditions\\\":[{\\\"caculateType\\\":{\\\"id\\\":\\\"1\\\",\\\"caculateEn\\\":\\\"SC\\\",\\\"caculateCh\\\":\\\"简单计数\\\"},\\\"sumColumn\\\":true,\\\"level\\\":1,\\\"table\\\":{\\\"id\\\":\\\"3\\\",\\\"tableEn\\\":\\\"V_D_BASIC_INFO\\\",\\\"tableCh\\\":\\\"学校－基本信息\\\",\\\"describe\\\":\\\"appr－经费属性\\\",\\\"columns\\\":[{\\\"id\\\":\\\"18\\\",\\\"columnEn\\\":\\\"GENDER\\\",\\\"columnCh\\\":\\\"性别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"genders\\\",\\\"table\\\":null},{\\\"id\\\":\\\"19\\\",\\\"columnEn\\\":\\\"ATTR\\\",\\\"columnCh\\\":\\\"经费属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"fundAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"20\\\",\\\"columnEn\\\":\\\"CONTINENT\\\",\\\"columnCh\\\":\\\"大洲\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents\\\",\\\"table\\\":null},{\\\"id\\\":\\\"21\\\",\\\"columnEn\\\":\\\"COUNTRY\\\",\\\"columnCh\\\":\\\"国籍\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents.countries\\\",\\\"table\\\":null},{\\\"id\\\":\\\"22\\\",\\\"columnEn\\\":\\\"REMARK\\\",\\\"columnCh\\\":\\\"批注\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"23\\\",\\\"columnEn\\\":\\\"PROJECTATTR\\\",\\\"columnCh\\\":\\\"留学项目属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"24\\\",\\\"columnEn\\\":\\\"PROJECTTYPE\\\",\\\"columnCh\\\":\\\"留学项目类别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"25\\\",\\\"columnEn\\\":\\\"PROJECTNAME\\\",\\\"columnCh\\\":\\\"留学项目名称\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes.projectNames\\\",\\\"table\\\":null},{\\\"id\\\":\\\"26\\\",\\\"columnEn\\\":\\\"DISPATCH\\\",\\\"columnCh\\\":\\\"派遣途径\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"dispatches\\\",\\\"table\\\":null},{\\\"id\\\":\\\"27\\\",\\\"columnEn\\\":\\\"BIRTHDAY\\\",\\\"columnCh\\\":\\\"出生日期\\\",\\\"dataType\\\":\\\"TIMESTAMP(6)\\\",\\\"inputType\\\":\\\"日期\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"28\\\",\\\"columnEn\\\":\\\"TRAVELTYPE\\\",\\\"columnCh\\\":\\\"国际旅费\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"travelTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"29\\\",\\\"columnEn\\\":\\\"CHINESENAME\\\",\\\"columnCh\\\":\\\"中文姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"30\\\",\\\"columnEn\\\":\\\"ANNUAL\\\",\\\"columnCh\\\":\\\"年度\\\",\\\"dataType\\\":\\\"NUMBER\\\",\\\"inputType\\\":\\\"数值\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"31\\\",\\\"columnEn\\\":\\\"PASSPORTNAME\\\",\\\"columnCh\\\":\\\"护照姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}]},\\\"column\\\":{\\\"id\\\":\\\"20\\\",\\\"columnEn\\\":\\\"CONTINENT\\\",\\\"columnCh\\\":\\\"大洲\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents\\\",\\\"table\\\":null}},{\\\"caculateType\\\":{\\\"id\\\":\\\"1\\\",\\\"caculateEn\\\":\\\"SC\\\",\\\"caculateCh\\\":\\\"简单计数\\\"},\\\"sumColumn\\\":true,\\\"level\\\":2,\\\"table\\\":{\\\"id\\\":\\\"3\\\",\\\"tableEn\\\":\\\"V_D_BASIC_INFO\\\",\\\"tableCh\\\":\\\"学校－基本信息\\\",\\\"describe\\\":\\\"appr－经费属性\\\",\\\"columns\\\":[{\\\"id\\\":\\\"18\\\",\\\"columnEn\\\":\\\"GENDER\\\",\\\"columnCh\\\":\\\"性别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"genders\\\",\\\"table\\\":null},{\\\"id\\\":\\\"19\\\",\\\"columnEn\\\":\\\"ATTR\\\",\\\"columnCh\\\":\\\"经费属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"fundAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"20\\\",\\\"columnEn\\\":\\\"CONTINENT\\\",\\\"columnCh\\\":\\\"大洲\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents\\\",\\\"table\\\":null},{\\\"id\\\":\\\"21\\\",\\\"columnEn\\\":\\\"COUNTRY\\\",\\\"columnCh\\\":\\\"国籍\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"continents.countries\\\",\\\"table\\\":null},{\\\"id\\\":\\\"22\\\",\\\"columnEn\\\":\\\"REMARK\\\",\\\"columnCh\\\":\\\"批注\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"23\\\",\\\"columnEn\\\":\\\"PROJECTATTR\\\",\\\"columnCh\\\":\\\"留学项目属性\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs\\\",\\\"table\\\":null},{\\\"id\\\":\\\"24\\\",\\\"columnEn\\\":\\\"PROJECTTYPE\\\",\\\"columnCh\\\":\\\"留学项目类别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"25\\\",\\\"columnEn\\\":\\\"PROJECTNAME\\\",\\\"columnCh\\\":\\\"留学项目名称\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"多级代码表\\\",\\\"codeTable\\\":\\\"projectAttrs.projectTypes.projectNames\\\",\\\"table\\\":null},{\\\"id\\\":\\\"26\\\",\\\"columnEn\\\":\\\"DISPATCH\\\",\\\"columnCh\\\":\\\"派遣途径\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"dispatches\\\",\\\"table\\\":null},{\\\"id\\\":\\\"27\\\",\\\"columnEn\\\":\\\"BIRTHDAY\\\",\\\"columnCh\\\":\\\"出生日期\\\",\\\"dataType\\\":\\\"TIMESTAMP(6)\\\",\\\"inputType\\\":\\\"日期\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"28\\\",\\\"columnEn\\\":\\\"TRAVELTYPE\\\",\\\"columnCh\\\":\\\"国际旅费\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"travelTypes\\\",\\\"table\\\":null},{\\\"id\\\":\\\"29\\\",\\\"columnEn\\\":\\\"CHINESENAME\\\",\\\"columnCh\\\":\\\"中文姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"30\\\",\\\"columnEn\\\":\\\"ANNUAL\\\",\\\"columnCh\\\":\\\"年度\\\",\\\"dataType\\\":\\\"NUMBER\\\",\\\"inputType\\\":\\\"数值\\\",\\\"codeTable\\\":null,\\\"table\\\":null},{\\\"id\\\":\\\"31\\\",\\\"columnEn\\\":\\\"PASSPORTNAME\\\",\\\"columnCh\\\":\\\"护照姓名\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"字符串\\\",\\\"codeTable\\\":null,\\\"table\\\":null}]},\\\"column\\\":{\\\"id\\\":\\\"18\\\",\\\"columnEn\\\":\\\"GENDER\\\",\\\"columnCh\\\":\\\"性别\\\",\\\"dataType\\\":\\\"VARCHAR2\\\",\\\"inputType\\\":\\\"代码表\\\",\\\"codeTable\\\":\\\"genders\\\",\\\"table\\\":null}}],\\\"title\\\":\\\"测试报表\\\",\\\"description\\\":\\\"测试报表描述\\\",\\\"orderConditions\\\":[]}\"\n" +
                "}";
        ConfigurationRepository configurationRepository = getBean(ConfigurationRepository.class);
        configurationRepository.deleteAll();
        DynamicReportService service = getBean(DynamicReportService.class);
//        CellRepository cellRepository = getBean(CellRepository.class);

        Configuration configuration = new ObjectMapper().readValue(configJSON, Configuration.class);
        configuration = service.saveNewConfig(configuration);
//        System.out.println("configuration = " + configuration);
        Report report = new Report(new ReportHead(configuration.getCells()), new ReportBody());
        System.out.println("report = " + report);
//        OriginalConfiguration configuration = new ObjectMapper().readValue(configJSON, OriginalConfiguration.class);

//        String SQL = service.generateSQL(configuration);
//        System.out.println("SQL = " + SQL);
    }
}