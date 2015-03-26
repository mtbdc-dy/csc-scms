package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by WangZishi on 3/27/2015.
 *
 */
public interface UserRepository extends CrudRepository<User,Long> {
}
