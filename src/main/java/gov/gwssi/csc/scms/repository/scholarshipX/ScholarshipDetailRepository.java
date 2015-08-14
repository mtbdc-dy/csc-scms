package gov.gwssi.csc.scms.repository.scholarshipX;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by gc on 2015/7/17.
 * 保险管理接口
 */
public interface ScholarshipDetailRepository extends CrudRepository<ScholarshipDetail, String> {
    ScholarshipDetail findById(String id);
}
