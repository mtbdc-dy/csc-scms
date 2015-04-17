package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Repository(value = "basicInfoRepository")
public interface GradeRepository extends CrudRepository<Grade, Long> {
    public List<Grade> findByStudentId(Long studentId);
}


