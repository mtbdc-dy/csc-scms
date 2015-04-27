package gov.gwssi.csc.scms.repository.log;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Murray on 2015/4/27.
 */
@Repository("operationLogRepository")
public interface OperationLogRepository extends CrudRepository<OperationLog, Long> {

    List<OperationLog> findByCreateBy(String userId);

    List<OperationLog> findByStudentId(Long studentId);
}
