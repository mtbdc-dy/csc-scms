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
    public void getSubject(){
        SubjectDictService subjectDictService = getBean("subjectDictService");
        subjectDictService.getSubjectDictJsonData("1");
        subjectDictService.getSubjectDictJsonData("2");
        subjectDictService.getSubjectDictJsonData("3");
    }
}
