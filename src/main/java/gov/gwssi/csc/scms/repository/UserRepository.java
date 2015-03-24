package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by WangZishi on 3/24/2015.
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Find the user with the given username. This method will be translated into a query using the
     * {@link javax.persistence.NamedQuery} annotation at the {@link User} class.
     *
     * @param lastname
     * @return
     */
    User findByTheUsersName(String username);

    /**
     * Find all users with the given lastname. This method will be translated into a query by constructing it directly
     * from the method name as there is no other query declared.
     *
     * @param lastname
     * @return
     */
    List<User> findByLastname(String lastname);

    /**
     * Returns all users with the given firstname. This method will be translated into a query using the one declared in
     * the {@link Query} annotation declared one.
     *
     * @param firstname
     * @return
     */
    @Query("select u from User u where u.firstname = ?")
    List<User> findByFirstname(String firstname);

    /**
     * Returns all users with the given name as first- or lastname. Makes use of the {@link Param} annotation to use named
     * parameters in queries. This makes the query to method relation much more refactoring safe as the order of the
     * method parameters is completely irrelevant.
     *
     * @param name
     * @return
     */
    @Query("select u from User u where u.firstname = :name or u.lastname = :name")
    List<User> findByFirstnameOrLastname(@Param("name") String name);
}
