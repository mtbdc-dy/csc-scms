package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by think on 2015/7/13.
 * ¥˙¬ÎŒ¨ª§≤‚ ‘¿‡
 */
public class CodeMainTenanceTest extends UnitTestBase {
    @Test
    public void getAllCodeTest() {
        CodeMainTenanceService codeMainTenanceService = getBean("codeMainTenanceService");
        List allCodeList = null;
        allCodeList = codeMainTenanceService.findAllCode("","");
        Assert.assertNotNull(allCodeList);
        System.out.println(allCodeList.size());
    }
}
