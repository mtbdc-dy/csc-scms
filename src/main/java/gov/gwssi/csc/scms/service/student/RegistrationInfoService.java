package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.RegistrationInfo;
import gov.gwssi.csc.scms.repository.student.RegistrationInfoRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("registrationInfoService")
public class RegistrationInfoService extends BaseService {

    @Autowired
    @Qualifier("registrationInfoRepository")
    private RegistrationInfoRepository registrationInfoRepository;

    public RegistrationInfo getRegistrationInfoById(Long id) {
        return registrationInfoRepository.findOne(id);
    }

    public RegistrationInfo saveRegistrationInfo(RegistrationInfo registrationInfo) {
        return registrationInfoRepository.save(registrationInfo);
    }

    public RegistrationInfo updateRegistrationInfo(RegistrationInfo registrationInfo) {
        RegistrationInfo registrationInfo1 = getRegistrationInfoById(registrationInfo.getId());
        super.copyFiledValue(RegistrationInfo.class, registrationInfo, registrationInfo1);
        return registrationInfoRepository.save(registrationInfo1);
    }

    public RegistrationInfo getRegistrationInfoByStudentId(Long studentId) {
        return registrationInfoRepository.findByStudentId(studentId);
    }
}
