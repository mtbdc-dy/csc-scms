package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.*;
import org.springframework.data.jpa.repository.Query;
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

    User findUserByIdAndEnable(String id, String enable);

    User findUserByUserId(String userId);

    User findUserByUserIdAndEnable(String userId, String enable);

    List<User> findUserByRoleAndEnable(Role role, String enable);

    List<User> findUserByNodeAndEnable(Node node, String enable);

    @Query(value = "select new gov.gwssi.csc.scms.domain.user.PwdToken(u.userId,u.fullName,u.password,u.userType,u.role.identity) from User u where u.userId = ?1 and u.enable = '1'")
    PwdToken getPwdToken(String userId);

    @Query(value = "select new gov.gwssi.csc.scms.domain.user.UserToken(u.userId,u.userType,u.fullName,u.node,u.role) from User u where u.userId = ?1")
    UserToken getUserToken(String userId);

}
