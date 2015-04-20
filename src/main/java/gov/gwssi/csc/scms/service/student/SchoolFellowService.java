package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Schoolfellow;
import gov.gwssi.csc.scms.repository.student.SchoolfellowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("schoolfellowService")
public class SchoolfellowService {

    @Autowired
    private SchoolfellowRepository schoolfellowRepository;

    public Schoolfellow saveSchoolfellow(Schoolfellow schoolFellow) {
        return schoolfellowRepository.save(schoolFellow);
    }

    public Schoolfellow updateSchoolfellow(Schoolfellow schoolFellow) {
        return schoolfellowRepository.save(schoolFellow);
    }

    public Schoolfellow getSchoolfellowByStudentId(Long studentId){
        return schoolfellowRepository.findByStudentId(studentId);
    }
}
