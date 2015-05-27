package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.UserRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Murray on 15/5/2.
 * 用户服务类
 */
@Service
public class UserService extends BaseService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private NodeService nodeService;

    @Autowired
    private MenuService menuService;

    public User getUserByUserIdAndEnable(String userId, String enable) {
        return userRepository.findUserByUserIdAndEnable(userId, enable);
    }

    public User checkRootUser(String userId) throws NoSuchUserException, UserIdentityError {
        User user = getUserByUserIdAndEnable(userId, User.ENABLE);
        if (user == null)
            throw new NoSuchUserException("can not find the enable root user:" + userId);

        if (!Role.ROOT_IDENTITY.equalsIgnoreCase(user.getRole().getIdentity())) {
            throw new UserIdentityError("not root user!");
        }
        return user;
    }

    public User addUser(User user, User loginUser) throws UserIdBeingUsedException, NoSuchRoleException, NoSuchNodeException {
        if (userExists(user.getUserId()))
            throw new UserIdBeingUsedException("this username for new user is used :" + user.getUserId());
        user.setPassword(MD5Util.MD5(user.getPassword()));
        user.setCreateBy(loginUser.getUserId());
        user.setCreateDate(new Date());
        return saveUser(user);
    }

    public User saveUser(User user) throws NoSuchRoleException, NoSuchNodeException {
        Role role = roleService.getRoleByRoleIdAndEnable(user.getRole().getRoleId(), Role.ENABLE);
        if (role == null)
            throw new NoSuchRoleException("can not find enabled role of the user with the roleId:" + user.getRole().getRoleId());
        user.setRole(role);

        Node node = nodeService.getNodeByNodeIdAndEnable(user.getNode().getNodeId(), Node.ENABLED);
        if (node == null) {
            throw new NoSuchNodeException("can not find enabled node of the user with the nodeId:" + user.getNode().getNodeId());
        }
        user.setNode(node);

        user = doSave(user);
        return initUser(user);
    }

    private User doSave(User user) {
        return userRepository.save(user);
    }

    public User deleteUser(String id, User loginUser) throws NoSuchUserException, NoSuchNodeException, NoSuchRoleException {
        User user = getUserByUserIdAndEnable(id, User.ENABLE);
        if (user == null)
            throw new NoSuchUserException("can not find enable user for delete:" + id);

        user.setEnable(User.UNENABLE);
        user.setUpdateDate(new Date());
        user.setUpdateBy(loginUser.getUserId());
        return doSave(user);
    }

    public User updateUser(User user, User loginUser) throws NoSuchUserException, NoSuchNodeException, NoSuchRoleException {
        User u = getUserByUserIdAndEnable(user.getUserId(), User.ENABLE);
        if (u == null)
            throw new NoSuchUserException("cannot find the user for update : " + user.getUserId());

        user.setUpdateDate(new Date());
        user.setUpdateBy(loginUser.getUserId());
        return saveUser(user);
    }

    public boolean userExists(String userId) {
        return userRepository.findUserByUserId(userId) != null;
    }

    public User userLogin(String userId, String password) throws NoSuchUserException {
        password = MD5Util.MD5(password);
        User user = userRepository.findUserByUserIdAndPasswordAndEnable(userId, password, User.ENABLE);
        initUser(user);

        if (user == null)
            throw new NoSuchUserException();
        return user;
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findUserByRoleAndEnable(role, User.ENABLE);
    }

    public List<User> getUsersByNode(Node node) {
        return userRepository.findUserByNodeAndEnable(node, User.ENABLE);
    }

    public List<User> getUsersByNode(String nodeId) throws NoSuchNodeException {
        Node node = nodeService.getNodeByNodeIdAndEnable(nodeId, Node.ENABLED);
        if (node == null)
            throw new NoSuchNodeException("can not find enabled node of the user with the nodeId:" + nodeId);
        List<User> users = userRepository.findUserByNodeAndEnable(node, User.ENABLE);
        for (User u : users) {
            initUser(u);
        }
        return users;
    }

    private User initUser(User user) {
        user.getNode().setParent(null);
        return initRoleMenuByUser(user);
    }

    private User initRoleMenuByUser(User user) {
        Role role = user.getRole();
        role.setMenus(menuService.getMenuByRole(role));
        return user;
    }
}
