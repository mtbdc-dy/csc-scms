package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.filter.Filter;
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
}