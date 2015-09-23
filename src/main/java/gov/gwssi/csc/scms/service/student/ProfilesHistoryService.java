package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.ProfilesHistory;
import gov.gwssi.csc.scms.repository.student.ProfilesHistoryRepository;
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
@Service("profilesHistoryService")
public class ProfilesHistoryService extends BaseService {

    @Autowired
    @Qualifier("profilesHistoryRepository")
    private ProfilesHistoryRepository profilesHistoryRepository;
    @Autowired
    private OperationLogService operationLogService;

    public ProfilesHistory getProfilesHistoryById(String id) {
        return profilesHistoryRepository.findOne(id);
    }

    public ProfilesHistory saveProfilesHistory(ProfilesHistory profilesHistory) {
        profilesHistory.setId(getBaseDao().getIdBySequence("SEQ_PROFILES_HISTORY"));
        return profilesHistoryRepository.save(profilesHistory);
    }

    public ProfilesHistory updateProfilesHistory(ProfilesHistory profilesHistory) {
        profilesHistory.setStudent(getProfilesHistoryById(profilesHistory.getId()).getStudent());
        return profilesHistoryRepository.save(profilesHistory);
    }

    @Transactional
    public ProfilesHistory updateProfilesHistory(ProfilesHistory profilesHistory,List<OperationLog> operationLogs) {
        operationLogService.saveOperationLog(operationLogs);
        profilesHistory.setStudent(getProfilesHistoryById(profilesHistory.getId()).getStudent());
        return profilesHistoryRepository.save(profilesHistory);
    }

    public ProfilesHistory getProfilesHistoryByStudentId(String studentId) {
        return profilesHistoryRepository.findByStudentId(studentId);
    }
}
