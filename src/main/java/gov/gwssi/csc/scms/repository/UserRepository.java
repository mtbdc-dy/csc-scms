package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.User;

import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by WangZishi on 3/24/2015.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find the user with the given username. This method will be translated into a query using the
     * {@link javax.persistence.NamedQuery} annotation at the {@link User} class.
     *
     * @param userId
     * @return
     */
    User findByTheUsersId(String userId);

    /**
     * Find all users with the given userName. This method will be translated into a query by constructing it directly
     * from the method name as there is no other query declared.
     *
     * @param userName
     * @return
     */
    List<User> findByUserName(String userName);
}
