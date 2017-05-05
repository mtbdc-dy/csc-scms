package gov.gwssi.csc.scms.repository.scholarshipX;

import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gc on 2015/7/17.
 * 保险管理接口
 */
public interface ScholarshipRepository extends CrudRepository<Scholarship, String> {
    Scholarship findById(String id);
    Scholarship findBySchoolAndYear(String school,long year);
}
