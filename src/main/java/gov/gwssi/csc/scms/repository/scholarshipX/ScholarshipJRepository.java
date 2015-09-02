package gov.gwssi.csc.scms.repository.scholarshipX;

import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by gc on 2015/7/17.
 * 保险管理接口
 */
@Repository(value="scholarshipJRepository")
public interface ScholarshipJRepository extends CrudRepository<ScholarshipJ,String> ,JpaSpecificationExecutor<ScholarshipJ> {
}
