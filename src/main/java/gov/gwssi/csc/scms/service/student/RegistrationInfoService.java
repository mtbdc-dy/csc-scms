package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.RegistrationInfo;
import gov.gwssi.csc.scms.repository.student.RegistrationInfoRepository;
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
@Service("registrationInfoService")
public class RegistrationInfoService extends BaseService {

    @Autowired
    @Qualifier("registrationInfoRepository")
    private RegistrationInfoRepository registrationInfoRepository;
    @Autowired
    private OperationLogService operationLogService;

    public RegistrationInfo getRegistrationInfoById(String id) {
        return registrationInfoRepository.findOne(id);
    }

    public RegistrationInfo saveRegistrationInfo(RegistrationInfo registrationInfo) {
        registrationInfo.setId(getBaseDao().getIdBySequence("SEQ_REGISTRATION_INFO"));
        return registrationInfoRepository.save(registrationInfo);
    }

    public RegistrationInfo updateRegistrationInfo(RegistrationInfo registrationInfo) {
        registrationInfo.setStudent(getRegistrationInfoById(registrationInfo.getId()).getStudent());
        return registrationInfoRepository.save(registrationInfo);
    }

    @Transactional
    public RegistrationInfo updateRegistrationInfo(RegistrationInfo registrationInfo,List<OperationLog> operationLogs) {
        operationLogService.saveOperationLog(operationLogs);
        registrationInfo.setStudent(getRegistrationInfoById(registrationInfo.getId()).getStudent());
        return registrationInfoRepository.save(registrationInfo);
    }

    public RegistrationInfo getRegistrationInfoByStudentId(String studentId) {
        return registrationInfoRepository.findByStudentId(studentId);
    }
}
