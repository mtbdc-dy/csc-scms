package gov.gwssi.csc.scms.service.dynamicReport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dynamicReport.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Table;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ColumnRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.TableRepository;
import org.junit.Test;
import org.springframework.data.domain.Page;

import static org.junit.Assert.*;

/**
 * Created by wangzishi on 15/8/31.
 */
public class DynamicReportServiceTest extends UnitTestBase {

    @Test
    public void testGetAllConfigurationsByFilter() throws Exception {
        DynamicReportService dynamicReportService = getBean(DynamicReportService.class);
        Filter filter = new Filter();
        filter.setTitle("大");
        Page<ReportConfiguration> reportConfigurations = dynamicReportService.getAllConfigurationsByFilter(filter);
        System.out.println("reportConfigurations = " + reportConfigurations);
    }

    @Test
    public void test(){
        DynamicReportService dynamicReportService = getBean(DynamicReportService.class);
        dynamicReportService.getColumns();
//        dynamicReportService.getTables();
//        ColumnRepository columnRepository = getBean(ColumnRepository.class);
//        columnRepository.findAll();
    }

    @Test
    public void testGenerateSQL() throws Exception {
        String configJSON = "{\n" +
                "  \"title\": \"测试动态报表统计\",\n" +
                "  \"description\": \"用于测试动态报表统计\",\n" +
                "  \"accessState\": \"1\",\n" +
                "  \"joinConditions\": [{\n" +
                "    \"table\": \"1\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"2\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"3\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }],\n" +
                "  \"whereConditions\": [{\n" +
                "    \"lParenthese\": \"\",\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"21\",\n" +
                "    \"operator\": \"2\",\n" +
                "    \"condition\": \"E0348\",\n" +
                "    \"rParenthese\": \"\",\n" +
                "    \"logic\": \"2\"\n" +
                "  }, {\n" +
                "    \"lParenthese\": \"\",\n" +
                "    \"table\": \"1\",\n" +
                "    \"column\": \"2\",\n" +
                "    \"operator\": \"2\",\n" +
                "    \"condition\": \"M0066\",\n" +
                "    \"rParenthese\": \"\",\n" +
                "    \"logic\": \"\"\n" +
                "  }],\n" +
                "  \"groupConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"18\"\n" +
                "  }, {\n" +
                "    \"table\": \"1\",\n" +
                "    \"column\": \"2\"\n" +
                "  }],\n" +
                "  \"orderConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"18\",\n" +
                "    \"orderType\": \"1\"\n" +
                "  }]\n" +
                "}";
        OriginalConfiguration configuration = new ObjectMapper().readValue(configJSON, OriginalConfiguration.class);
        DynamicReportService service = getBean(DynamicReportService.class);
        String SQL = service.generateSQL(configuration);
        System.out.println("SQL = " + SQL);

    }
}