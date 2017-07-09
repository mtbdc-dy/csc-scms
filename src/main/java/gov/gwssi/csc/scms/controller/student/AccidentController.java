package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.Accident;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.AccidentService;
import gov.gwssi.csc.scms.service.student.NoSuchAccidentException;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianjing on 2015/7/31.
 * 突发事件控制器
 */

@RestController
@RequestMapping(value = "/students")
public class AccidentController {
    @Autowired
    private AccidentService accidentService;

    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    /**
     * 新增突发事件
     * @param studentId 学生id
     * @param accidentJson 突发事件信息
     * @return
     */
    @RequestMapping(value = "/{studentId}/accidents", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Accident putAccident(@PathVariable(value = "studentId") String studentId,
                                @RequestBody String accidentJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(accidentJson, JsonBody.class);

            Accident accident = mapper.readValue(jbosy.getValue(), Accident.class);

            if (accident == null) {
                throw new NoSuchAccidentException("cannot generate the accident");
            }
            Student student = studentService.getStudentById(studentId);
            accident.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            accident = accidentService.saveAccidentAndLog(accident, operationLogs);
            accident.setStudent(null);
            return accident;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除突发事件
     * @param header
     * @param studentId 学生id
     * @param accidentId 突发事件id
     * @return
     */
    @RequestMapping(value = "/{studentId}/accidents/{accidentId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Accident deleteAccident(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header, @PathVariable(value = "studentId") String studentId, @PathVariable(value = "accidentId") String accidentId) {
        try {
            User user = userService.getUserByJWT(header);
            Student student = studentService.getStudentById(studentId);
            Accident accident = accidentService.deleteAccidentById(student, user, accidentId);
            if (accident == null) {
                throw new NoSuchAccidentException("cannot delete the accident");
            }
            return accident;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 修改突发事件
     * @param studentId 学生id
     * @param accidentId 突发事件id
     * @param accidentJson 突发事件信息
     * @return
     */
    @RequestMapping(value = "/{studentId}/accidents/{accidentId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Accident editAccident(@PathVariable(value = "studentId") String studentId, @PathVariable(value = "accidentId") String accidentId,
                                 @RequestBody String accidentJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(accidentJson, JsonBody.class);

            Accident accident = mapper.readValue(jbosy.getValue(), Accident.class);

            if (accident == null) {
                throw new NoSuchAccidentException("cannot edit the accident");
            }

            Student student = studentService.getStudentById(studentId);
            accident.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            accident = accidentService.editAccident(accident, operationLogs);
            accident.setStudent(null);
            return accident;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
