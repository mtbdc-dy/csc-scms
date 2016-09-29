package gov.gwssi.csc.scms.repository.dynamicReport.ASMS;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.SelectCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzishi on 15/10/12.
 */
@Repository(value = "ASMSSelectConditionRepository")
public interface SelectConditionRepository extends CrudRepository<SelectCondition, String> {
}
