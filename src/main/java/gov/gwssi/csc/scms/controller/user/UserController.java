package gov.gwssi.csc.scms.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.user.*;
import gov.gwssi.csc.scms.service.user.*;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ProjectService projectService ;

    @RequestMapping(value = "/node", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeTree(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            userService.getRootUser(header);
            List<Node> nodes = nodeService.getNodeTree();
            nodeService.setParentNull(nodes);
            return nodes;
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
            node.setChildren(null);
            node.setParent(null);
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
            node.setChildren(null);
            node.setParent(null);
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
            Node node = nodeService.deleteNodeByNodeId(nodeId, user);
            node.setChildren(null);
            node.setParent(null);
            return node;
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
            List<Menu> root = menuService.getMenuTree();
            return root;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/menu/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Menu> getMenusByUserId(@PathVariable String userId) {
        try {
            User user = userService.getUserByUserId(userId);
            Role role = user.getRole();
            List<Menu> menus = menuService.getMenuByRole(role);
            return menus;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            userService.getRootUser(header);
            List<Role> roles = roleService.getRolesByEnable(Role.ENABLE);
            return roles;
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
            role = roleService.updateRole(role, user);
            return role;
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
    public Map<String,String> deleteRole(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String roleId) {
        try {
            User user = userService.getRootUser(header);
            Map<String,String> result = roleService.deleteRole(roleId, user);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/user/{nodeId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<User> getUsers(@RequestHeader(value = HEADER_AUTHORIZATION) String header, @PathVariable String nodeId) {
        try {
            userService.getRootUser(header);

            List<User> users = userService.getUsersByNode(nodeId);
            userService.setUserNull(users);
            for(User user:users){
                projectService.setParentNull(user.getProjects());
            }
            return users;
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
            User userRet = userService.updateUser(user1, user);
            userService.setUserNull(userRet);
            return userRet;
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
            User userRet = userService.addUser(user1, user);
            userService.setUserNull(userRet);
            return userRet;
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
    public PwdToken login(@PathVariable(value = "userId") String userId) {
        try {
//            Date a = new Date();
//            String pwd = userService.userLogin(userId);
//            Map<String, String> map = new HashMap<String, String>();
//            map.put("pwd", pwd);
//            Date b = new Date();
//            long c = b.getTime() - a.getTime();
//            System.out.println("time for login one User = " + c + "ms");
//            return map;
            Date a = new Date();
            PwdToken pwdToken = userService.userLogin(userId);
            Date b = new Date();
            long c = b.getTime() - a.getTime();
            System.out.println("time for login one User = " + c + "ms");
            return pwdToken;
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/loginAfter/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public UserToken loginAfter(@PathVariable(value = "userId") String userId) {
        try {
            UserToken userToken = userService.userLoginAfter(userId);

            userToken.getNode().setParent(null);
            nodeService.setParentNull(userToken.getNode().getChildren());

            Role role = userToken.getRole();
            List<Menu> menus = menuService.getMenuByRole(role);
            userToken.getRole().setMenus(menus);

            return userToken;
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{userId}/changePwd", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public void putUserPwd(@PathVariable("userId") String userId, @RequestBody String bodyJson) {
        try {
            JsonBody jbosy = new ObjectMapper().readValue(bodyJson, JsonBody.class);
            String newPwd1 = jbosy.getValue();
            userService.updateUserPwd(userId, newPwd1);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{userId}/getPwd", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String, String> getUserPwd(@PathVariable("userId") String userId) {
        try {
            String pwd = userService.getUserByUserId(userId).getPassword();
            Map<String, String> map = new HashMap<String, String>();
            map.put("pwd", pwd);
//            String pwdJson = "{\"pwd\":\""+pwd+"\"}";
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
