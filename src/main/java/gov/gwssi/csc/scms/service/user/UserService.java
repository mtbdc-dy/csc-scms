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

    public User getUserByUserId(String userId) throws NoSuchUserException {
        User user = userRepository.getUserByUserIdAndEnable(userId, "1");
        if (user == null)
            throw new NoSuchUserException();
        return user;
    }

    public List<User> getUsersByEnable(String enable) {
        return userRepository.findUserByEnable(enable);
    }

    public User addUser(User user) throws UserIdBeingUsedException {
        if (userExists(user.getUserId()))
            throw new UserIdBeingUsedException("this id for new user is used :" + user.getUserId());
        user.setPassword(MD5Util.MD5(user.getPassword()));
        return saveUser(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User enableUser(String userId) throws NoSuchUserException {
        User u = getUserByUserId(userId);
        if (u == null)
            throw new NoSuchUserException();
        if ("1".equals(u.getEnable()))
            u.setEnable("0");
        else
            u.setEnable("1");
        return userRepository.save(u);
    }

    public User updateUser(User user) throws NoSuchUserException {
        User u = getUserByUserId(user.getUserId());
        if (u == null)
            throw new NoSuchUserException();
        return userRepository.save(user);
    }

    public boolean userExists(String userId) {
        return userRepository.exists(userId);
    }

    public User userLogin(String userId, String password) throws NoSuchUserException {
        password = MD5Util.MD5(password);
        User user = userRepository.getUserByUserIdAndPasswordAndEnable(userId, password, "1");
        if (user == null)
            throw new NoSuchUserException();
        return user;
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findUserByRoleAndEnable(role, "1");
    }

    public List<User> getUsersByNode(Node node) {
        return userRepository.findUserByNodeAndEnable(node, "1");
    }

}
