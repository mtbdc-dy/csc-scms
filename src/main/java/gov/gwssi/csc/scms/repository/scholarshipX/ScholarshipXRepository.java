package gov.gwssi.csc.scms.repository.scholarshipX;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 奖学金管理接口
 */
@Repository(value="scholarshipXRepository")
public interface ScholarshipXRepository extends CrudRepository<ScholarshipX,String>,JpaSpecificationExecutor<ScholarshipX> {

    List<ScholarshipX> findBySchoolAndYearAndSchReview(String school,long year,String schReview);
    List<ScholarshipX> findBySchoolAndYear(String school,long year);
    List<ScholarshipX> findByStudentId(String studentId);
    List<ScholarshipX> findByStudentIdAndYear(String studentId,long year);
}
