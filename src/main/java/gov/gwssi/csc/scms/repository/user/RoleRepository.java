package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Lei on 2015/5/5.
 */
public interface RoleRepository extends CrudRepository<Role, String> {

    Role findRoleByRoleIdAndEnable(String roleId,String enable);

    List<Role> findRoleByEnable(String enable);
}
