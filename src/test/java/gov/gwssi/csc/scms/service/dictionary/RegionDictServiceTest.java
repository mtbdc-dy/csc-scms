package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;

/**
 * Created by WangZhenghua on 2015/4/23.
 * region代码表测试用例
 */
public class RegionDictServiceTest extends UnitTestBase {
    @Test
    public void getRegionDict(){
        RegionDictService regionDictService = getBean("regionDictService");
        regionDictService.getRegionDictJsonData("1");
        regionDictService.getRegionDictJsonData("2");
    }
}
