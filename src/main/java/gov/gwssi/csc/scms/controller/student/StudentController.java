package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.UserException;
import gov.gwssi.csc.scms.service.dictionary.util.JsonMapper;
import gov.gwssi.csc.scms.service.student.*;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

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
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<StudentResultObject> getStudentsByConditions(
            @RequestParam(value = "filter") String filter, @RequestParam(value = "userId") String userId) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

            User user = userService.getUserByUserId(userId);
            if (user == null)
                throw new UserException("can't find the user by userId:" + userId);

            //按照分页（默认）要求，返回列表内容
            List<StudentResultObject> studentResultObjects = studentService.getStudentsByFilter(sfo, user);
            return studentResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (UserException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Student getStudentById(@PathVariable(value = "id") String id) {
        try {
            Student student = studentService.getStudentById(id);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
            return null;
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
            return null;
        }
    }

    @RequestMapping(value = "/{id}/{group}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Object getStudentGroupById(@PathVariable(value = "id") String id, @PathVariable("group") String group) {
        try {
            return studentService.getGroupByStudentId(id, group);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 更新学生相关信息
     *
     * @param id
     * @param group
     * @param body
     * @return
     */
    @RequestMapping(value = "/{id}/{group}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Object putStudentGroup(@PathVariable(value = "id") String id, @PathVariable("group") String group, @RequestBody String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(body, JsonBody.class);
            Object groupObj = updateStudentGroup(group, jbosy.getValue());
            if (groupObj == null)
                return null;

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            groupObj = studentService.updateGroupByName(group, groupObj, operationLogs);
            return groupObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
