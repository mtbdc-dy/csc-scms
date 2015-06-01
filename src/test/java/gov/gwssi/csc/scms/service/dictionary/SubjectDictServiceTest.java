package gov.gwssi.csc.scms.service.dictionary;

import com.google.common.collect.Lists;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.dictionary.SubjectDictTreeJson;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.junit.Test;
import java.util.List;
import java.util.Map;

/**
 * Created by WangZhenghua on 2015/4/20.
 * 学科代码表测试用例
 */
public class SubjectDictServiceTest  extends UnitTestBase {

    @Test
    public void getSubject() throws NoSuchDictTreeException{
        SubjectDictService subjectDictService = getBean("subjectDictService");
        // 测试学科代码表--一级层次
        subjectDictService.getSubjectDictTree("1");
        // 测试学科代码表--二级层次
        subjectDictService.getSubjectDictTree("2");
        // 测试学科代码表--三级层次
        subjectDictService.getSubjectDictTree("3");
    }
}
