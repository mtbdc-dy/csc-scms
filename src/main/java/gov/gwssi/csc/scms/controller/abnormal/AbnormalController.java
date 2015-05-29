package gov.gwssi.csc.scms.controller.abnormal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.AbnormalFilterObject;
import gov.gwssi.csc.scms.domain.query.AbnormalResultObject;
import gov.gwssi.csc.scms.domain.query.AddStudentResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.abnormal.AbnormalService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by lzs on 2015/4/23.
 * 移动申请控制器
 */
@RestController
@RequestMapping("/abnormal")
public class AbnormalController {
    @Autowired
    private AbnormalService abnormalService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;

    //学校用户在前台点击异动申请菜单后，返回异动申请列表
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<AbnormalResultObject> getAbnormalsByConditions(@RequestParam(value = "filter") String filter, @RequestParam(value = "userId") String userId) {
        try {
            AbnormalFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), AbnormalFilterObject.class);

//            User user = userService.getUserByUserId(userId);
//            if (user == null) {
//                throw new NoSuchUserException(userId);
//            }

            //按照分页（默认）要求，返回列表内容
            List<AbnormalResultObject> abnormalResultObjects = abnormalService.getAbnormalsByFilter(sfo, null);
            return abnormalResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//        catch (NoSuchUserException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    //学校用户在前台点击新增申请，返回需要申请的学生信息列表
//    @RequestMapping(value = "/add", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
//    public List<AddStudentResultObject> getAddStudentsByConditions(@RequestParam(value = "filter") String filter, @RequestParam(value = "filter") String userId) {
//        try {
//            StudentFilterObject sfo = null;
//            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);
//
//            User user = userService.getUserByUserId(userId);
//            if (user == null) {
//                throw new RuntimeException("no such user valid with userId:" + userId);
//            }
//
//            //按照分页（默认）要求，返回列表内容
//            List<AddStudentResultObject> studentResultObjects = abnormalService.getAddStudentsByFilter(sfo, user);
//            return studentResultObjects;
//        } catch (UnsupportedEncodingException uee) {
//            uee.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (NoSuchUserException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    //保存新增的异动申请

    @RequestMapping( method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public  Abnormal putAbnormal(@RequestBody String abnormalJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(abnormalJson, JsonBody.class);

            Abnormal abnormal = mapper.readValue(jbosy.getValue(), Abnormal.class);

            if (abnormal == null)
                return null;

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

                abnormal = abnormalService.saveAbnormal(abnormal, operationLogs);
            return abnormal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //修改新增的异动申请
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Abnormal modAbnormal(@RequestBody String abnormalJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            boolean rv = true;
            JsonBody jbosy = new ObjectMapper().readValue(abnormalJson, JsonBody.class);
            Abnormal abnormal = mapper.readValue(jbosy.getValue(), Abnormal.class);
            if (abnormal == null) {
                return null;
            } else {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            abnormal = abnormalService.updateAbnormal(abnormal, null);
             return abnormal;

        }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //删除异动申请
            @RequestMapping(value = "/{id}",method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
            public  Abnormal deleteAbnormal(@PathVariable String id) {
                try {
            List<OperationLog> operationLogs = null;
                    Abnormal abnormal =  abnormalService.deleteAbnormalById(id, operationLogs);
                   return abnormal;
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
        }
    }

}
