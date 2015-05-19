package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Node;
import gov.gwssi.csc.scms.domain.user.Role;
import gov.gwssi.csc.scms.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Murray on 15/5/2.
 * 角色数据操作接口
 */

@Repository("userRepository")
public interface UserRepository extends CrudRepository<User, String> {
    User findUserByUserIdAndPasswordAndEnable(String userId, String password, String enable);

    User findUserByUserIdAndEnable(String userId, String enable);

    List<User> findUserByEnable(String enable);

    List<User> findUserByRoleAndEnable(Role role, String enable);

    List<User> findUserByNodeAndEnable(Node node,String enable);
}
