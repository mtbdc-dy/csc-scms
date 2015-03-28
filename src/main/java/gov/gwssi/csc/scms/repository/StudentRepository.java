package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * Created by WangZishi on 3/25/2015.
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String> {

    Student save(Student student);

    Student findByCscNumber(String cscNumber);

    List<Student> findByGenderLike(String Gender);
}
