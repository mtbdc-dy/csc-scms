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

    @RequestMapping(value = "/query/{startTime}/{endTime}/{menuId}/{optType}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<OperationLog> queryLog(@PathVariable("startTime") String startTime,
                                       @PathVariable("endTime") String endTime,
                                       @PathVariable("menuId") String menuId,
                                       @PathVariable("optType") String optType,
                                       @RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            User user = getUserByHeader(header);
            return doQuery(user, startTime, endTime, menuId, optType);
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

    private List<OperationLog> doQuery(User user, String startTime, String endTime, String menuId, String optType) throws ParseException, LogQueryException, NoSupportedUserException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        if (isNull(startTime))
            throw new LogQueryException("start time can not be null!");

        if (isNull(endTime))
            throw new LogQueryException("end time can not be null!");

        Date startDate = sdf.parse(startTime);
        Date endDate = sdf.parse(endTime);
        if (isNull(menuId) && isNull(optType)) {
            return doQueryWithOnlyTime(user, startDate, endDate);
        } else if (isNotNull(menuId) && isNotNull(optType)) {
            return doQueryWithAllCondition(user, startDate, endDate, menuId, optType);
        } else {
            if (isNull(optType)) {
                return doQueryWithMenuId(user, startDate, endDate, menuId);
            } else {
                return doQueryWithOptType(user, startDate, endDate, optType);
            }
        }
    }

    private List<OperationLog> doQueryWithOptType(User user, Date startDate, Date endDate, String optType) throws NoSupportedUserException {
        return operationLogService.doQueryWithOptType(user, startDate, endDate, optType);
    }

    private List<OperationLog> doQueryWithMenuId(User user, Date startDate, Date endDate, String menuId) throws NoSupportedUserException {
        return operationLogService.doQueryWithMenuId(user, startDate, endDate, menuId);
    }

    private List<OperationLog> doQueryWithAllCondition(User user, Date startDate, Date endDate, String businessModule, String optType) throws NoSupportedUserException {
        return operationLogService.doQueryWithAllCondition(user, startDate, endDate, businessModule, optType);
    }

    private List<OperationLog> doQueryWithOnlyTime(User user, Date startDate, Date endDate) throws NoSupportedUserException {
        return operationLogService.doQueryWithOnlyTime(user, startDate, endDate);
    }

    private boolean isNull(String str) {
        return ("null".equalsIgnoreCase(str));
    }

    private boolean isNotNull(String str) {
        return !("null".equalsIgnoreCase(str));
    }

}
