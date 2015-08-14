package gov.gwssi.csc.scms.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.user.*;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Lei on 2015/5/8.
 * 用户权限相关API
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "/node", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeTree(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            userService.getRootUser(header);

            return nodeService.getNodeTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Node putNode(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String nodeStr) {
        try {
            User user = userService.getRootUser(header);

            Node node = new ObjectMapper().readValue(nodeStr, Node.class);
            node = nodeService.updateNode(node, user);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Node addNode(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String nodeStr) {
        try {
            User user = userService.getRootUser(header);

            Node node = new ObjectMapper().readValue(nodeStr, Node.class);
            node = nodeService.addNode(node, user);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node/{nodeId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Node deleteNode(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String nodeId) {
        try {
            User user = userService.getRootUser(header);

            return nodeService.deleteNodeByNodeId(nodeId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    // GET /menu/{userId}
    //获取所有菜单的API
    @RequestMapping(value = "/menu", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Menu> getMenuTree(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            userService.getRootUser(header);

            return menuService.getMenuTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            userService.getRootUser(header);

            return roleService.getRolesByEnable(Role.ENABLE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Role putRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String roleStr) {
        try {
            User user = userService.getRootUser(header);

            Role role = new ObjectMapper().readValue(roleStr, Role.class);
            return roleService.updateRole(role, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Role addRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String roleStr) {
        try {
            User user = userService.getRootUser(header);

            Role role = new ObjectMapper().readValue(roleStr, Role.class);
            return roleService.addRole(role, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role/{roleId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Role deleteRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String roleId) {
        try {
            User user = userService.getRootUser(header);

            return roleService.deleteRole(roleId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    @RequestMapping(value = "/user/{nodeId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<User> getUsers(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String nodeId) {
        try {
            userService.getRootUser(header);

            return userService.getUsersByNode(nodeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public User putUser(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String UserStr) {
        try {
            User user = userService.getRootUser(header);

            User user1 = new ObjectMapper().readValue(UserStr, User.class);
            return userService.updateUser(user1, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public User addUser(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String userStr) {
        try {
            User user = userService.getRootUser(header);

            User user1 = new ObjectMapper().readValue(userStr, User.class);
            return userService.addUser(user1, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public void deleteUser(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String userId) {
        try {
            User user = userService.getRootUser(header);

            userService.deleteUser(userId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //    GET /login/{userId} USER JSONObject
    @RequestMapping(value = "/login/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public User login(@PathVariable(value = "userId") String userId) {
        try {
            Date a = new Date();
            User user = userService.userLogin(userId);
            Date b = new Date();
            long c = b.getTime() - a.getTime();
            System.out.println("time for login one User = " + c + "ms");
            return user;
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return null;
        }
    }

}
