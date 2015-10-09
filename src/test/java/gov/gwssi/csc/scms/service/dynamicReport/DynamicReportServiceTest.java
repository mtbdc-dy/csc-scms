package gov.gwssi.csc.scms.service.dynamicReport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dynamicReport.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Table;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ColumnRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.ConfigurationRepository;
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
        ConfigurationRepository configurationRepository = getBean(ConfigurationRepository.class);
        System.out.println(configurationRepository.findAll());
    }

    @Test
    public void testGenerateSQL() throws Exception {
        String configJSON = "{\n" +
                "  \"title\": \"测试报表\",\n" +
                "  \"description\": \"测试报表描述\",\n" +
                "  \"accessState\": \"1\",\n" +
                "  \"joinConditions\": [{\n" +
                "    \"table\": \"2\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"3\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"4\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"5\",\n" +
                "    \"joinType\": \"1\"\n" +
                "  }],\n" +
                "  \"whereConditions\": [{\n" +
                "    \"lParenthese\": \"\",\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"21\",\n" +
                "    \"operator\": \"2\",\n" +
                "    \"condition\": \"E0356\",\n" +
                "    \"rParenthese\": \"\",\n" +
                "    \"logic\": \"1\"\n" +
                "  }, {\n" +
                "    \"lParenthese\": \"\",\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"21\",\n" +
                "    \"operator\": \"2\",\n" +
                "    \"condition\": \"E0230\",\n" +
                "    \"rParenthese\": \"\",\n" +
                "    \"logic\": \"\"\n" +
                "  }],\n" +
                "  \"groupConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"18\"\n" +
                "  }, {\n" +
                "    \"table\": \"2\",\n" +
                "    \"column\": \"7\"\n" +
                "  }],\n" +
                "  \"orderConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"18\",\n" +
                "    \"orderType\": \"1\"\n" +
                "  }, {\n" +
                "    \"table\": \"2\",\n" +
                "    \"column\": \"7\",\n" +
                "    \"orderType\": \"2\"\n" +
                "  }],\n" +
                "  \"selectConditions\": [{\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"18\",\n" +
                "    \"calculateType\": \"1\",\n" +
                "    \"level\": 1,\n" +
                "    \"sumColumn\": false\n" +
                "  }, {\n" +
                "    \"table\": \"3\",\n" +
                "    \"column\": \"26\",\n" +
                "    \"calculateType\": \"1\",\n" +
                "    \"level\": 2,\n" +
                "    \"sumColumn\": true\n" +
                "  }, {\n" +
                "    \"table\": \"2\",\n" +
                "    \"column\": \"8\",\n" +
                "    \"calculateType\": \"1\",\n" +
                "    \"level\": 1,\n" +
                "    \"sumColumn\": true\n" +
                "  }]\n" +
                "}";
        OriginalConfiguration configuration = new ObjectMapper().readValue(configJSON, OriginalConfiguration.class);
        DynamicReportService service = getBean(DynamicReportService.class);
        String SQL = service.generateSQL(configuration);
        System.out.println("SQL = " + SQL);

    }
}