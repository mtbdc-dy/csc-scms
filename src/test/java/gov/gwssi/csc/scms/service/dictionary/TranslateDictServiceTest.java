package gov.gwssi.csc.scms.service.dictionary;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;

/**
 * Created by WangZhenghua on 2015/4/27.
 * 代码表测试用例
 */
public class TranslateDictServiceTest  extends UnitTestBase {
    @Test
    public void getTranslateDict() throws  NoSuchDictTreeException{
        TranslateDictService translateDictService = getBean("translateDictService");
        translateDictService.getTranslateDictByClassId("B");
    }
}
