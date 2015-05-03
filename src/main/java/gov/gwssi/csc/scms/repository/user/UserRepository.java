package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Murray on 15/5/2.
 */

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {

    @Query("select count(user) from User user where user.userId = :userId")
    int userCount(@Param("userId") String userId);

    User getUserByUserIdAndPasswordAndEnable(String userId, String password, String enabled);

    @Query("update User u set u.enabled = :enabled where u.userId = :userId")
    int updateEnabledByUserId(@Param("userId") String userId, @Param("enabled") String enabled);
}
