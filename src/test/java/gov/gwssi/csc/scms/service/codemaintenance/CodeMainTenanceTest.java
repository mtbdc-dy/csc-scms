package gov.gwssi.csc.scms.service.codemaintenance;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by think on 2015/7/13.
 * ����ά��������
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
    @Test
    public void getDetailCodeTest() {
        CodeMainTenanceService codeMainTenanceService = getBean("codeMainTenanceService");
        String allCodeList = null;
       // allCodeList = codeMainTenanceService.findDetailCode("15","","0");
        Assert.assertNotNull(allCodeList);
        System.out.println(allCodeList);
    }
}
