package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.SchoolFellow;
import gov.gwssi.csc.scms.repository.student.SchoolFellowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("schoolFellowService")
public class SchoolFellowService {

    @Autowired
    private SchoolFellowRepository schoolFellowRepository;

    public SchoolFellow saveSchoolFellow(SchoolFellow schoolFellow) {
        return schoolFellowRepository.save(schoolFellow);
    }

    public SchoolFellow updateSchoolFellow(SchoolFellow schoolFellow) {
        return schoolFellowRepository.save(schoolFellow);
    }

    public SchoolFellow getSchoolFellowByStudentId(Long studentId){
        return schoolFellowRepository.findByStudentId(studentId);
    }
}
