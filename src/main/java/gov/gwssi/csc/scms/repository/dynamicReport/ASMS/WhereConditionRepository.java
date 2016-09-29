package gov.gwssi.csc.scms.repository.dynamicReport.ASMS;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.WhereCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by wangzishi on 15/10/12.
 */
@Repository(value = "ASMSWhereConditionRepository")
public interface WhereConditionRepository extends CrudRepository<WhereCondition, String> {
}
