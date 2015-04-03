package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.RegistrationInfo;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository
public interface RegistrationInfoRepository extends CrudRepository<RegistrationInfo, String> {
    RegistrationInfo findByStudent(Student student);
}
