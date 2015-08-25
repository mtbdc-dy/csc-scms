package gov.gwssi.csc.scms.service.students;

// import gov.gwssi.csc.scms.base.UnitTestBase;

import gov.gwssi.csc.scms.domain.student.Student;
import org.junit.Test;

import java.lang.reflect.Field;

/**
 * Created by wang Zishi on 15/8/7.
 * 学生服务单元测试
 */
public class StudentsServiceTest /* extends UnitTestBase */ {

    @Test
    public void testGetStudents() throws Exception {
        Student student = new Student();
        student.setCscId("csc2015010101");

        for (Field field : student.getClass().getDeclaredFields()) {
            System.out.println("field = " + field.getName());
        }

        Field field = student.getClass().getDeclaredField("cscId");
        field.setAccessible(true);
        System.out.println(field.get(student));
        field.set(student, "Wonderful!");
        System.out.println(field.get(student));

        System.out.println(student.getClass().getDeclaredMethod("getCscId").invoke(student));

        Student abc = student.clone();
        System.out.println(abc.getCscId());
    }


}
