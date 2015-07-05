package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import org.junit.Test;

import gov.gwssi.csc.scms.base.UnitTestBase;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Murray on 2015/4/30.
 */
public class UserTest extends UnitTestBase {

    @Test
    public void initTableTest() {
        System.out.println("the test successfully");
    }

    @Test
    public void saveUser() {

    }

    @Test
    public void userProjectsTest() throws NoSuchUserException {
        UserService userService = getBean(UserService.class);

        User user = userService.getUserByUserIdAndEnable("123", User.ENABLE);

        List<Project> projects = user.getProjects();

        System.out.println(Arrays.toString(projects.toArray()));
    }
}
