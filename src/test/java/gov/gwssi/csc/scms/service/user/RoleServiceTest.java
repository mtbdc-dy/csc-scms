package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.user.Role;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by Lei on 2015/5/8.
 */
public class RoleServiceTest extends UnitTestBase {

    private RoleService roleService;

    @Override
    public void before() {
        super.before();
        roleService = getBean("roleService");
    }

    @Test
    public void getAllRoleByEnable() {
        List<Role> roles = roleService.getAllRoleByEnable("1");
        for (Role role : roles)
            System.out.println(role.getRoleId() + "," + role.getRole());
    }

    @Test
    public void getRoleByRoleAndEnable() {
        Role role1 = roleService.getRoleByRoleIdAndEnable("3","0");
        Role role2 = roleService.getRoleByRoleIdAndEnable("3","1");
        Role role3 = roleService.getRoleByRoleIdAndEnable("2","0");
        Role role4 = roleService.getRoleByRoleIdAndEnable("4","1");

        Assert.assertNotNull(role1);
        Assert.assertNull(role2);
        Assert.assertNull(role3);
        Assert.assertNull(role4);
    }
}
