package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Accident;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository
public interface AccidentRepository extends CrudRepository<Accident, String> {
    List<Accident> findByStudent(Student student);
}
