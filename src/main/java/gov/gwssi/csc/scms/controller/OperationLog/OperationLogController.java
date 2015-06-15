package gov.gwssi.csc.scms.controller.OperationLog;

import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.log.LogQueryException;
import gov.gwssi.csc.scms.service.log.NoSupportedUserException;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Lei on 2015-06-11.
 * 日志查询API
 */

@RestController
@RequestMapping("/log")
public class OperationLogController {

    @Autowired
    private OperationLogService operationLogService;

    @Autowired
    private UserService userService;

    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;

    @RequestMapping(value = "/query", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<OperationLog> queryLog(@RequestParam("beginTime") String beginTime,
                                       @RequestParam("endTime") String endTime,
                                       @RequestParam(value = "moduleId", required = false) String moduleId,
                                       @RequestParam(value = "optType", required = false) String optType,
                                       @RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            User user = getUserByHeader(header);
            return doQuery(user, beginTime, endTime, moduleId, optType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private User getUserByHeader(String header) throws RequestHeaderError, NoSuchUserException {
        Map map = JWTUtil.decode(header);
        if (map == null)
            throw new RequestHeaderError("can not read the header message!");

        Object userId = map.get("userId");
        if (userId == null)
            throw new RequestHeaderError("can not read the invalid message!");

        User user = userService.getUserByUserIdAndEnable((String) userId, User.ENABLE);
        if (user == null)
            throw new NoSuchUserException("can not find login user by token!");

        return user;
    }

    private List<OperationLog> doQuery(User user, String beginTime, String endTime, String moduleId, String optType) throws ParseException, LogQueryException, NoSupportedUserException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        if (isNull(beginTime))
            throw new LogQueryException("start time can not be null!");

        if (isNull(endTime))
            throw new LogQueryException("end time can not be null!");

        Date startDate = sdf.parse(beginTime);
        Date endDate = sdf.parse(endTime);
        if (isNull(moduleId) && isNull(optType)) {
            return doQueryWithOnlyTime(user, startDate, endDate);
        } else if (isNotNull(moduleId) && isNotNull(optType)) {
            return doQueryWithAllCondition(user, startDate, endDate, moduleId, optType);
        } else {
            if (isNull(optType)) {
                return doQueryWithModuleId(user, startDate, endDate, moduleId);
            } else {
                return doQueryWithOptType(user, startDate, endDate, optType);
            }
        }
    }

    private List<OperationLog> doQueryWithOptType(User user, Date startDate, Date endDate, String optType) throws NoSupportedUserException {
        return operationLogService.doQueryWithOptType(user, startDate, endDate, optType);
    }

    private List<OperationLog> doQueryWithModuleId(User user, Date startDate, Date endDate, String moduleId) throws NoSupportedUserException {
        return operationLogService.doQueryWithModuleId(user, startDate, endDate, moduleId);
    }

    private List<OperationLog> doQueryWithAllCondition(User user, Date startDate, Date endDate, String moduleId, String optType) throws NoSupportedUserException {
        return operationLogService.doQueryWithAllCondition(user, startDate, endDate, moduleId, optType);
    }

    private List<OperationLog> doQueryWithOnlyTime(User user, Date startDate, Date endDate) throws NoSupportedUserException {
        return operationLogService.doQueryWithOnlyTime(user, startDate, endDate);
    }

    private boolean isNull(String str) {
        return ("null".equalsIgnoreCase(str) || str == null);
    }

    private boolean isNotNull(String str) {
        return !("null".equalsIgnoreCase(str) || str == null);
    }

}
