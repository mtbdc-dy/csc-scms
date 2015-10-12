package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.WhereCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzishi on 15/10/12.
 */
@Repository
public interface WhereConditionRepository extends CrudRepository<WhereCondition, String> {
}
