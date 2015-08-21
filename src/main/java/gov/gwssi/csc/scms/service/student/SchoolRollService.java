package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.SchoolRollRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("schoolRollService")
public class SchoolRollService extends BaseService {

    @Autowired
    @Qualifier("schoolRollRepository")
    private SchoolRollRepository schoolRollRepository;

    public SchoolRoll getSchoolRollById(String id) {
        SchoolRoll schoolRoll1 = schoolRollRepository.findOne(id);
        return schoolRollRepository.findOne(id);
    }

    public SchoolRoll updateSchoolRoll(SchoolRoll schoolRoll) {
//        String id = schoolRoll.getId();
//        SchoolRoll sr = getSchoolRollById(schoolRoll.getId());
//        Student student = sr.getStudent();
       // schoolRoll.setStudent(student);
        return schoolRollRepository.save(schoolRoll);
    }

    public SchoolRoll saveSchoolRoll(SchoolRoll schoolRoll) {
        schoolRoll.setId(getBaseDao().getIdBySequence("SEQ_SCHOOLROLL"));
        return schoolRollRepository.save(schoolRoll);
    }

    public SchoolRoll getSchoolRollByStudentId(String studentId) {
        return schoolRollRepository.findByStudentId(studentId);
    }

    public void updateSchoolRollRegisterYear(String studentId){
        SchoolRoll schoolRoll = getSchoolRollByStudentId(studentId);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        schoolRoll.setRegisterYear(year);
        updateSchoolRoll(schoolRoll);
    }



}
