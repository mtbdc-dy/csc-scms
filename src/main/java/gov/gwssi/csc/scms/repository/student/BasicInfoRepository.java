package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.BasicInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murray on 2015/4/16.
 * 数据操作接口
 */
@Repository(value = "basicInfoRepository")
public interface BasicInfoRepository extends CrudRepository<BasicInfo, String> {

    BasicInfo findByStudentId(String studentId);
}
