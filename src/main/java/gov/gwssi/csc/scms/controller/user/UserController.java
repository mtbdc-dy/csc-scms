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
    private MenuService menuService;

    @RequestMapping(name = "/menuTree", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Menu> getMenuTree() {
        return menuService.getMenuTree();
    }

    @RequestMapping(name = "/nodeList", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeList() {
        return nodeService.getNodesByEnable("1");
    }

    @RequestMapping(name = "/nodeTree", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeTree() {
        return nodeService.getNodeTree();
    }

    @RequestMapping(name = "/role", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRoleList() {
        return roleService.getRolesByEnable(Role.ENABLE);
    }

    @RequestMapping(name = "/user", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<User> getUserList() {
        return userService.getUsersByEnable(User.ENABLE);
    }

    @RequestMapping(name = "/login", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public User login(@RequestParam(value = "username") String userName, @RequestParam("password") String password) {
        try {
            return userService.userLogin(userName, password);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return null;
        }
    }

}
