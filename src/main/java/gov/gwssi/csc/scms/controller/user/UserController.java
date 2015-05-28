package gov.gwssi.csc.scms.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
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

    @RequestMapping(value = "/node/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeTree(@PathVariable String userId) {
        try {
            if (userService.checkRootUser(userId) == null)
                throw new UserIdentityError("not root user!");
            else
                return nodeService.getNodeTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Node putNode(@PathVariable String userId, @RequestBody String nodeStr) {
        try {
            User user = userService.checkRootUser(userId);

            Node node = new ObjectMapper().readValue(nodeStr, Node.class);
            node = nodeService.updateNode(node, user);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node/{userId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Node addNode(@PathVariable String userId, @RequestBody String nodeStr) {
        try {
            User user = userService.checkRootUser(userId);

            Node node = new ObjectMapper().readValue(nodeStr, Node.class);
            node = nodeService.addNode(node, user);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node/{userId}/{nodeId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Node deleteNode(@PathVariable String userId, @PathVariable String nodeId) {
        try {
            User user = userService.checkRootUser(userId);

            return nodeService.deleteNodeByNodeId(nodeId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/menu/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Menu> getMenuTree(@PathVariable String userId) {
        try {
            userService.checkRootUser(userId);

            return menuService.getMenuTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRole(@PathVariable String userId) {
        try {
            userService.checkRootUser(userId);

            return roleService.getRolesByEnable(Role.ENABLE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Role putRole(@PathVariable String userId, @RequestBody String roleStr) {
        try {
            User user = userService.checkRootUser(userId);

            Role role = new ObjectMapper().readValue(roleStr, Role.class);
            return roleService.updateRole(role, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role/{userId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Role addRole(@PathVariable String userId, @RequestBody String roleStr) {
        try {
            User user = userService.checkRootUser(userId);

            Role role = new ObjectMapper().readValue(roleStr, Role.class);
            return roleService.addRole(role, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role/{userId}/{roleId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Role deleteRole(@PathVariable String userId, @PathVariable String roleId) {
        try {
            User user = userService.checkRootUser(userId);

            return roleService.deleteRole(roleId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{userId}/{nodeId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<User> getUsers(@PathVariable String userId,@PathVariable String nodeId) {
        try {
            userService.checkRootUser(userId);

            return userService.getUsersByNode(nodeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public User putUser(@PathVariable String userId, @RequestBody String UserStr) {
        try {
            User user = userService.checkRootUser(userId);

            User user1 = new ObjectMapper().readValue(UserStr, User.class);
            return userService.updateUser(user1,user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public User addUser(@PathVariable String userId, @RequestBody String userStr) {
        try {
            User user = userService.checkRootUser(userId);

            User user1 = new ObjectMapper().readValue(userStr, User.class);
            return userService.addUser(user1,user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{userId}/{roleId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public User deleteUser(@PathVariable String userId, @PathVariable String id) {
        try {
            User user = userService.checkRootUser(userId);
            return userService.deleteUser(id,user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public User login(@RequestParam(value = "username") String userName, @RequestParam("password") String password) {
        try {
            return userService.userLogin(userName, password);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return null;
        }
    }

}
