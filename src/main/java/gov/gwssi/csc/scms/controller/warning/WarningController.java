package gov.gwssi.csc.scms.controller.warning;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.WarningResultObject;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.domain.warning.Warning;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.service.warning.NoSuchWarningException;
import gov.gwssi.csc.scms.service.warning.WarningService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by tianjing on 2015/7/16.
 */
@RestController
@RequestMapping(value = "/blacklist")
public class WarningController {

    @Autowired
    private WarningService warningService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List<WarningResultObject> getStudentsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                             @RequestParam(value = "filter") String filter) {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);
            User user = userService.getUserByJWT(header);
            List<WarningResultObject> warningResultObjects = warningService.getWarningByFilter(sfo, user);
            return warningResultObjects;
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


    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public WarningResultObject putWarning(@PathVariable(value = "studentId") String studentId,
                                          @RequestBody String warningJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(warningJson, JsonBody.class);

            Warning warning = mapper.readValue(jbosy.getValue(), Warning.class);

            if (warning == null) {
                throw new NoSuchWarningException("cannot generate the warning");
            }
            warning.setStudentId(studentId);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            String id = warningService.saveWarning(warning, operationLogs);
            WarningResultObject warningResult = warningService.getWarningAndStu(id);
            return warningResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //删除
    @RequestMapping(value = "/{id}/{log}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Warning deleteWarning(@PathVariable("id") String id, @PathVariable("log") String log) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//          JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
            List<OperationLog> operationLogs = mapper.readValue(log, javaType);
            Warning warning = warningService.deleteWarningById(id, operationLogs);
            if (warning == null) {
                throw new NoSuchWarningException("cannot delete the warning");
            }
            return warning;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
//    public Warning getWarningById(@PathVariable(value = "id") String id) {
//        try {
//            Warning warning = warningService.getWarningById(id);
//            return warning;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public Warning getWarningByStudentId(@PathVariable(value = "studentId") String studentId) {
        try {
            Warning warning = warningService.getWarningByStudentId(studentId);
            return warning;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
