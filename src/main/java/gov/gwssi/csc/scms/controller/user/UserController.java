package gov.gwssi.csc.scms.controller.user;

import gov.gwssi.csc.scms.domain.user.*;
import gov.gwssi.csc.scms.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Lei on 2015/5/8.
 * 用户权限相关API
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private RightService rightService;

    @Autowired
    private MenuService menuService;

    @RequestMapping(name = "/menu", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Menu> getMenuList() {
        return menuService.getAllMenus();
    }

    @RequestMapping(name = "/role", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRoleList() {
        return roleService.getAllRoleByEnable("1");
    }

    @RequestMapping(name = "/node", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeList() {
        return nodeService.getAllNodeByEnable("1");
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public User login(@RequestParam(value = "username") String userName, @RequestParam("password") String password) {

        System.out.println("one user is login::" + userName + " , " + password);

        try {
            User user = userService.userLogin(userName, password);
            return user;
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return null;
        }
    }

}
