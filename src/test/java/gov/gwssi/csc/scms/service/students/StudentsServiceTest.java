package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.student.Student;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.Iterator;

/**
 * Created by wang Zishi on 15/8/7.
 * 学生服务单元测试
 */
public class StudentsServiceTest extends UnitTestBase {

    @Test
    public void testGetStudents() throws Exception {
        Filter filter = new Filter();
        filter.setCscId("%");

        StudentsService studentsService = super.getBean("studentsService");

        Page<Student> studentPage = studentsService.getStudentsPageByFilter(filter, 0, 30);
        String fields="cscId,gender,age";
        String[] field=fields.split(",");
        for (Iterator iter = studentPage.getContent().iterator(); iter.hasNext(); ) {
            Student student = (Student) iter.next();
           String aa=student.getCscId();
            student.getBasicInfo().setGender("");

        }
        System.out.println("studentPage.getContent() = " + studentPage.getContent().size());

    }


}
