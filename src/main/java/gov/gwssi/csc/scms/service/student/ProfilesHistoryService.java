package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.ProfilesHistory;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.ProfilesHistoryRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("profilesHistoryService")
public class ProfilesHistoryService extends BaseService {

    @Autowired
    private ProfilesHistoryRepository profilesHistoryRepository;

    public ProfilesHistory getProfilesHistoryByStudent(Student student) {
        return profilesHistoryRepository.findByStudent(student);
    }

    public ProfilesHistory getProfilesHistoryById(String id) {
        return profilesHistoryRepository.findOne(id);
    }

    public ProfilesHistory saveProfilesHistory(ProfilesHistory profilesHistory) {
        return profilesHistoryRepository.save(profilesHistory);
    }
}
