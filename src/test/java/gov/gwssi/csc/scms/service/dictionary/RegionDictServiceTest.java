package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dictionary.DictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.junit.Test;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/23.
 * region代码表测试用例
 */
public class RegionDictServiceTest extends UnitTestBase {
    @Test
    public void getRegionDict() throws NoSuchDictTreeException{
        RegionDictService regionDictService = getBean("regionDictService");
        // 测试获取资源--大洲
        List<DictTreeJson> list =  regionDictService.getRegionDictTreeByLevel("1");
        String jsonData = JsonMapper.getInstance().toJson(list);
        System.out.println(jsonData);
        // 测试获取资源--大洲以及国别
        regionDictService.getRegionDictTreeByLevel("2");
    }
}
