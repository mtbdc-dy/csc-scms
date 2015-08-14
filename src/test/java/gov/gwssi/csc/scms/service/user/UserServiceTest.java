package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;

import java.util.Date;

/**
 * Created by wangzishi on 15/7/24.
 */
public class UserServiceTest extends UnitTestBase {

    @Test
    public void testUserLogin() throws Exception {
        UserService userService = getBean("userService");
        Date a = new Date();
        for (int i = 0; i < 10; i++) {
            userService.userLogin("root");
        }
        Date b = new Date();
        long c = b.getTime() - a.getTime();
        System.out.println("time for login one User = " + c + "ms");
    }
}