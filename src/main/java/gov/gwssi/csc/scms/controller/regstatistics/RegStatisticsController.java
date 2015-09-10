package gov.gwssi.csc.scms.controller.regstatistics;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.regstatistics.RegStatistics;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.log.LogQueryException;
import gov.gwssi.csc.scms.service.log.NoSupportedUserException;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.regstatistics.RegStatisticsConverter;
import gov.gwssi.csc.scms.service.regstatistics.RegStatisticsService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
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
    @Autowired
    private UserService userService;
    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List<RegStatistics> getRegStatistics(@RequestParam("type") String type,
                                        @RequestParam("province") String province,
                                       @RequestParam("university") String university,
                                       @RequestParam("arrivalDateBegin") String arrivalDateBegin,
                                       @RequestParam("arrivalDateEnd") String arrivalDateEnd) {
        try {
            province = null == province||"null".equals(province)?"":province;
            university = null == university||"null".equals(university)?"":university;
            arrivalDateBegin = null == arrivalDateBegin||"null".equals(arrivalDateBegin)?"":arrivalDateBegin.replace("-","");
            arrivalDateEnd = null == arrivalDateEnd||"null".equals(arrivalDateEnd)?"":arrivalDateEnd.replace("-","");
            return regStatisticsService.getRegStatistics(type, province, university, arrivalDateBegin, arrivalDateEnd);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //分页统计
    @RequestMapping(
            value = "/{type}",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getOldStuTimeSet(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @PathVariable(value = "type") String type,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User user = userService.getUserByJWT(header);
            Page<RegStatistics> regStatisticsPage = regStatisticsService.getRegStatisticsPagingByFilter(filter, page, size, mode, user,type);
            Page<Map<String, Object>> mapPage = regStatisticsPage.map(new RegStatisticsConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
