package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;

/**
 * Created by WangZhenghua on 2015/4/24.
 * 项目代码表测试用例
 */
public class ProjectDictServiceTest extends UnitTestBase {
    @Test
    public void getProjectDict() throws Exception{
        ProjectDictService projectDictService = getBean("projectDictService");
        // 测试项目代码表：一级层次
        projectDictService.getProjectDictTreeByLevel("1");
        // 测试项目代码表：二级层次
        projectDictService.getProjectDictTreeByLevel("2");
    }
}
