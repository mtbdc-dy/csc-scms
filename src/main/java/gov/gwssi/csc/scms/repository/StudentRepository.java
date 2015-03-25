package gov.gwssi.csc.scms.repository;

import gov.gwssi.csc.scms.domain.Student;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 此写法等价于:
 * public interface StudentRepository extends Repository<Student, String> { …… }
 *
 * Created by WangZishi on 3/25/2015.
 */
@Repository
@RepositoryDefinition(domainClass = Student.class, idClass = String.class)
public interface StudentRepository {

    Student save(Student student);

    Student findByCscNumber(String cscNumber);

    List<Student> findByGenderLike(String Gender);
}
