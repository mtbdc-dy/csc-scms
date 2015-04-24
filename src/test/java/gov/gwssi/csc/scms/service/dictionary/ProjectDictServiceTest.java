package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;

/**
 * Created by WangZhenghua on 2015/4/24.
 * 项目代码表测试用例
 */
public class ProjectDictServiceTest extends UnitTestBase {
    @Test
    public void getProjectDict(){
        ProjectDictService projectDictService = getBean("projectDictService");
        projectDictService.getProjectDictJsonData("1");
        projectDictService.getProjectDictJsonData("2");
    }
}
