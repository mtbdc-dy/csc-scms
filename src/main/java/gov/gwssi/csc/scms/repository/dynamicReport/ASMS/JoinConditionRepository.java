package gov.gwssi.csc.scms.repository.dynamicReport.ASMS;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.JoinCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzishi on 15/10/12.
 */
@Repository(value = "ASMSJoinConditionRepository")
public interface JoinConditionRepository extends CrudRepository<JoinCondition, String>{
}
