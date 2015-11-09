package gov.gwssi.csc.scms.service.log;

import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.log.OperationLogRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static gov.gwssi.csc.scms.service.log.OperationLogSpecs.filterIsLike;
import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Murray on 2015/4/27.
 * 日志操作服务层
 */
@Service("operationLogService")
public class OperationLogService extends BaseService {

    @Autowired
    @Qualifier("operationLogRepository")
    private OperationLogRepository operationLogRepository;

    public List<OperationLog> saveOperationLog(List<OperationLog> operationLogs) {
        if (operationLogs == null)
            return null;
        for (OperationLog operationLog : operationLogs)
            operationLog.setId(getBaseDao().getIdBySequence("SEQ_OPT_LOG"));
        return (List<OperationLog>) operationLogRepository.save(operationLogs);
    }

    @SuppressWarnings("unchecked")
    public List<OperationLog> getLogByUser(String userId) {
        return operationLogRepository.findByCreateBy(userId);
    }

    @SuppressWarnings("unchecked")
    public List<OperationLog> getLogByStudentId(String studentId) {
        return operationLogRepository.findByStudentId(studentId);
    }

    public List<OperationLog> doQueryWithOptType(User user, Date startDate, Date endDate, String optType) throws NoSupportedUserException {
        if (User.CSC_USER.equals(user.getUserType())) {
            return operationLogRepository.findByCreateDBetweenAndOptType(startDate, endDate, optType);
        } else if (User.UNIVERSITY_USER.equals(user.getUserType())) {
            return operationLogRepository.findByNodeIdAndCreateDBetweenAndOptType(user.getNode().getNodeId(), startDate, endDate, optType);
        } else {
            throw new NoSupportedUserException("log query not support current user!");
        }
    }

    public List<OperationLog> doQueryWithModuleId(User user, Date startDate, Date endDate, String moduleId) throws NoSupportedUserException {
        if (User.CSC_USER.equals(user.getUserType())) {
            return operationLogRepository.findByCreateDBetweenAndModuleId(startDate, endDate, moduleId);
        } else if (User.UNIVERSITY_USER.equals(user.getUserType())) {
            return operationLogRepository.findByNodeIdAndCreateDBetweenAndModuleId(user.getNode().getNodeId(), startDate, endDate, moduleId);
        } else {
            throw new NoSupportedUserException("log query not support current user!");
        }
    }

    public List<OperationLog> doQueryWithAllCondition(User user, Date startDate, Date endDate, String moduleId, String optType) throws NoSupportedUserException {
        if (User.CSC_USER.equals(user.getUserType())) {
            return operationLogRepository.findByCreateDBetweenAndModuleIdAndOptType(startDate, endDate, moduleId, optType);
        } else if (User.UNIVERSITY_USER.equals(user.getUserType())) {
            return operationLogRepository.findByNodeIdAndCreateDBetweenAndModuleIdAndOptType(user.getNode().getNodeId(), startDate, endDate, moduleId, optType);
        } else {
            throw new NoSupportedUserException("log query not support current user!");
        }
    }

    public List<OperationLog> doQueryWithOnlyTime(User user, Date startDate, Date endDate) throws NoSupportedUserException {
        if (User.CSC_USER.equals(user.getUserType())) {
            return operationLogRepository.findByCreateDBetween(startDate, endDate);
        } else if (User.UNIVERSITY_USER.equals(user.getUserType())) {
            return operationLogRepository.findByNodeIdAndCreateDBetween(user.getNode().getNodeId(), startDate, endDate);
        } else {
            throw new NoSupportedUserException("log query not support current user!");
        }
    }
    public Page<OperationLog> getOptLogsPagingByFilter(Filter filter,Integer page,Integer size,String mode,User user) {
        String userId = user.getUserId();
        Specification<OperationLog> specA = filterIsLike(filter,userId);
//        Specification<Ticket> specB = userIs(user);getOptLogsPagingByFilter
//        System.out.println();
        return operationLogRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.DESC,"createD"));
    }
    public Page<OperationLog> getStudentChangeLogsPagingByFilter(Integer page,Integer size,String studengId) {
        Specification<OperationLog> specA = filterIsLike(studengId);
//        Specification<Ticket> specB = userIs(user);getOptLogsPagingByFilter
//        System.out.println();
        return operationLogRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.DESC,"createD"));
    }
}
