package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import gov.gwssi.csc.scms.repository.student.SchoolRollRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("schoolRollService")
public class SchoolRollService extends BaseService {

    @Autowired
    @Qualifier("schoolRollRepository")
    private SchoolRollRepository schoolRollRepository;

    public SchoolRoll getSchoolRollById(String id) {
        return schoolRollRepository.findOne(id);
    }

    public SchoolRoll updateSchoolRoll(SchoolRoll schoolRoll) {
        schoolRoll.setStudent(getSchoolRollById(schoolRoll.getId()).getStudent());
        return schoolRollRepository.save(schoolRoll);
    }

    public SchoolRoll saveSchoolRoll(SchoolRoll schoolRoll) {
        schoolRoll.setId(getBaseDao().getIdBySequence("SEQ_SCHOOLROLL"));
        return schoolRollRepository.save(schoolRoll);
    }

    public SchoolRoll getSchoolRollByStudentId(String studentId) {
        return schoolRollRepository.findByStudentId(studentId);
    }
}
