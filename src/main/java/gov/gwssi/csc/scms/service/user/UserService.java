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

    public void enabledUser(String userId, String enabled) {
        userRepository.updateEnabledByUserId(userId, enabled);
    }

    public User updateUser(User user) {
        User u = getUserByUserId(user.getUserId());
        user.setRoll(u.getRole());
        user.setNode(u.getNode());
        return userRepository.save(user);
    }

    public boolean userExists(String userId) {
        return userRepository.userCount(userId) > 0;
    }

    public User userLogin(String userId, String password) {
        return userRepository.getUserByUserIdAndPasswordAndEnable(userId, password, "1");
    }

}
