package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.service.export.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.NoSuchStudentException;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

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

    @Autowired
    private ExportService exportService;

    /**
     * 学籍信息管理相关操作，获取学生列表
     * 请求信息为Json格式对应的StudentFilterObject类
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List<StudentResultObject> getStudentsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "filter") String filter) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);
//            User user = userService.getUserByUserIdAndEnable(userId, User.ENABLE);

            User user = userService.getUserByJWT(header);
//            if (user == null)
//                throw new NoSuchUserException(userId);

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
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
            throw new RuntimeException(userIdentityError);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
            throw new RuntimeException(requestHeaderError);
        }
    }


    /**
     * 在校生学籍信息管理相关操作，获取学生列表
     * 请求信息为Json格式对应的StudentFilterObject类
     */
    @RequestMapping(value = "/schoolstudent", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List<StudentResultObject> getSchoolStudentsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                             @RequestParam(value = "filter") String filter) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);
//            User user = userService.getUserByUserIdAndEnable(userId, User.ENABLE);

            User user = userService.getUserByJWT(header);
//            if (user == null)
//                throw new NoSuchUserException(userId);

            //按照分页（默认）要求，返回列表内容
            List<StudentResultObject> studentResultObjects = studentService.getSchoolStudentsByFilter(sfo, user);
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
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
            throw new RuntimeException(userIdentityError);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
            throw new RuntimeException(requestHeaderError);
        }
    }

    /**
     * 离校生学籍信息管理相关操作，获取学生列表
     * 请求信息为Json格式对应的StudentFilterObject类
     */
    @RequestMapping(value = "/leavestudent", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List<StudentResultObject> getLeaveStudentsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                   @RequestParam(value = "filter") String filter) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);
//            User user = userService.getUserByUserIdAndEnable(userId, User.ENABLE);

            User user = userService.getUserByJWT(header);
//            if (user == null)
//                throw new NoSuchUserException(userId);

            //按照分页（默认）要求，返回列表内容
            List<StudentResultObject> studentResultObjects = studentService.getLeaveStudentsByFilter(sfo, user);
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
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
            throw new RuntimeException(userIdentityError);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
            throw new RuntimeException(requestHeaderError);
        }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public Student getStudentById(@PathVariable(value = "id") String id) {
        try {
            Student student = studentService.getCompleteInfoOfStudentById(id);
//            List<Insurance> insuranceList = student.getInsurances();
            ObjectMapper mapper = new ObjectMapper();
            mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

            System.out.println(mapper.writeValueAsString(student));

//            SerializationFeature.FAIL_ON_EMPTY_BEANS
//            insuranceList

//            if(insuranceList != null && insuranceList.size()>0){
//                List<Insurance> insurances = new ArrayList<Insurance>();
//                long max = insuranceList.get(0).getYear();
//                for(int i=1;i<insuranceList.size();i++){
//                    if(insuranceList.get(i).getYear()>max){
//                        max = insuranceList.get(i).getYear();
//                    }
//                }
//                for(int j=0;j<insuranceList.size();j++){
//                    Insurance insurance = insuranceList.get(j);
//                    if(insurance.getInsurSta().equals("1")&&insurance.getYear() == max){
//                        insurance.setStudent(null);
//                        insurances.add(insurance);
//                        break;
//                    }
//                }
//                student.setInsurances(insurances);
//            }
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *根据日志修改学生数据项
     * @param logJson 日志对象 从日志中获取修改的表和数据项
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public String putStudent(@PathVariable(value = "id") String id, @RequestParam(value = "dbType") String dbType, @RequestBody String logJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            OperationLog operationLog = new ObjectMapper().readValue(logJson, OperationLog.class);
            Student student = studentService.getStudentById(id);
            if (student == null)
                return null;

            String after = studentService.saveStudent(dbType,operationLog);
            if(!"".equals(after)){
                studentService.updateRegistState(operationLog);
            }
            return "{\"after\":\""+after+"\"}";
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

//    @RequestMapping(value = "/{id}/{group}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
//    public Object getStudentGroupById(@PathVariable(value = "id") String id, @PathVariable("group") String group) {
//        try {
//            return studentService.getGroupByStudentId(id, group);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

//    /**
//     * 更新学生相关信息
//     *
//     * @param id
//     * @param group 修改的对象
//     * @param mode 用于区分修改前判断是否已经有group的完整信息，
//     *             若有，则updateGroupByName方法直接修改，若无先获取 再set需要修改的数据项并保存
//     * @param body 修改后的数据项和日志
//     * @return
//     */
//    @RequestMapping(value = "/{id}/{group}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
//    public Object putStudentGroup(@PathVariable(value = "id") String id, @PathVariable("group") String group,
//                                  @RequestParam(value = "mode") String mode, @RequestBody String body) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JsonBody jbosy = new ObjectMapper().readValue(body, JsonBody.class);
//            //Json转成对象 包含修改后的信息
//            Object groupObj = updateStudentGroup(group, jbosy.getValue());
//            if (groupObj == null)
//                throw new NoSuchStudentException("cannot find the student for update" );
//
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
//            if("withoutDetail".equals(mode)){//无Detail 需要传studentId
//                groupObj = studentService.updateGroupByName(id, group, groupObj, operationLogs);
//            }else{
//                groupObj = studentService.updateGroupByName(group, groupObj, operationLogs);
//            }
//
//            return groupObj;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 新生老生注册功能
     *
     * @param studentId
     * @param mode 区分是注册 还是 放弃来华
     * @param body 修改后的数据项和日志
     * @return
     */
    @RequestMapping(value = "/register/{studentId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public String putStudentRegister(@PathVariable(value = "studentId") String studentId,
                                     @RequestParam(value = "mode") String mode, @RequestBody String body) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(body, JsonBody.class);
            //Json转成对象 包含修改后的信息
            //SchoolRoll schoolRoll = mapper.readValue(jbosy.getValue(), SchoolRoll.class);
            if (studentId == null || studentId.equals(""))
                throw new RequestHeaderError("no student is selected!");
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            studentService.registerorAbandon(mode, studentId, operationLogs);

            return null;
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

    /**
     * 导出报到注册信息
     * GET
     * Accept: application/octet-stream
     *
     * @param id
     */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportSturegister(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;

        String tableName = "v_exp_register";
        bytes = exportService.exportByfilter(tableName,"0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }
}
