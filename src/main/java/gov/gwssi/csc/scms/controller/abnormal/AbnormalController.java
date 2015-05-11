package gov.gwssi.csc.scms.controller.abnormal;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.AbnormalFilterObject;
import gov.gwssi.csc.scms.domain.query.AbnormalResultObject;
import gov.gwssi.csc.scms.domain.query.AddStudentResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.abnormal.AbnormalService;
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
    private UserService userService;

    //学校用户在前台点击异动申请菜单后，返回异动申请列表
    @RequestMapping(value = "/manager", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<AbnormalResultObject> getAbnormalsByConditions(@RequestParam(value = "filter") String filter, @RequestParam(value = "filter") String userId) {
        try {
            AbnormalFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), AbnormalFilterObject.class);

            User user = userService.getUserByUserId(userId);
            if (user == null) {
                throw new RuntimeException("no such user valid with userId:" + userId);
            }

            //按照分页（默认）要求，返回列表内容
            List<AbnormalResultObject> abnormalResultObjects = abnormalService.getAbnormalsByFilter(sfo, user);
            return abnormalResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //学校用户在前台点击新增申请，返回需要申请的学生信息列表
    @RequestMapping(value = "/add", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<AddStudentResultObject> getAddStudentsByConditions(@RequestParam(value = "filter") String filter, @RequestParam(value = "filter") String userId) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

            User user = userService.getUserByUserId(userId);
            if (user == null) {
                throw new RuntimeException("no such user valid with userId:" + userId);
            }

            //按照分页（默认）要求，返回列表内容
            List<AddStudentResultObject> studentResultObjects = abnormalService.getAddStudentsByFilter(sfo, user);
            return studentResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        return null;
    }

    //保存新增的异动申请
    @RequestMapping(value = "/save", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Abnormal putAbnormal( @RequestBody String abnormalJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(abnormalJson, JsonBody.class);

            Abnormal abnormal = mapper.readValue(jbosy.getValue(), Abnormal.class);
            if (abnormal == null)
                return null;

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            //根据id号是否为空来判断是新增还是修改
            if (null != abnormal.getId() || !"".endsWith(abnormal.getId())) {
                abnormal = abnormalService.updateAbnormal(abnormal, operationLogs);
            } else {
                abnormal = abnormalService.saveAbnormal(abnormal, operationLogs);
            }
            return abnormal;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
