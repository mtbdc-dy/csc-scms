package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.student.*;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by WangZishi on 3/27/2015.
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    /**
     * 学籍信息管理相关操作，获取学生列表
     * 请求信息为Json格式对应的StudentFilterObject类
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<StudentResultObject> getStudentsByConditions(
            @RequestParam(value = "filter") String filter, @RequestParam(value = "userId") String userId) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

            User user = userService.getUserByUserIdAndEnable(userId, User.ENABLE);
            if (user == null)
                throw new NoSuchUserException(userId);

            //按照分页（默认）要求，返回列表内容
            List<StudentResultObject> studentResultObjects = studentService.getStudentsByFilter(sfo, user);
            return studentResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            throw new RuntimeException(uee);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchUserException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Student getStudentById(@PathVariable(value = "id") String id) {
        try {
            Student student = studentService.getStudentById(id);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Student putStudent(@PathVariable(value = "id") String id, @RequestBody String studentJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(studentJson, JsonBody.class);

            Student student = mapper.readValue(jbosy.getValue(), Student.class);
            if (student == null)
                return null;

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            student = studentService.saveStudent(student, operationLogs);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Student deleteStudent(@PathVariable(value = "id") String id) {
        try {
            List<OperationLog> operationLogs = null;
            Student student = studentService.deleteStudentById(id, operationLogs);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{id}/{group}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Object getStudentGroupById(@PathVariable(value = "id") String id, @PathVariable("group") String group) {
        try {
            return studentService.getGroupByStudentId(id, group);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 更新学生相关信息
     *
     * @param id
     * @param group 修改的对象
     * @param mode 用于区分修改前判断是否已经有group的完整信息，
     *             若有，则updateGroupByName方法直接修改，若无先获取 再set需要修改的数据项并保存
     * @param body 修改后的数据项和日志
     * @return
     */
    @RequestMapping(value = "/{id}/{group}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Object putStudentGroup(@PathVariable(value = "id") String id, @PathVariable("group") String group,
                                  @RequestParam(value = "mode") String mode, @RequestBody String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(body, JsonBody.class);
            //Json转成对象 包含修改后的信息
            Object groupObj = updateStudentGroup(group, jbosy.getValue());
            if (groupObj == null)
                throw new NoSuchStudentException("cannot find the student for update" );

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            if("withoutDetail".equals(mode)){//无Detail 需要传studentId
                groupObj = studentService.updateGroupByName(id, group, groupObj, operationLogs);
            }else{
                groupObj = studentService.updateGroupByName(group, groupObj, operationLogs);
            }

            return groupObj;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 手动离华管理
     *
     * @param studentIds :"["1","2"...]"
     * @param body 修改后的数据项和日志
     * @return
     */
    @RequestMapping(value = "/leaveChina/{studentIds}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public String putStudentLeaveChina(@PathVariable(value = "studentIds") String studentIds, @RequestBody String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(body, JsonBody.class);
            //Json转成对象 包含修改后的信息
            SchoolRoll schoolRoll = mapper.readValue(jbosy.getValue(), SchoolRoll.class);
            if (studentIds == null || studentIds.equals(""))
                throw new RequestHeaderError("no student is selected!");
            if (schoolRoll == null)
                throw new NoSuchStudentException("cannot find the student for update" );

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            studentService.leaveChina(studentIds, schoolRoll, operationLogs);

            return null;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private Object updateStudentGroup(String group, String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object groupObject = null;

        if ("basicInfo".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, Schoolfellow.class);
        }
        if ("accident".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Accident.class);
            groupObject = mapper.readValue(body, javaType);
        }
        if ("relatedAddress".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, RelatedAddress.class);
            groupObject = mapper.readValue(body, javaType);
        }
        if ("grade".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Grade.class);
            groupObject = mapper.readValue(body, javaType);
        }
        if ("gradeAttachment".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, GradeAttachment.class);
            groupObject = mapper.readValue(body, javaType);
        }
        return groupObject;
    }
}
