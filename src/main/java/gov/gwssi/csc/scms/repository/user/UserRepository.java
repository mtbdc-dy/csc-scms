package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murray on 15/5/2.
 */

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {
    User getUserByUserIdAndPasswordAndEnable(String userId, String password, String enabled);
}
