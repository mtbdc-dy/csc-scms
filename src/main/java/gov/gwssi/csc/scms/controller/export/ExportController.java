package gov.gwssi.csc.scms.controller.export;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.students.StudentsService;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;

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

    /**
     * 导出保险信息
     * GET /insurance?ids=1,2,3 HTTP/1.1
     * Accept: application/octet-stream
     *
     * @param id     需要导出的保险信息ID
     */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportStudents(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;

        String[] tableName = {"v_sheet1_basic_info" ,
                "v_sheet2_profiles_history" ,
                "v_sheet3_registration_info" ,
                "v_sheet4_discuss" ,
                "v_sheet5_schoolroll" ,
                "v_sheet6_related_address",
                "v_sheet7_accident",
                "v_sheet8_airticket",
                "v_sheet9_grade",
                "v_sheet10_school_fellow"};
        bytes = exportService.exportByFilter(tableName,"0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            params = {"mode","filter"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportStudnetsAll(
            @RequestHeader(value = HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
        byte[] bytes = null;
        String [] id = studentsService.getStudentsAllByFilter(filter, mode, header);

        String[] tableName = {"v_sheet1_basic_info" ,
                "v_sheet2_profiles_history" ,
                "v_sheet3_registration_info" ,
                "v_sheet4_discuss" ,
                "v_sheet5_schoolroll" ,
                "v_sheet6_related_address",
                "v_sheet7_accident",
                "v_sheet8_airticket",
                "v_sheet9_grade",
                "v_sheet10_school_fellow"};
        bytes = exportService.exportByFilter(tableName,"0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }



}
