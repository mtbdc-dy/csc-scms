package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.repository.dynamicReport.ReportConfigurationRepository;
import org.junit.Test;


/**
 * Created by WangZhenghua on 2015/5/14.
 * dstatistics模块下的单元测试
 */
public class StatisticsServiceTest extends UnitTestBase {

    @Test
    public void testReportConfiguration(){
        ReportConfigurationRepository reportConfigurationRepository = getBean(ReportConfigurationRepository.class);
        reportConfigurationRepository.findAll();
    }

//    // DimTable测试，获取动态查询统计所有表清单
//    @Test
//    public void getDimTable(){
//        DimTableService dimTableService = getBean(DimTableService.class);
//        System.out.println("dimTableService:"+dimTableService);
//        System.out.println(dimTableService.getAllDimTableJsonData());
//    }
//
//    // DimColumn测试，根据英文表名获取表下所有字段
//    @Test
//    public void getDimColumn(){
//        DimColumnService dimColumnService = getBean(DimColumnService.class);
//        System.out.println("dimColumnService:"+dimColumnService);
//        System.out.println(dimColumnService.getDimColumnJsonDataByTableEn("SCMS_D_DISPLAY"));
//    }
//
//    // DConfig测试，根据ID获取DConfig信息
//    @Test
//    public void getDConfig(){
//        DynamicReportService dConfigService = getBean(DynamicReportService.class);
//        List<ReportConfiguration> reportConfigurationList = dConfigService.findDConfigList();
//        if(reportConfigurationList != null){
//            for(ReportConfiguration reportConfiguration : reportConfigurationList){
//                System.out.println(reportConfiguration.getId()+"--"+ reportConfiguration.getConfigType()+"--"+ reportConfiguration.getCreateBy()+"--"+ reportConfiguration.getCreateByName());
//            }
//        }
////        Assert.assertNotNull(dConfigService.findById("1").getConfigType());
////        Assert.assertEquals(dConfigService.findById("1").getConfigType(),"1");
////        System.out.println("DConig "+dConfigService.findById("1").getConfigType());
//    }
//
//    @Test
//    public void getTables() throws NoSuchDictTreeException{
//        DimTableService dimTableService = getBean(DimTableService.class);
//        List<TablesJson> list = dimTableService.getTables();
//        System.out.println(JsonMapper.getInstance().toJson(list));
//    }


    
}
