package gov.gwssi.csc.scms.service;

import gov.gwssi.csc.scms.base.UnitTestBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;
@RunWith(BlockJUnit4ClassRunner.class)
public class UserServiceTest extends UnitTestBase {

//    @Autowired
//    UserService userService;

    @Test
    public void testGetCurrentMaxUserId() throws Exception {
        UserService userService = super.getBean("userService");
        System.out.println(userService.getCurrentMaxUserId());
    }
}