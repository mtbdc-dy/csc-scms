package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.SchoolFellow;
import gov.gwssi.csc.scms.repository.student.SchoolFellowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Iterable saveSchoolFellow(List<SchoolFellow> schoolFellowList) {
        return schoolFellowRepository.save(schoolFellowList);
    }

    public SchoolFellow updateSchoolFellow(SchoolFellow schoolFellow) {
        return schoolFellowRepository.save(schoolFellow);
    }
}
