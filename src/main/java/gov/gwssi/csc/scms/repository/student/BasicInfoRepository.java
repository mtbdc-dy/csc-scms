package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository
public interface BasicInfoRepository extends CrudRepository<BasicInfo, String> {
    BasicInfo findByStudent(Student student);
}
