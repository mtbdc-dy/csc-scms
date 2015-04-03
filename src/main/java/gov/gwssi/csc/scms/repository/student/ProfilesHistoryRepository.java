package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.ProfilesHistory;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Murray on 2015/4/3.
 */
public interface ProfilesHistoryRepository extends CrudRepository<ProfilesHistory, String> {
    ProfilesHistory findByStudent(Student student);
}
