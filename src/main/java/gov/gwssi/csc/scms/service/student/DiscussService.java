package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.Discuss;
import gov.gwssi.csc.scms.repository.student.DiscussRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("discussService")
public class DiscussService extends BaseService {

    @Autowired
    @Qualifier("discussRepository")
    private DiscussRepository discussRepository;
    @Autowired
    private OperationLogService operationLogService;

    public Discuss getDiscussById(String id) {
        return discussRepository.findOne(id);
    }

    public Discuss getDiscussByStudentId(String studentID) {
        return discussRepository.findByStudentId(studentID);
    }

    public Discuss saveDiscuss(Discuss discuss) {
        discuss.setId(getBaseDao().getIdBySequence("SEQ_DISCUSS"));
        return discussRepository.save(discuss);
    }

    public Discuss updateDiscuss(Discuss discuss) {
        discuss.setStudent(getDiscussByStudentId(discuss.getId()).getStudent());
        return saveDiscuss(discuss);
    }

    @Transactional
    public Discuss updateDiscuss(Discuss discuss,List<OperationLog> operationLogs) {
        operationLogService.saveOperationLog(operationLogs);
        discuss.setStudent(getDiscussByStudentId(discuss.getId()).getStudent());
        return saveDiscuss(discuss);
    }
}
