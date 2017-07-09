package gov.gwssi.csc.scms.controller.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.UploadFileServer;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.students.StudentsService;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by gc on 2015/8/14
 * 导出管理控制器
 */
@RestController
@RequestMapping(value = "/export")
public class ExportController {
    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentsService studentsService;

    //此API没有用到
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportStudents(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;

        String[] tableName = {"v_sheet1_basic_info",
                "v_sheet2_profiles_history",
                "v_sheet3_registration_info",
                "v_sheet4_discuss",
                "v_sheet5_schoolroll",
                "v_sheet6_related_address",
                "v_sheet7_accident",
                "v_sheet8_airticket",
                "v_sheet9_grade",
                "v_sheet10_school_fellow"};
        bytes = exportService.exportByFilter(tableName, "0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }

    // 跨域请求
    @RequestMapping(
            value = "/all",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity exportInsuranceOptions() {
        HttpHeaders httpHeaders = new HttpHeaders();
        List<HttpMethod> methods = new ArrayList<HttpMethod>();
        methods.add(HttpMethod.GET);
        methods.add(HttpMethod.POST);
        methods.add(HttpMethod.DELETE);
        methods.add(HttpMethod.PUT);
        methods.add(HttpMethod.PATCH);
        httpHeaders.setAccessControlAllowMethods(methods);
        httpHeaders.setAccessControlAllowOrigin("*");
        List<String> headers = new ArrayList<String>();
        headers.add("authorization");
        httpHeaders.setAccessControlAllowHeaders(headers);
        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }

    /**
     * 导出学生信息，根据查询参数filter查询得到需要导出的学生id，指定导出视图数组viewName
     * 导出视图数组viewName
     * 基本信息：v_sheet1_basic_info
     * 来华前概况：v_sheet2_profiles_history
     * 申请信息：v_sheet3_registration_info
     * 商议信息：v_sheet4_discuss
     * 学籍信息：v_sheet5_schoolroll
     * 相关地址：v_sheet6_related_address
     * 突发事件：v_sheet7_accident
     * 机票信息：v_sheet8_airticket
     * 学习成绩：v_sheet9_grade
     * 校友信息：v_sheet10_school_fellow
     * 调用导出服务exportService的exportByFilter方法得到导出Excel的字节数组，上传至文件服务器，返回文件路径，
     * 前台根据文件路径请求文件服务器下载Excel文件。导出配置，在导出配置表SCMS_INI_EXPORT进行配置各sheet页导出信息
     * @param header
     * @param mode 模块名称
     * @param filterJSON 查询条件
     * @return
     * @throws IOException
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            params = {"mode", "filter"}
    )
    public Map<String,Object> exportStudnetsAll(
            @RequestHeader(value = HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
        byte[] bytes = null;
        String[] id = studentsService.getStudentsAllByFilter(filter, mode, header);
        User user = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] tableName;
        if ("1".equals(user.getUserType())) {
            String[] viewName = {"v_sheet1_basic_info",
                    "v_sheet2_profiles_history",
                    "v_sheet3_registration_info",
                    "v_sheet4_discuss",
                    "v_sheet5_schoolroll",
                    "v_sheet6_related_address",
                    "v_sheet7_accident",
                    "v_sheet8_airticket",
                    "v_sheet9_grade",
                    "v_sheet10_school_fellow"};
            tableName = viewName;
        } else {
            String[] viewName = {"v_sheet1_basic_info",
                    "v_sheet2_profiles_history",
                    "v_sheet3_registration_info",
                    "v_sheet5_schoolroll",
                    "v_sheet6_related_address",
                    "v_sheet7_accident",
                    "v_sheet8_airticket",
                    "v_sheet9_grade",
                    "v_sheet10_school_fellow"};
            tableName = viewName;
        }
        bytes = exportService.exportByFilter(tableName, "0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = ts.getTime() + ".xls"; // 组装附件名称和格式
        //上传至文件服务器
        String file = UploadFileServer.uploadFile(fileName, bytes);
        Map<String, Object> fileMap = new ObjectMapper().readValue(file, Map.class);
        return fileMap;
    }


}
