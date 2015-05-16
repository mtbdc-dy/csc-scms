package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.statistics.DConfig;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;


/**
 * Created by WangZhenghua on 2015/5/14.
 * dstatistics模块下的单元测试
 */
public class StatisticsServiceTest extends UnitTestBase {

    // DimTable测试，获取动态查询统计所有表清单
    @Test
    public void getDimTable(){
        DimTableService dimTableService = getBean(DimTableService.class);
        System.out.println("dimTableService:"+dimTableService);
        System.out.println(dimTableService.getAllDimTableJsonData());
    }

    // DimColumn测试，根据英文表名获取表下所有字段
    @Test
    public void getDimColumn(){
        DimColumnService dimColumnService = getBean(DimColumnService.class);
        System.out.println("dimColumnService:"+dimColumnService);
        System.out.println(dimColumnService.getDimColumnJsonDataByTableEn("SCMS_D_DISPLAY"));
    }

    // DConfig测试，根据ID获取DConfig信息
    @Test
    public void getDConfig(){
        DConfigService dConfigService = getBean(DConfigService.class);
        List<DConfig> dConfigList = dConfigService.findDConfigList();
        if(dConfigList != null){
            for(DConfig dConfig : dConfigList){
                System.out.println(dConfig.getId()+"--"+dConfig.getConfigType()+"--"+dConfig.getCreateBy());
            }
        }
//        Assert.assertNotNull(dConfigService.findById("1").getConfigType());
//        Assert.assertEquals(dConfigService.findById("1").getConfigType(),"1");
//        System.out.println("DConig "+dConfigService.findById("1").getConfigType());
    }


    
}
