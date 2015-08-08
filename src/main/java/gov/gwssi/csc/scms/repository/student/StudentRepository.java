package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by Murray on 2015/4/16.
 * Modified by Wang Zishi on 2015/08/07.
 * 数据操作接口
 */
@Repository
public interface StudentRepository extends CrudRepository<Student, String>, JpaSpecificationExecutor<Student> {

    Student findByCscId(String cscId);

    @Query(value = "select s.id, s.cscId, s.basicInfo from Student s where s.basicInfo.gender like :gender")
    Set<Student> findStudentsByGender(@Param(value = "gender") String gender);

    @Query(value = "select count(s) from Student s where s.basicInfo.gender like :gender")
    int countStudentsByGender(@Param(value = "gender") String gender);

}
