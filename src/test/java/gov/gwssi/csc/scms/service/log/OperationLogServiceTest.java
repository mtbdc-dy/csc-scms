package gov.gwssi.csc.scms.service.log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.user.UserService;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Test
    public void queryByAllcondition() throws NoSupportedUserException, ParseException {
        OperationLogService operationLogService = getBean("operationLogService");
        UserService userService = getBean("userService");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userService.getUserByUserIdAndEnable("TsingHua", User.ENABLE);
        List<OperationLog> logs = operationLogService.doQueryWithAllCondition(user,
                sdf.parse("2015-06-01 00:00:00"), sdf.parse("2015-06-15 23:59:59"), "1", "1");

        Assert.assertNotNull(logs);
        System.out.println("user message:" + user.getNode().getNodeId());
        for (OperationLog log : logs) {
            System.out.println(log.getId() + "::" + log.getModule());
        }
    }

    @Test
    public void queryByDatecondition() throws NoSupportedUserException, ParseException, JsonProcessingException {
        OperationLogService operationLogService = getBean("operationLogService");
        UserService userService = getBean("userService");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User user = userService.getUserByUserIdAndEnable("root", User.ENABLE);
        List<OperationLog> logs = operationLogService.doQueryWithOnlyTime(user,
                sdf.parse("2015-06-01 00:00:00"), sdf.parse("2015-06-15 23:59:59"));

        Assert.assertNotNull(logs);
        System.out.println("user message:" + user.getNode().getNodeId());

        ObjectMapper mapper = new ObjectMapper();

        String logStr = mapper.writeValueAsString(logs);

        System.out.println(logStr);
    }

    private List<OperationLog> getList() {
        List<OperationLog> list = new ArrayList<OperationLog>();

        OperationLog op1 = new OperationLog();
        op1.setModule("在校生管理");
        op1.setTableEN("basicInfo");
        //op1.setColunmEN("passportName");
        op1.setBefore("beForeName");
        op1.setAfter("afterName");
        op1.setStudentId("2005042828");
        list.add(op1);

        OperationLog op2 = new OperationLog();
        op2.setModule("离校生管理");
        op2.setTableEN("relatedAddress");
        // op2.setColunmEN("personName");
        op2.setBefore("beForeAddress");
        op2.setAfter("afterAddress");
        op2.setStudentId("2005042828");
        list.add(op2);

        return list;
    }
}
