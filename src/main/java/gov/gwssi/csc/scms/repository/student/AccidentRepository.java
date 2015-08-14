package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Accident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 * 数据操作接口
 */
@Repository("accidentRepository")
public interface AccidentRepository extends CrudRepository<Accident, String> {

    List<Accident> findByStudentId(String studentId);
}
