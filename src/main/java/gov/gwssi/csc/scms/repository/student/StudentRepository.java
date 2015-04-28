package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository(value = "studentRepository")
public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByCscId(String cscId);
}
