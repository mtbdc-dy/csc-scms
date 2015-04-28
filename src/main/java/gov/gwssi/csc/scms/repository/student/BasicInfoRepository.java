package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.BasicInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository(value = "basicInfoRepository")
public interface BasicInfoRepository extends CrudRepository<BasicInfo, String> {

    BasicInfo findByStudentId(String studentId);
}
