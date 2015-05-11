package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murray on 2015/4/16.
 * 数据操作接口
 */
@Repository(value = "studentRepository")
public interface StudentRepository extends CrudRepository<Student, String> {

    Student findByCscId(String cscId);
}
