package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Grade;
import gov.gwssi.csc.scms.repository.student.GradeRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("gradeService")
public class GradeService extends BaseService {

    @Autowired
    @Qualifier("grateRepository")
    private GradeRepository gradeRepository;

    public Grade getGradeById(String id) {
        return gradeRepository.findOne(id);
    }

    public List<Grade> saveGrade(List<Grade> grades) {
        for (Grade grade : grades)
            grade.setId(getBaseDao().getIdBySequence("SEQ_GRADE"));
        return (List<Grade>) gradeRepository.save(grades);
    }

    public Grade updateGrade(Grade grade) {
        grade.setStudent(getGradeById(grade.getId()).getStudent());
        return gradeRepository.save(grade);
    }

    public List<Grade> updateGrade(List<Grade> grades) {
        List<Grade> list = new ArrayList<Grade>();
        for (Grade grade : grades)
            list.add(updateGrade(grade));
        return list;
    }

    public List<Grade> getGradeByStudentId(String studentId) {
        return gradeRepository.findByStudentId(studentId);
    }
}
