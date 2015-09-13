package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.user.*;
import gov.gwssi.csc.scms.repository.user.UserRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import gov.gwssi.csc.scms.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

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

    @Autowired
    private ProjectService projectService;


    public User getUserByIdAndEnable(String id, String enable) {
        return userRepository.findUserByIdAndEnable(id, enable);
    }

    public User getUserByUserIdAndEnable(String userId, String enable) throws NoSuchUserException{
        return userRepository.findUserByUserIdAndEnable(userId, enable);
    }

    public User getUserByJWT(String header) throws RequestHeaderError, UserIdentityError, NoSuchUserException {
        Map map = JWTUtil.decode(header);
        if (map == null)
            throw new RequestHeaderError("can not read the header message!");

        Object userId = map.get("userId");
        if (userId == null)
            throw new RequestHeaderError("can not read the invalid message!");
        User user = getUserByUserIdAndEnable(String.valueOf(userId), User.ENABLE);
        if (user == null)
            throw new NoSuchUserException("can not find the enable root user");
        return user;
    }

    public User getRootUser(String header) throws RequestHeaderError, UserIdentityError, NoSuchUserException {
//        Map map = JWTUtil.decode(header);
//        if (map == null)
//            throw new RequestHeaderError("can not read the header message!");
//
//        Object userId = map.get("userId");
//        if (userId == null)
//            throw new RequestHeaderError("can not read the invalid message!");
        User user = getUserByJWT(header);
        if (checkRootUser(user)){

        }

        return user;
    }

    public boolean checkRootUser(User user) throws NoSuchUserException, UserIdentityError {
//        User user = getUserByUserIdAndEnable(userId, User.ENABLE);
        if (user == null)
            throw new NoSuchUserException("can not find the enable root user");
        if (!Role.ROOT_IDENTITY.equals(user.getRole().getIdentity())) {
            throw new UserIdentityError("not root user!");
        }
        return true;
    }

    public User addUser(User user, User loginUser) throws UserIdBeingUsedException, NoSuchRoleException, NoSuchNodeException {
        if (userExists(user.getUserId()))
            throw new UserIdBeingUsedException("this username for new user is used :" + user.getUserId());
        user.setId(getBaseDao().getIdBySequence("seq_user"));
        user.setPassword(user.getPassword());
        user.setCreateBy(loginUser.getUserId());
        user.setCreateDate(new Date());
        user.setEnable(User.ENABLE);
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

        List<Project> projects = user.getProjects();
        List<Project> newProjects = new ArrayList<Project>(projects.size());
        Project newProject;
        for (Project project : projects) {
            newProject = projectService.getProjectByProjectIdAndEnabled(project.getProjectId(), Project.ENABLED);
            if (newProject != null) {
                newProjects.add(newProject);
            }
        }
        user.setProjects(newProjects);

        user = doSave(user);
        return initUser(user);
    }

    private User doSave(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(String id, User loginUser) throws NoSuchUserException, NoSuchNodeException, NoSuchRoleException {
        User user = getUserByUserIdAndEnable(id, User.ENABLE);
        if (user == null)
            throw new NoSuchUserException("can not find enable user for delete:" + id);

        user.setEnable(User.UNENABLE);
        user.setUpdateDate(new Date());
        user.setUpdateBy(loginUser.getUserId());
        doSave(user);
    }

    public User updateUser(User user, User loginUser) throws NoSuchUserException, NoSuchNodeException, NoSuchRoleException {
        User u = getUserByUserIdAndEnable(user.getUserId(), User.ENABLE);
        if (u == null)
            throw new NoSuchUserException("cannot find the user for update : " + user.getUserId());

        if (user.getPassword() != null) {
            user.setPassword(MD5Util.MD5(user.getPassword()));
        } else {
            user.setPassword(u.getPassword());
        }
        user.setUpdateDate(new Date());
        user.setUpdateBy(loginUser.getUserId());
        return saveUser(user);
    }

    public boolean userExists(String userId) {
        return userRepository.findUserByUserId(userId) != null;
    }

    public PwdToken userLogin(String userId) throws NoSuchUserException {
//        Date a = new Date();
//        User user = userRepository.findUserByUserId(userId);
//        Date b = new Date();
//        if (user == null)
//            throw new NoSuchUserException();
//        initUser(user);
//        Date c = new Date();
//        long findUser = b.getTime() - a.getTime();
//        long initUser = c.getTime() - b.getTime();
//
//        System.out.println("findUser = " + findUser + "ms");
//        System.out.println("initUser = " + initUser + "ms");
//
//        return user;
        Date a = new Date();

//        String pwd = getBaseDao().getPWDByUserId(userId);
        PwdToken pwdToken = userRepository.getPwdToken(userId);
        Role role = pwdToken.getRole();
        List<Menu> menus = menuService.getMenuByRole(role);
        pwdToken.getRole().setMenus(menus);

        Date b = new Date();
        long findUser = b.getTime() - a.getTime();
        System.out.println("findUser = " + findUser + "ms");
        return pwdToken;

    }


    public UserToken userLoginAfter(String userId) throws NoSuchUserException {
        UserToken userToken = userRepository.getUserToken(userId);
        return userToken;

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

        List<Project> projects = user.getProjects();
        for (Project project : projects) {
            Project parent = project.getParent();
            while (parent != null) {
                parent.setChildren(null);
                parent = parent.getParent();
            }
        }

        return initRoleMenuByUser(user);
    }

    private User initRoleMenuByUser(User user) {
        Role role = user.getRole();
        role.setMenus(menuService.getMenuByRole(role));
        return user;
    }
    //修改用户密码
    @Transactional
    public void updateUserPwd(String userId, String newPwd1){
        User user = userRepository.findUserByUserId(userId);
        user.setPassword(newPwd1);
        userRepository.save(user);
    }

    public User getUserByUserId(String userId){
        return userRepository.findUserByUserId(userId);
    }
}
