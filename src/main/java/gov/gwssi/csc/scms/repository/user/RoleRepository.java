package gov.gwssi.csc.scms.repository.user;

import gov.gwssi.csc.scms.domain.user.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Lei on 2015/5/5.
 */
public interface RoleRepository extends CrudRepository<Role, String> {
}
