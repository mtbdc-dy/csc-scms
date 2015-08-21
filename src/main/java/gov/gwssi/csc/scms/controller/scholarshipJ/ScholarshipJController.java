package gov.gwssi.csc.scms.controller.scholarshipJ;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.ScholarshipJResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipJService;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipXService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * Created by gc on 2015/8/18.
 * 奖学金评审管理控制器
 */
@RestController
@RequestMapping(value = "/scholarshipJ")
public class ScholarshipJController {
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScholarshipJService scholarshipJService;
    @Autowired
    private ScholarshipXService scholarshipXService;


    //基金委用户在前台点击查询，返回列表
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getListBy(@RequestParam(value = "year") String year,
                          @RequestParam(value = "state") String state,
                          @RequestParam(value = "province") String province,
                          @RequestParam(value = "univ") String univ,
                          @RequestParam(value = "qualified") String qualified) {
        //按照分页（默认）要求，返回列表内容
        List allList = null;
        if(year ==null||"null".equals(year)){
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            int cunyear=ts.getYear()+1900;//为空时，默认为当前年份
            year = String.valueOf(cunyear);
        }
        allList = scholarshipJService.findListBy(year, state, province, univ,qualified);

        return allList;
    }

    //根据传入的id对主表的基金委提交状态和时间进行更新
    @RequestMapping(value = "sub/{ids}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List subScholarshipJ(@PathVariable("ids") String ids, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            String[] id=ids.split(",");
            User user = userService.getUserByJWT(header);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
            for(int i=0;i<id.length;i++){
                Scholarship scholarship = scholarshipXService.findScholarshipOne(id[i]);
                scholarship.setUpdated(ts);//同时对主表的更新人和更新时间，进行更新
                scholarship.setUpdateBy(user.getId());
                scholarship.setCscSta("1");//更新为已提交
                scholarship.setCscDate(ts);
                scholarshipXService.saveScholarship(scholarship, null);//对主表进行更新
            }
            List allList = null;
            int cunyear=ts.getYear()+1900;//为空时，默认为当前年份

            allList = scholarshipJService.findListBy(String.valueOf(cunyear), "", "", "","");

            return allList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }



    /**
     * 导出奖学金评审信息
     * GET /scholarshipJ?id=1,2,3 HTTP/1.1
     * Accept: application/octet-stream
     *
     * @param id     需要导出的主表信息ID
     */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportInsurance(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;

        String tableName = "v_scholarship_unreport";
        bytes = exportService.exportByfilter(tableName, id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }




}
