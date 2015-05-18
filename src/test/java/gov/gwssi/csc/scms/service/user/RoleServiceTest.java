package gov.gwssi.csc.scms.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import org.junit.Assert;
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
        List<Role> roles = roleService.getRolesByEnable(Role.ENABLE);
        for (Role role : roles)
            System.out.println(role.getRoleId() + "," + role.getRole());
    }

    @Test
    public void getRoleTest() {
        Role role = roleService.getRoleByRoleId(Role.ENABLE);
        Assert.assertNotNull(role);
    }

    @Test
    public void getRoleMenuTest() {
        Role role = roleService.getRoleByRoleId("1");
        Assert.assertNotNull(role);

        List<Menu> menus = role.getMenus();
        Assert.assertNotNull(menus);

        for (Menu menu : menus) {
            System.out.println("|--" + menu.getMenuId() + "," + menu.getMenu() + "," + menu.getMenuType());
            printChildremMenu(menu.getChildren(), "");
        }
    }

    @Test
    public void getRoleJsonTest() {
        Role role = roleService.getRoleByRoleId("1");
        Assert.assertNotNull(role);

        List<Menu> menus = role.getMenus();
        Assert.assertNotNull(menus);

        ObjectMapper mp = new ObjectMapper();
        String json = null;
        try {
            json = mp.writeValueAsString(role);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("ERROR======");
        }
        System.out.println("Json :" + json);
    }

    private void printChildremMenu(List<Menu> children, String s) {
        String path = "   ";

        if (children == null || children.size() == 0)
            return;

        for (Menu menu : children) {
            String newPath = s + path;
            System.out.println(newPath + "|--" + menu.getMenuId() + "," + menu.getMenu() + "," + menu.getMenuType());
            printChildremMenu(menu.getChildren(), newPath);
        }
    }

    @Test
    public void getRoleByRoleAndEnable() {
        Role role1 = roleService.getRoleByRoleIdAndEnable("1", Role.UNENABLE);
        Role role2 = roleService.getRoleByRoleIdAndEnable("1", Role.ENABLE);

        Assert.assertNull(role1);
        Assert.assertNotNull(role2);
    }
}
