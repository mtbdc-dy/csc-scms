package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.Student;
import org.junit.Test;
import org.springframework.data.domain.Page;

/**
 * Created by wang Zishi on 15/8/7.
 * 学生服务单元测试
 */
public class StudentsServiceTest extends UnitTestBase {

    @Test
    public void testGetStudents() throws Exception {
        Filter filter = new Filter();
        filter.setCscId("%X%");

        StudentsService studentsService = super.getBean("studentsService");

        Page<Student> studentPage = studentsService.getStudentsByFilter(filter);

        System.out.println("studentPage = " + studentPage);

    }


}
