package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Schoolfellow;
import gov.gwssi.csc.scms.repository.student.SchoolfellowRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("schoolfellowService")
public class SchoolFellowService extends BaseService {

    @Autowired
    @Qualifier("schoolfellowRepository")
    private SchoolfellowRepository schoolfellowRepository;

    public Schoolfellow getSchoolfellowById(Long id) {
        return schoolfellowRepository.findOne(id);
    }

    public Schoolfellow getSchoolfellowByStudentId(Long studentId) {
        return schoolfellowRepository.findByStudentId(studentId);
    }

    public Schoolfellow saveSchoolfellow(Schoolfellow schoolfellow) {
        return schoolfellowRepository.save(schoolfellow);
    }

    public Schoolfellow updateSchoolfellow(Schoolfellow schoolfellow) {
        Schoolfellow schoolFellowTemp = getSchoolfellowById(schoolfellow.getId());
        super.copyFiledValue(Schoolfellow.class, schoolfellow, schoolFellowTemp);
        return saveSchoolfellow(schoolFellowTemp);
    }
}
