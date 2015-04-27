package gov.gwssi.csc.scms.repository.log;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Murray on 2015/4/27.
 */
public interface OperationLogRepository extends CrudRepository<OperationLog, Long> {

    List<OperationLog> findByCreateBy(String userId);

    List<OperationLog> findByStudentId(Long studentId);
}
