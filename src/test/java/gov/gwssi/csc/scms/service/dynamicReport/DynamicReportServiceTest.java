package gov.gwssi.csc.scms.service.dynamicReport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Cell;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Report;
import gov.gwssi.csc.scms.repository.dynamicReport.ConfigurationRepository;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
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

    }
}