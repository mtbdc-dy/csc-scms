package gov.gwssi.csc.scms.controller.user;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.deploy.net.HttpResponse;
import gov.gwssi.csc.scms.domain.user.*;
import gov.gwssi.csc.scms.service.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @RequestMapping(value = "/node/{userId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Node> getNodeTree(@PathVariable String userId) {
        try {
            User user = userService.getUserByUserId(userId);
            if (!"Y0006".equalsIgnoreCase(user.getRole().getIdentity())) {
                throw new UserIdentityError("not root user!");
            }
            List<Node> nodes = nodeService.getNodeTree();
            return nodes;
        } catch (NoSuchUserException e1) {
            throw new RuntimeException(e1);
        } catch (UserIdentityError e2) {
            throw new RuntimeException(e2);
        }
    }

    @RequestMapping(value = "/node/{userId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Node putNode(@PathVariable String userId, @RequestBody String nodeStr) {
        try {
            System.out.println("some one is put node!");
            User user = userService.getUserByUserId(userId);
            if (!"Y0006".equalsIgnoreCase(user.getRole().getIdentity())) {
                throw new UserIdentityError("not root user!");
            }
            System.out.println("user is validate!");
            Node node = new ObjectMapper().readValue(nodeStr, Node.class);
            System.out.println("get the node:" + node.toString());
            node = nodeService.updateNode(node);
            return node;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/role", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Role> getRoleList() {
        return roleService.getRolesByEnable(Role.ENABLE);
    }

    @RequestMapping(value = "/userList", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<User> getUserList() {
        return userService.getUsersByEnable(User.ENABLE);
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
