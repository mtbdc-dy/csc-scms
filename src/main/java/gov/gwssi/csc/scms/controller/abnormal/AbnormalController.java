package gov.gwssi.csc.scms.controller.abnormal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.AbnormalResultObject;
import gov.gwssi.csc.scms.domain.query.AddStudentResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.abnormal.AbnormalService;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.student.NoSuchStudentException;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
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
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List<AbnormalResultObject> getAbnormalsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "filter") String filter) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

//            User user = userService.getUserByUserIdAndEnable(userId, User.ENABLE);
            User user = userService.getUserByJWT(header);
//            if (user == null)
//                throw new NoSuchUserException(userId);

            //按照分页（默认）要求，返回列表内容
            List<AbnormalResultObject> abnormalResultObjects = abnormalService.getAbnormalsByFilter(sfo, user);
            return abnormalResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            throw new RuntimeException(uee);
        }
        catch (NoSuchUserException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
            throw new RuntimeException(userIdentityError);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
            throw new RuntimeException(requestHeaderError);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    //保存新增的异动申请

    @RequestMapping(value = "/{studentId}",  method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public  AbnormalResultObject putAbnormal(@PathVariable(value = "studentId") String studentId,
                                             @RequestBody String abnormalJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(abnormalJson, JsonBody.class);

            Abnormal abnormal = mapper.readValue(jbosy.getValue(), Abnormal.class);

            if (abnormal == null) {
                throw new NoSuchAbnormalException("cannot generate the abnormal" );
            }
            abnormal.setStudentId(studentId);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            String id = abnormalService.saveAbnormal(abnormal, operationLogs);
            AbnormalResultObject  abnormalResult = abnormalService.getAbnormalAndStu(id);
            return abnormalResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //修改新增的异动申请
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public AbnormalResultObject modAbnormal(@RequestBody String abnormalJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            boolean rv = true;
            JsonBody jbosy = new ObjectMapper().readValue(abnormalJson, JsonBody.class);
            Abnormal abnormal = mapper.readValue(jbosy.getValue(), Abnormal.class);
            if (abnormal == null) {
                throw new NoSuchAbnormalException("cannot generate the abnormal" );
            } else {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            abnormal = abnormalService.updateAbnormal(abnormal, null);
            //修改成功后返回 修改的该条异动信息 包含学生信息
            AbnormalResultObject  abnormalResult = abnormalService.getAbnormalAndStu(abnormal.getId());
            return abnormalResult;

        }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //删除异动申请
            @RequestMapping(value = "/{id}/{log}",method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
            public  Abnormal deleteAbnormal(@PathVariable("id") String id, @PathVariable("log") String log) {
                try {
                    ObjectMapper mapper = new ObjectMapper();
                    JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//                    JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
                    List<OperationLog> operationLogs  = mapper.readValue(log, javaType);
                    Abnormal abnormal =  abnormalService.deleteAbnormalById(id, operationLogs);
                    if (abnormal == null) {
                        throw new NoSuchAbnormalException("cannot delete the abnormal" );
                    }
                    return abnormal;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
        }
    }

}
