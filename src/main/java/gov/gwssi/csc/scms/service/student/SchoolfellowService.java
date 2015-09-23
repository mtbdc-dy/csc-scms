package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.Schoolfellow;
import gov.gwssi.csc.scms.repository.student.SchoolfellowRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("schoolfellowService")
public class SchoolfellowService extends BaseService {

    @Autowired
    @Qualifier("schoolfellowRepository")
    private SchoolfellowRepository schoolfellowRepository;
    @Autowired
    private OperationLogService operationLogService;

    public Schoolfellow getSchoolfellowById(String id) {
        return schoolfellowRepository.findOne(id);
    }

    public Schoolfellow getSchoolfellowByStudentId(String studentId) {
        return schoolfellowRepository.findByStudentId(studentId);
    }

    public Schoolfellow saveSchoolfellow(Schoolfellow schoolfellow) {
        schoolfellow.setId(getBaseDao().getIdBySequence("SEQ_SCHOOL_FELLOW"));
        return schoolfellowRepository.save(schoolfellow);
    }

    public Schoolfellow updateSchoolfellow(Schoolfellow schoolfellow) {
        schoolfellow.setStudent(getSchoolfellowById(schoolfellow.getId()).getStudent());
        return saveSchoolfellow(schoolfellow);
    }

    @Transactional
    public Schoolfellow updateSchoolfellow(Schoolfellow schoolfellow,List<OperationLog> operationLogs) {
        operationLogService.saveOperationLog(operationLogs);
        schoolfellow.setStudent(getSchoolfellowById(schoolfellow.getId()).getStudent());
        return saveSchoolfellow(schoolfellow);
    }
}
