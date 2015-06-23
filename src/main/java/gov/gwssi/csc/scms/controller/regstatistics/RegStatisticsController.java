package gov.gwssi.csc.scms.controller.regstatistics;

import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.log.LogQueryException;
import gov.gwssi.csc.scms.service.log.NoSupportedUserException;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.regstatistics.RegStatisticsService;
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
 * Created by Wangrui on 2015-06-22.
 * 日志查询API
 */

@RestController
@RequestMapping("/regstatistics")
public class RegStatisticsController {

    @Autowired
    private RegStatisticsService regStatisticsService;

    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;

    @RequestMapping(value = "/query", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<RegStatistics> getRegStatistics(@RequestParam(value = "province", required = false) String province,
                                       @RequestParam(value = "university", required = false) String university,
                                       @RequestParam("arrivalDateBegin") String arrivalDateBegin,
                                       @RequestParam("arrivalDateEnd") String arrivalDateEnd,
                                       @RequestHeader(value = HEADER_AUTHORIZATION) String header) {
        try {
            return regStatisticsService.getRegStatistics(province, university, arrivalDateBegin, arrivalDateEnd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
