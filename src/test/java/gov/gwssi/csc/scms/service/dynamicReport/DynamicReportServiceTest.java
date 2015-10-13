package gov.gwssi.csc.scms.service.dynamicReport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.WhereCondition;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Report;
import gov.gwssi.csc.scms.repository.dynamicReport.ConfigurationRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.WhereConditionRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by wangzishi on 15/8/31.
 */
public class DynamicReportServiceTest extends UnitTestBase {

    @Test
    public void testSaveConfig() throws Exception {
        DynamicReportService service = getBean(DynamicReportService.class);
        ConfigurationRepository repository = getBean(ConfigurationRepository.class);
        WhereConditionRepository whereConditionRepository = getBean(WhereConditionRepository.class);

        Configuration configuration = new Configuration();
        configuration.setId(service.getId());
        configuration.setTitle("lalalala");
        repository.save(configuration);

        WhereCondition condition1 = new WhereCondition();
        WhereCondition condition2 = new WhereCondition();
        condition1.setId(service.getId());
        condition1.setConfig(configuration);
        condition2.setId(service.getId());
        condition2.setConfig(configuration);

        List<WhereCondition> list = new ArrayList<WhereCondition>();
        list.add(condition1);
        list.add(condition2);
        whereConditionRepository.save(list);


//        configuration.setWhereConditions(list);
//        repository.save(configuration);
    }

    @Test
    public void testGenerateSQL() throws Exception {

    }
}