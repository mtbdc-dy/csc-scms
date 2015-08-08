package gov.gwssi.csc.scms.service.students;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.filter.Filter;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Date;

/**
 * Created by wang Zishi on 15/8/7.
 * 学生服务单元测试
 */
public class StudentsServiceTest extends UnitTestBase {

    @Test
    public void testGetStudents() throws Exception {

//        StudentsService studentsService = super.getBean("studentsService");
//
//        Page<Student> studentPage = studentsService.getStudentsByFilter();
//
//        System.out.println("studentPage = " + studentPage);
//        if (studentPage.hasNext()){
//            studentPage.nextPageable();
//            System.out.println("studentPage = " + studentPage);
//        }
        Filter filter = new Filter();
        filter.setAnnual(new Date());

        for (Field field : filter.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.get(filter) == null) {
                    System.out.println("field.get(filter) = " + field.get(filter));
                } else {
//                    System.out.println(field.get + " = " + field.get(filter));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


}
