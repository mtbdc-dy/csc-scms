package gov.gwssi.csc.scms.service.students;

// import gov.gwssi.csc.scms.base.UnitTestBase;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.warning.Warning;
import gov.gwssi.csc.scms.repository.student.StudentRepository;
import gov.gwssi.csc.scms.repository.warning.WarningRepository;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by wang Zishi on 15/8/7.
 * 学生服务单元测试
 */
public class StudentsServiceTest extends UnitTestBase {

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

    @Test
    public void deleteTest(){
        StudentRepository studentRepository = getBean(StudentRepository.class);
        WarningRepository warningRepository = getBean(WarningRepository.class);
        Student student = studentRepository.findOne("3");
        String warningId = student.getWarning().getWarningId();
        student.setWarning(null);
        studentRepository.save(student);
        warningRepository.delete(warningId);

        System.out.println("student.getWarning() = " + student);
    }

    @Test
    public void warningTest(){
//        StudentRepository studentRepository = getBean(StudentRepository.class);
        WarningRepository warningRepository = getBean(WarningRepository.class);

        Warning warning = warningRepository.findOne("2015082500000000145");
        Student student = warning.getStudent();

        String[] fields = {"id", "warning.warningId"};
        StudentConverter studentConverter = new StudentConverter(fields);

        Map<String, Object> map = studentConverter.convert(student);

        System.out.println("map = " + map);
    }


}
