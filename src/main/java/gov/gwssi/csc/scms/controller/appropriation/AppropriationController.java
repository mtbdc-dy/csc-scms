package gov.gwssi.csc.scms.controller.appropriation;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.appropriation.Appropriation;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.appropriation.AppropriationConverter;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.appropriation.AppropriationService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by gc on 2015/8/28.
 * 经费统计控制器
 */
@RestController
@RequestMapping(value = "/appropriation")
public class AppropriationController {
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private AppropriationService appropriationService;

    //基金委用户在前台点击查询，返回列表
//    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
//    public List getListBy(@RequestParam(value = "appropriation") String appropriation,
//                          @RequestParam(value = "planned") String planned,
//                          @RequestParam(value = "projectAttr") String projectAttr,
//                          @RequestParam(value = "projectType") String projectType,
//                          @RequestParam(value = "projectName") String projectName) {
//        List allList = null;
//        List listParameter = new ArrayList();//存储过程传入参数
//        listParameter.add("");
//        listParameter.add(appropriation);
//        listParameter.add("");
//        listParameter.add(planned);
//        listParameter.add(projectAttr);
//        listParameter.add(projectType);
//        listParameter.add(projectName);
//        allList = appropriationService.getList(listParameter);
//        System.out.println();
//        return allList;
//    }
    //分页统计
    @RequestMapping(
            value = "statistics",
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
            Page<Appropriation> appropriationsPage = appropriationService.getAppropriationsPagingByFilter(filter, page, size);
            Page<Map<String, Object>> mapPage = appropriationsPage.map(new AppropriationConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    /**
     * 导出经费统计数据
     * GET /appropriation?ids=1,2,3 HTTP/1.1
     * Accept: application/octet-stream
     *
     * @param id     需要导出的经费统计的表ID
     */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportInsurance(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;

        String tableName = "v_exp_appropriation";
        bytes = exportService.exportByfilter(tableName,"0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }



}
