package gov.gwssi.csc.scms.controller.scholarshipJ;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipJConverter;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipJService;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipXService;
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
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

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

    //根据传入的id对主表的基金委提交状态和时间进行更新，批复
    @RequestMapping(value = "sub/{ids}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List subScholarshipJ(@PathVariable("ids") String ids, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            String[] id=ids.split(",");
            User user = userService.getUserByJWT(header);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            //对主表进行更新
            for(int i=0;i<id.length;i++){
                Scholarship scholarship = scholarshipXService.findScholarshipOne(id[i]);
                scholarship.setUpdated(ts);//同时对主表的更新人和更新时间，进行更新
                scholarship.setUpdateBy(user.getUserId());
                scholarship.setCscSta("1");//更新为已提交
                scholarship.setCscDate(ts);
                //对学校的相关字段进行更新
                scholarship.setSchoolSta("2");//已批复
                scholarship.setSchoolQual(scholarship.getCscQual());//人数
                scholarship.setSchoolUnQual(scholarship.getCscUnQual());
                scholarshipXService.saveScholarship(scholarship, user,"2");//对主表进行更新,并保存批复日志

                //对子表进行更新，批复后，把csc的相关值，都赋值给school的相关字段
//                List detailList = scholarshipJService.findDetailListBy(id[i]);//找到主表对应的所有子表
//                for ( int j=0;j<detailList.size();j++) {
//                    HashMap strD = (HashMap) detailList.get(j);
//                    ScholarshipDetail scholarshipDetail=scholarshipXService.getScholarshipDetailById((String)strD.get("ID"));
//                    scholarshipDetail.setSchStartTime((Date) strD.get("CSCSTARTTIME"));
//                    scholarshipDetail.setSchEndTime((Date) strD.get("CSCENDTIME"));
//                    scholarshipDetail.setSchReason((String) strD.get("CSCREASON"));
//                    scholarshipDetail.setSchResult((String) strD.get("CSCRESULT"));
//                    scholarshipDetail.setSchReview((String) strD.get("CSCREVIEW"));
//                    scholarshipXService.saveScholarshipDetail(scholarshipDetail,user);
//                }
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
     * @param id     需要导出的主表信息的scholarshipId
     */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportInsurance(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;
        String ids=null;
        //将scholarshipId转化成id
        for ( int i =0;i<id.length;i++) {
            List detailList = scholarshipJService.findDetailListExport(id[i]);//找到主表对应的所有子表数据（筛选出cscResult不为AP0001）
            for(int j=0;j<detailList.size();j++){
                HashMap strD = (HashMap) detailList.get(j);
               ids=ids+","+strD.get("ID");
            }
        }
        String[] id1=null;
        if(ids!=null){
           id1=ids.split(",");//转化后的id数组
        }
        String tableName = "v_scholarship_lastyear";//对主表对应的所有信息以学生为单位导出
        bytes = exportService.exportByFilter(tableName,"1", id1);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }

    /**
     *查询得到导出数据总数
     */
    @RequestMapping(
            value = "/getTotalNum",
            method = RequestMethod.GET,
            params = {"filter"},
            headers = "Accept=application/json")
    public Map<String,Integer> getScholarshipJExportTotalNum(
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
        String id[] = scholarshipJService.getAllScholarshipJsByFilter(filter);
        int count = 0;
        for ( int i =0;i<id.length;i++) {
            List detailList = scholarshipJService.findDetailListExport(id[i]);//找到主表对应的所有子表数据（筛选出cscResult不为AP0001）
            count += detailList.size();
        }
        Map<String,Integer> resutlt = new HashMap<String, Integer>();
        resutlt.put("totalNum",count);
        return resutlt;
    }

    /**
     *全部导出功能
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            params = {"filter"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportInsurance(
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
        String id[] = scholarshipJService.getAllScholarshipJsByFilter(filter);
        byte[] bytes = null;
        String ids=null;
        //将scholarshipId转化成id
        for ( int i =0;i<id.length;i++) {
            List detailList = scholarshipJService.findDetailListExport(id[i]);//找到主表对应的所有子表数据（筛选出cscResult不为AP0001）
            for(int j=0;j<detailList.size();j++){
                HashMap strD = (HashMap) detailList.get(j);
                ids=ids+","+strD.get("ID");
            }
        }
        String[] id1=null;
        if(ids!=null){
            id1=ids.split(",");//转化后的id数组
        }
        String tableName = "v_scholarship_lastyear";//对主表对应的所有信息以学生为单位导出
        bytes = exportService.exportByFilter(tableName, "1", id1);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }
    //分页查询
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getScholarshipJs(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            Page<ScholarshipJ> scholarshipJPage = scholarshipJService.getScholarshipJsPagingByFilter(filter, page, size, mode);
            Page<Map<String, Object>> mapPage = scholarshipJPage.map(new ScholarshipJConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //统计未上报已上报学校数量
    @RequestMapping(
            value = "/scholarshipJSchoolNum",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"filter"})
    public Map<String,Long> getScholarshipJSchoolNum(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        Map<String, Long> result=new HashMap<String,Long >();
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            result = scholarshipJService.getScholarshipJSchoolNum(filter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }



}
