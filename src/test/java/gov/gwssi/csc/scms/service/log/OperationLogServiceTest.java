package gov.gwssi.csc.scms.service.log;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 2015/4/27.
 */
public class OperationLogServiceTest extends UnitTestBase {

    @Test
    public void saveLog() {
        OperationLogService operationLogService = getBean("operationLogService");
        List<OperationLog> list = operationLogService.saveOperationLog(getList());
        Assert.assertNotNull(list);
    }

    private List<OperationLog> getList() {
        List<OperationLog> list = new ArrayList<OperationLog>();

        OperationLog op1 = new OperationLog();
        op1.setMenu("在校生管理");
        op1.setTableEN("basicInfo");
        //op1.setColunmEN("passportName");
        op1.setBefore("beForeName");
        op1.setAfter("afterName");
        op1.setStudentId("2005042828");
        list.add(op1);

        OperationLog op2 = new OperationLog();
        op2.setMenu("离校生管理");
        op2.setTableEN("relatedAddress");
        // op2.setColunmEN("personName");
        op2.setBefore("beForeAddress");
        op2.setAfter("afterAddress");
        op2.setStudentId("2005042828");
        list.add(op2);

        return list;
    }
}
