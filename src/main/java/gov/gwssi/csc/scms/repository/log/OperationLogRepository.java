package gov.gwssi.csc.scms.repository.log;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Murray on 2015/4/27.
 */
@Repository("operationLogRepository")
public interface OperationLogRepository extends CrudRepository<OperationLog, String>, JpaSpecificationExecutor<OperationLog> {

    List<OperationLog> findByCreateBy(String userId);

    List<OperationLog> findByStudentId(String studentId);

    List<OperationLog> findByNodeIdAndCreateDBetween(String nodeId, Date startDate, Date endDate);

    List<OperationLog> findByNodeIdAndCreateDBetweenAndModuleId(String nodeId, Date startDate, Date endDate, String menuId);

    List<OperationLog> findByNodeIdAndCreateDBetweenAndOptType(String nodeId, Date startDate, Date endDate, String optType);

    List<OperationLog> findByNodeIdAndCreateDBetweenAndModuleIdAndOptType(String nodeId, Date startDate, Date endDate, String menuId, String optType);

    List<OperationLog> findByCreateDBetween(Date startDate, Date endDate);

    List<OperationLog> findByCreateDBetweenAndModuleId(Date startDate, Date endDate, String menuId);

    List<OperationLog> findByCreateDBetweenAndOptType(Date startDate, Date endDate, String optType);

    List<OperationLog> findByCreateDBetweenAndModuleIdAndOptType(Date startDate, Date endDate, String menuId, String optType);
}
