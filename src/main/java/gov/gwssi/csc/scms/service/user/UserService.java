package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.UserRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 15/5/2.
 */
@Service
public class UserService extends BaseService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public User getUserByUserId(String userId) {
        return userRepository.getUserByUserIdAndEnable(userId, "1");
    }

    public User addUser(User user) throws IdBeUsedException {
        if (userExists(user.getUserId()))
            throw new IdBeUsedException("this id for new user is used :" + user.getUserId());
        return saveUser(user);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User changeUserEnable(String userId, String enable) {
        User u = getUserByUserId(userId);
        u.setEnable(enable);
        return userRepository.save(u);
    }

    public User updateUser(User user) throws NoSuchUserException {
        User u = getUserByUserId(user.getUserId());
        if (u == null)
            throw new NoSuchUserException();
        user.setRoll(u.getRole());
        user.setNode(u.getNode());
        return userRepository.save(user);
    }

    public boolean userExists(String userId) {
        return userRepository.exists(userId);
    }

    public User userLogin(String userId, String password) {
        return userRepository.getUserByUserIdAndPasswordAndEnable(userId, password, "1");
    }

    public List<User> getUsersByRole(Role role) {
        return userRepository.findUserByRole(role);
    }

}
