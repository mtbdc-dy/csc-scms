package gov.gwssi.csc.scms.controller.timeset;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.universities.DimUniv;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.timeset.TimeSetConverter;
import gov.gwssi.csc.scms.service.timeset.TimeSetService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by LiZhiSheng on 2015/8/10.
 */
@RestController
@RequestMapping(value = "/timeset")
public class TimeSetController {
    @Autowired
    private TimeSetService timeSetService;
    @Autowired
    private UserService userService;

    //点击查询返回列表
//    @RequestMapping(value = "/newstu",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
//    public List getALLCode(@RequestParam(value = "pro") String pro,@RequestParam(value = "univ") String univ) {
//        //按照分页（默认）要求，返回列表内容
//        List proAndUnivList = null;
//        if(pro ==null||"null".equals(pro)){
//            pro = "";
//        }
//        if(univ ==null||"null".equals(univ)||"undefined".equals(univ)){
//            univ = "";
//        }
//        proAndUnivList = timeSetService.findProAndUniv(pro, univ);
//
//        return proAndUnivList;
//    }

    //设置选中院校报到期限
    @RequestMapping(value = "/newstus",
            method = RequestMethod.PUT,
            headers = {"Accept=application/json; charset=utf-8;"})
    public void save(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                     @RequestParam(value = "ids") String ids, @RequestParam(value = "begin") String begin, @RequestParam(value = "end") String end) {
        try {
            User user = userService.getUserByJWT(header);
            String userName = user.getFullName();
            timeSetService.setTime(userName, begin, end, ids);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
    }

    //设置新生全部院校报到期限
    @RequestMapping(value = "/newstus/all",
            method = RequestMethod.PUT,
            headers = {"Accept=application/json; charset=utf-8;"},
            params = {"filter","begin","end"})
    public void saveNewAll(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                           @RequestParam(value = "filter") String filterJSON,
                           @RequestParam(value = "begin") String begin,
                           @RequestParam(value = "end") String end) {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User user = userService.getUserByJWT(header);
            String ids = timeSetService.getAllDimUnivsIdsByFilter(filter,user);
            String userName = user.getFullName();
            timeSetService.setTime(userName, begin, end, ids);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    //老生
    //点击查询返回列表
    @RequestMapping(value = "/oldstu", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getALLOld(@RequestParam(value = "pro") String pro, @RequestParam(value = "univ") String univ) {
        //按照分页（默认）要求，返回列表内容
        List proAndUnivList = null;
        if (pro == null || "null".equals(pro)) {
            pro = "";
        }
        if (univ == null || "null".equals(univ) || "undefined".equals(univ)) {
            univ = "";
        }
        proAndUnivList = timeSetService.findOldProAndUniv(pro, univ);

        return proAndUnivList;
    }

    //返回列表
    @RequestMapping(value = "/oldstus",
            method = RequestMethod.PUT,
            headers = {"Accept=application/json; charset=utf-8;"})
    public void saveOld(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                        @RequestParam(value = "ids") String ids, @RequestParam(value = "begin") String begin, @RequestParam(value = "end") String end) {

        System.out.println("ids=" + ids);

        try {
            User user = userService.getUserByJWT(header);
            String userName = user.getFullName();
            timeSetService.setOldTime(userName, begin, end, ids);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }


    }

    //分页查询 老生
    @RequestMapping(
            value = "/oldstu",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getOldStuTimeSet(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,

            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User user = userService.getUserByJWT(header);
            Page<DimUniv> dimUnivsPage = timeSetService.getDimUnivsPagingByFilter(filter, page, size, mode, user);
            Page<Map<String, Object>> mapPage = dimUnivsPage.map(new TimeSetConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //分页查询 新生
    @RequestMapping(
            value = "/newstu",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getNewStuTimeSet(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,

            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User user = userService.getUserByJWT(header);
            Page<DimUniv> dimUnivsPage = timeSetService.getDimUnivsPagingByFilter(filter, page, size, mode, user);
            Page<Map<String, Object>> mapPage = dimUnivsPage.map(new TimeSetConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 新生注册前查询操作人员（学校用户）的报到期限设置，若系统时间不在之内，则返回false,否则返回true
     */
    @RequestMapping(value = "/freshRegister/{nodeId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String, String> getFreshRegisterTimeSet(@PathVariable(value = "nodeId") String nodeId) {
        try {
            Map<String, String> result = timeSetService.getFreshRegisterTimeSet(nodeId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 老生注册前查询操作人员（学校用户）的报到期限设置，若系统时间不在之内，则返回false,否则返回true
     */
    @RequestMapping(value = "/oldRegister/{nodeId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String, String> getOldRegisterTimeSet(@PathVariable(value = "nodeId") String nodeId) {
        try {
            Map<String, String> result = timeSetService.getOldRegisterTimeSet(nodeId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
