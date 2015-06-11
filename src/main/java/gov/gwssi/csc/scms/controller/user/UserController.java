package gov.gwssi.csc.scms.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.user.Menu;
import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.user.*;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;

    @RequestMapping(value = "/node", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeTree(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            getRootUser(header);

            return nodeService.getNodeTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/node", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Node putNode(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String nodeStr) {
        try {
            User user = getRootUser(header);

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
            User user = getRootUser(header);

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
            User user = getRootUser(header);

            return nodeService.deleteNodeByNodeId(nodeId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/menu", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Menu> getMenuTree(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            getRootUser(header);

            return menuService.getMenuTree();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            getRootUser(header);

            return roleService.getRolesByEnable(Role.ENABLE);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Role putRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String roleStr) {
        try {
            User user = getRootUser(header);

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
            User user = getRootUser(header);

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
            User user = getRootUser(header);

            return roleService.deleteRole(roleId, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{nodeId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<User> getUsers(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String nodeId) {
        try {
            getRootUser(header);

            return userService.getUsersByNode(nodeId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public User putUser(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @RequestBody String UserStr) {
        try {
            User user = getRootUser(header);

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
            User user = getRootUser(header);

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
            User user = getRootUser(header);

            userService.deleteUser(userId, user);
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

    private User getRootUser(String header) throws RequestHeaderError, UserIdentityError, NoSuchUserException {
        Map map = JWTUtil.decode(header);
        if (map == null)
            throw new RequestHeaderError("can not read the header message!");

        Object userId = map.get("userId");
        if (userId == null)
            throw new RequestHeaderError("can not read the invalid message!");

        return userService.checkRootUser(String.valueOf(userId));
    }

}
