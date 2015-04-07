package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.RegistrationInfo;
import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.RegistrationInfoRepository;
import gov.gwssi.csc.scms.repository.student.SchoolRollRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("schoolRollService")
public class SchoolRollService extends BaseService {

    @Autowired
    private SchoolRollRepository schoolRollRepository;

    public SchoolRoll getSchoolRollByStudent(Student student) {
        return schoolRollRepository.findOne(student.getSchoolRoll().getId());
    }

    public SchoolRoll getSchoolRollById(Long id) {
        return schoolRollRepository.findOne(id);
    }

    public SchoolRoll saveSchoolRoll(SchoolRoll schoolRoll) {
        return schoolRollRepository.save(schoolRoll);
    }
}
