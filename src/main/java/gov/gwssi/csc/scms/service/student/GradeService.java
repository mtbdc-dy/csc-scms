package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Grade;
import gov.gwssi.csc.scms.repository.student.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("gradeService")
public class GradeService {

    @Autowired
    @Qualifier("grateRepository")
    private GradeRepository gradeRepository;

    public Grade saveGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Iterable saveGrade(List<Grade> grade) {
        return gradeRepository.save(grade);
    }

    public Grade updateGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    public Iterable updateGrade(List<Grade> grade) {
        return gradeRepository.save(grade);
    }

    public List<Grade> getGradeByStudentId(Long studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}
