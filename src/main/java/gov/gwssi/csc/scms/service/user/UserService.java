package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.UserRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 15/5/2.
 */
@Service
public class UserService extends BaseService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    public User getUserByUserId(String userId) {
        return userRepository.findOne(userId);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User enabledUser(String userId, String enable) {
        User u = getUserByUserId(userId);
        u.setEnable(enable);
        return userRepository.save(u);
    }

    public User updateUser(User user) {
        User u = getUserByUserId(user.getUserId());
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

}
