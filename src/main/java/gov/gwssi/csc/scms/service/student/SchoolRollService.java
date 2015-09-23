package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.SchoolRollRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("schoolRollService")
public class SchoolRollService extends BaseService {

    @Autowired
    @Qualifier("schoolRollRepository")
    private SchoolRollRepository schoolRollRepository;
    @Autowired
    private OperationLogService operationLogService;

    public SchoolRoll getSchoolRollById(String id) {
        SchoolRoll schoolRoll1 = schoolRollRepository.findOne(id);
        return schoolRollRepository.findOne(id);
    }

    public SchoolRoll updateSchoolRoll(SchoolRoll schoolRoll) {
        if(schoolRoll.getStudent() == null){
            schoolRoll.setStudent(getSchoolRollById(schoolRoll.getId()).getStudent());
        }
        return schoolRollRepository.save(schoolRoll);
    }

    @Transactional
    public SchoolRoll updateSchoolRoll(SchoolRoll schoolRoll,List<OperationLog> operationLogs) {
        operationLogService.saveOperationLog(operationLogs);
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

    public void updateSchoolRollRegisterYear(String studentId){
        SchoolRoll schoolRoll = getSchoolRollByStudentId(studentId);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        schoolRoll.setRegisterYear(year);
        updateSchoolRoll(schoolRoll);
    }



}
