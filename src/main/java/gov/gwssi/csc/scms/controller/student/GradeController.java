package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.Grade;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.GradeService;
import gov.gwssi.csc.scms.service.student.NoSuchGradeException;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjing on 2015/8/4.
 */

@RestController
@RequestMapping(value = "/students")
public class GradeController {
    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeService gradeService;

    @RequestMapping(value = "/{studentId}/grades", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Grade putGrade(@PathVariable(value = "studentId") String studentId,
                          @RequestBody String gradeJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(gradeJson, JsonBody.class);

            Grade grade = mapper.readValue(jbosy.getValue(), Grade.class);

            if (grade == null) {
                throw new NoSuchGradeException("cannot generate the grade");
            }
            Student student = studentService.getStudentById(studentId);
            grade.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            grade = gradeService.saveGradeAndLog(grade, operationLogs);
            grade.setStudent(null);
            return grade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //删除
    @RequestMapping(value = "/{studentId}/grades/{gradeId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Grade deleteGrade(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header, @PathVariable(value = "studentId") String studentId, @PathVariable(value = "gradeId") String gradeId) {
        try {
            User user = userService.getUserByJWT(header);
            Student student = studentService.getStudentById(studentId);
            Grade grade = gradeService.deleteGradeById(student, user, gradeId);
            if (grade == null) {
                throw new NoSuchGradeException("cannot delete the grade");
            }
            grade.setStudent(null);
            return grade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //修改
    @RequestMapping(value = "/{studentId}/grades", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Grade editGrade(@PathVariable(value = "studentId") String studentId, @RequestBody String gradeJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(gradeJson, JsonBody.class);

            Grade grade = mapper.readValue(jbosy.getValue(), Grade.class);

            if (grade == null) {
                throw new NoSuchGradeException("cannot edit the grade");
            }

            Student student = studentService.getStudentById(studentId);
            grade.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            grade = gradeService.editGrade(grade, operationLogs);
            grade.setStudent(null);
            return grade;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
