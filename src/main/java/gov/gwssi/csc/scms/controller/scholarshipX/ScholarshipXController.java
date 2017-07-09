package gov.gwssi.csc.scms.controller.scholarshipX;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.UploadFileServer;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipXConverter;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipXService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by gc on 2015/7/17.
 * 奖学金评审管理（院校用户）控制器
 */
@RestController
@RequestMapping(value = "/scholarshipX")
public class ScholarshipXController {
    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScholarshipXService scholarshipXService;

    /**
     * 生成奖学金列表，调用存储过程p_scms_scholarship
     * @param header
     * @return
     * @throws NoSuchUserException
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String, String> getScholarshipXs(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        User user = null;
        List<OperationLog> operationLogs = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        }
        Map<String, String> result = scholarshipXService.getScholarshipXList(user);//保存日志

        return result;
    }

    //学校用户在前台点击查询，返回列表，此API没有用到
    @RequestMapping(value = "/select", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> getScholarshipXsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                       @RequestParam(value = "filter") String filter) throws NoSuchUserException {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(filter, StudentFilterObject.class);

            User user = userService.getUserByJWT(header);
            String userid = user.getUserId();
            //按照分页（默认）要求，返回列表内容
            List<ScholarshipXResultObject> scholarshipXResultObjects = scholarshipXService.getScholarshipXListByFilter(sfo, user);
            return scholarshipXResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            throw new RuntimeException(uee);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
            throw new RuntimeException(userIdentityError);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
            throw new RuntimeException(requestHeaderError);
        }

    }

    //根据detail的id，查询LIST,查询历史评审意见
    @RequestMapping(value = "/selectH/{cscId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> getScholarshipXsById(@PathVariable(value = "cscId") String cscId, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        List<ScholarshipXResultObject> scholarshipXResultObjectList = null;
        try {
            scholarshipXResultObjectList = scholarshipXService.getScholarshipXListcscId(cscId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scholarshipXResultObjectList;
    }

    //修改奖学金评审管理信息
    @RequestMapping(value = "/save/{school}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> modScholarshipXdetail(@PathVariable(value = "school") String school,
                                                                @RequestBody String scholarshipJson, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(scholarshipJson, JsonBody.class);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, ScholarshipDetail.class);
            List<ScholarshipDetail> ScholarshipDetails = mapper.readValue(jbosy.getValue(), javaType);
            List<ScholarshipXResultObject> scholarshipXResultObjects = new ArrayList<ScholarshipXResultObject>();
            User user = userService.getUserByJWT(header);
            if (ScholarshipDetails.size() == 0) {
                return null;
            } else {
                scholarshipXService.saveScholarshipDetails(ScholarshipDetails,user,school);
                ScholarshipDetail scholarshipDetail;
                for (int i = 0; i < ScholarshipDetails.size(); i++) {
                    scholarshipDetail = ScholarshipDetails.get(i);
                    ScholarshipXResultObject scholarshipXResult = scholarshipXService.getScholarshipXAndStu(scholarshipDetail.getId());
                    scholarshipXResultObjects.add(scholarshipXResult);
                }
                return scholarshipXResultObjects;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //新增一个学生，到奖学金评审记录当中，对奖学金详细子表进行新增保存
    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public ScholarshipXResultObject saveScholarshipX(@PathVariable(value = "studentId") String studentId,
                                                     @RequestBody String scholarshipJson, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(scholarshipJson, JsonBody.class);
            ScholarshipDetail scholarshipDetail = mapper.readValue(jbosy.getValue(), ScholarshipDetail.class);
            User user = userService.getUserByJWT(header);
            if (scholarshipDetail == null) {
                return null;
            } else {
                scholarshipDetail.setSchReview("AQ0001");//合格
                scholarshipDetail.setCscReview("AQ0001");
                //查询该学生历史的schReview
                ScholarshipXResultObject scholarshipXLS = scholarshipXService.getScholarshipXAndStuBy(scholarshipDetail.getStudent().getId());
                if (scholarshipXLS != null && scholarshipXLS.getSchReview().equals("AQ0002")) {//上次结果存在且上次结果为不合格
                    scholarshipDetail.setSchResult("AP0002");//恢复
                    scholarshipDetail.setCscResult("AP0002");
                } else {//上次为空，或者上次为合格
                    scholarshipDetail.setSchResult("AP0001");//继续
                    scholarshipDetail.setCscResult("AP0001");
                }
                Student student = studentService.getStudentById(studentId);
                scholarshipDetail.setStudent(student);

                String id = scholarshipXService.savenewScholarshipDetail(scholarshipDetail, user);//插入新增记录

                ScholarshipXResultObject scholarshipXResult = scholarshipXService.getScholarshipXAndStu(scholarshipDetail.getId());
                System.out.println(scholarshipXResult.getId() + " " + scholarshipXResult.getPassportName());
                return scholarshipXResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //删除
    @RequestMapping(value = "/{id}/{log}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Scholarship deleteScholarshipDetail(@PathVariable("id") String id, @PathVariable("log") String log, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            User user = userService.getUserByJWT(header);
            String[] id1;
            id1 = id.split(",");
            Scholarship scholarship = scholarshipXService.deleteScholarshipDetails(id1,user);
            return scholarship;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 提交奖学金评审列表时校验该奖学金评审列表的学生当前院校是否都为该院校
     * @param scholarshipId
     * @param school
     * @param header
     * @return
     */
    @RequestMapping(value = "/submitChecking/{scholarshipId}/{school}",method = RequestMethod.GET)
    public List<String> submitChecking(
            @PathVariable("scholarshipId") String scholarshipId,
            @PathVariable("school") String school,
            @RequestHeader(value=JWTUtil.HEADER_AUTHORIZATION) String header){
        try{
            List<String> cscIds = scholarshipXService.submitChecking(scholarshipId,school);
            return cscIds;
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //学校用户提交奖学金评审列表
    @RequestMapping(value = "/sub", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> subScholarship(@RequestBody String scholarshipJson, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(scholarshipJson, JsonBody.class);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, ScholarshipDetail.class);
            List<ScholarshipDetail> ScholarshipDetails = mapper.readValue(jbosy.getValue(), javaType);
            List<ScholarshipXResultObject> scholarshipXResultObjects = new ArrayList<ScholarshipXResultObject>();
            User user = userService.getUserByJWT(header);
            if (ScholarshipDetails.size() == 0) {
                return null;
            } else {
                scholarshipXService.subScholarship(ScholarshipDetails,user);
                ScholarshipDetail scholarshipDetail;
                for (int i = 0; i < ScholarshipDetails.size(); i++) {
                    scholarshipDetail = ScholarshipDetails.get(i);
                    ScholarshipXResultObject scholarshipXResult = scholarshipXService.getScholarshipXAndStu(scholarshipDetail.getId());
                    scholarshipXResultObjects.add(scholarshipXResult);
                }
                return scholarshipXResultObjects;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 导出奖学金评审信息，此API没有用到
     * GET /scholarshipX?ids=1,2,3 HTTP/1.1
     * Accept: application/octet-stream
     *
     * @param id 需要导出的保险信息ID
     */
    @RequestMapping(
            method = RequestMethod.GET,
            params = {"id"},
            headers = "Accept=application/octet-stream")
    public ResponseEntity<byte[]> exportInsurance(
            @RequestParam("id") String[] id) throws IOException {
        byte[] bytes = null;

        String tableName = "v_scholarship_lastyear";
        bytes = exportService.exportByFilter(tableName, "0", id, "");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

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
     * 导出奖学金评审信息
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            params = {"filter"}
    )
    public Map<String, Object> exportAllScholarshipX(
            @RequestHeader(value = HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        byte[] bytes = null;
        Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
        List<ScholarshipX> scholarshipXes = scholarshipXService.getAllScholarshipXByFilter(filter, header);
        String result[]=new String[scholarshipXes.size()];
        for(int i=0;i<scholarshipXes.size();i++){
            String id = scholarshipXes.get(i).getId();
            result[i] = id;
        }
        String tableName = "v_scholarship_lastyear";
        String type = "0";
        if("2".equals(scholarshipXes.get(0).getSchoolSta())){ //已批复
            type = "2";
        }
        bytes = exportService.exportByFilter(tableName, type, result, "");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式
        //上传至文件服务器
        String file = UploadFileServer.uploadFile(fileName, bytes);
        Map<String, Object> fileMap = new ObjectMapper().readValue(file, Map.class);
        return fileMap;
    }

    // 基金委用户在前台点击查询，返回列表，此API没有用到
    @RequestMapping(value = "/select/{school}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> getScholarshipXsByConditionsJ(@PathVariable("school") String school, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                        @RequestParam(value = "filter") String filter) throws NoSuchUserException {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(filter, StudentFilterObject.class);

            User user = userService.getUserByJWT(header);
            String userid = user.getUserId();
            //按照分页（默认）要求，返回列表内容
            List<ScholarshipXResultObject> scholarshipXResultObjects = scholarshipXService.getScholarshipXListByFilterJ(sfo, user, school);
            return scholarshipXResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            throw new RuntimeException(uee);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
            throw new RuntimeException(userIdentityError);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
            throw new RuntimeException(requestHeaderError);
        }

    }

    //分页查询
    //学校用户在前台点击查询，返回列表
    @Transactional
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "field", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getScholarshipXs(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "field") String[] fields,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
            Page<ScholarshipX> scholarshipXPage = scholarshipXService.getScholarshipXsPagingByFilter(filter, page, size, mode, header);
            Page<Map<String, Object>> mapPage = scholarshipXPage.map(new ScholarshipXConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //分页查询
    //基金委跳转进来的相关操作
    //基金委用户在前台点击查询，返回列表
    @Transactional
    @RequestMapping(
            value = "/scholarshipJ/{school}",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "field", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getScholarshipXsByConditionsJ(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @PathVariable("school") String school,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "field") String[] fields,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
            Page<ScholarshipX> scholarshipXPage = scholarshipXService.getScholarshipXsPagingByFilterJ(filter, page, size, mode, header, school);
            Page<Map<String, Object>> mapPage = scholarshipXPage.map(new ScholarshipXConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //新增学生时首先校验该学生是否已经存在于奖学金列表中
    @RequestMapping(value = "/addChecking/{studentId}/{scholarshipId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String, Integer> verifyInsuranceStudent(
            @PathVariable(value = "studentId") String studentId,
            @PathVariable(value = "scholarshipId") String scholarshipId) {
        try {
            Map<String, Integer> result = scholarshipXService.verifyScholarshipXStudent(studentId,scholarshipId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //进入院校奖学金界面时首先查询该院校当年奖学金状态SCHOOLSTA，若为未提交，或者查询结果为空，显示生成按钮，否则不显示生成按钮
    @RequestMapping(value = "/schoolSta/{school}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String, String> getSchoolSta(@PathVariable(value = "school") String school) {
        try {
            int year = Calendar.getInstance().get(Calendar.YEAR);
            Map<String, String> result = scholarshipXService.getSchoolSta(school, year);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
