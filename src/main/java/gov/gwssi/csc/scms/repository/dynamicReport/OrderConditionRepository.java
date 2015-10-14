package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.OrderCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by wangzishi on 15/10/12.
 */
@Repository
public interface OrderConditionRepository extends CrudRepository<OrderCondition, String> {
}
