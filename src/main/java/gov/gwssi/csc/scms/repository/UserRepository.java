package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * User: ryan
 * Date: 2/20/13
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
