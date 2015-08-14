package gov.gwssi.csc.scms.service.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.Role;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lei on 2015/5/9.
 */
public class MenuServiceTest extends UnitTestBase {

    private final static String beforePath = "   ";

    MenuService menuService;

    RoleService roleService;

    @Override
    public void before() {
        super.before();
        menuService = getBean(MenuService.class);
        roleService = getBean(RoleService.class);
    }

    @Test
    public void saveMenu() {
        List<Menu> menus = getMenus();

        for (Menu menu : menus)
            menuService.saveMenu(menu);
    }

    @Test
    public void showTree() throws JsonProcessingException {
        List<Menu> menus = menuService.getMenuTree();

//        System.out.println("menus size :: " + menus.size());
//        printMenuTree(menus, "");

        String menuStr = new ObjectMapper().writeValueAsString(menus);
        System.out.println(menuStr);
    }

    @Test
    public void getMenuTreeByRole() throws JsonProcessingException {
//        Role role = roleService.getRoleByRoleIdAndEnable("1", Role.ENABLE);
//        Assert.assertNotNull(role);
//        List<Menu> menus = menuService.getMenuByRole(role);
//        Assert.assertNotNull(menus);

        List<Menu> menus = menuService.getMenuTree();

//        System.out.println("menus size: " + menus.size());
//        printMenuTree(menus, "");

        ObjectMapper mapper = new ObjectMapper();

        String menuTree = mapper.writeValueAsString(menus);
        System.out.println(menuTree);
    }

    private void printMenuTree(Menu root, String path) {
        System.out.print(path);
        System.out.print("|--");
        System.out.println(root.getMenu() + "(" + root.getMenuId() + ")");
    }

    private void printMenuTree(List<Menu> nodes, String path) {
        if (nodes == null || nodes.size() == 0)
            return;
        for (Menu node : nodes) {
            printMenuTree(node, path);
            printMenuTree(node.getChildren(), path + beforePath);
        }
    }

    private List<Menu> getMenus() {
        Menu menu1 = new Menu();
        menu1.setMenuId("M001");
        menu1.setMenu("一级菜单");
        menu1.setMenuType("1");

        Menu menu2 = new Menu();
        menu2.setMenuId("M002");
        menu2.setMenu("一级菜单");
        menu2.setMenuType("1");

        Menu menu3 = new Menu();
        menu3.setMenuId("M003");
        menu3.setMenu("二级菜单");
        menu3.setMenuType("2");
        menu3.setParent(menu1);

        Menu menu4 = new Menu();
        menu4.setMenuId("M004");
        menu4.setMenu("二级菜单");
        menu4.setMenuType("2");
        menu4.setParent(menu1);


        Menu menu5 = new Menu();
        menu5.setMenuId("M005");
        menu5.setMenu("三级菜单");
        menu5.setMenuType("3");
        menu5.setParent(menu4);

        List<Menu> ch1 = new ArrayList<Menu>();
        ch1.add(menu1);
        ch1.add(menu2);
        ch1.add(menu3);
        ch1.add(menu4);
        ch1.add(menu5);

        return ch1;
    }
}
