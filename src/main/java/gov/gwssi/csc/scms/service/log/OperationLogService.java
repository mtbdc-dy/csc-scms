package gov.gwssi.csc.scms.service.log;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.repository.log.OperationLogRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 2015/4/27.
 */
@Service("operationLogService")
public class OperationLogService extends BaseService {

    @Autowired
    @Qualifier("operationLogRepository")
    private OperationLogRepository operationLogRepository;

    public OperationLog saveOperationLog(OperationLog operationLog) {
        return operationLogRepository.save(operationLog);
    }

    public List<OperationLog> saveOperationLog(List<OperationLog> operationLogs) {
        return (List<OperationLog>) operationLogRepository.save(operationLogs);
    }

    public List<OperationLog> getLogByUser(String userId) {
        return operationLogRepository.findByCreateBy(userId);
    }

    public List<OperationLog> getLogByStudentId(Long studentId) {
        return operationLogRepository.findByStudentId(studentId);
    }
}
