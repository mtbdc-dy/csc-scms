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
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by gc on 2015/7/17.
 * 保险管理控制器
 */
@RestController
@RequestMapping(value = "/scholarshipX")
public class ScholarshipXController {
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ScholarshipXService scholarshipXService;

    //用户在前台点击生奖学金评审列表，返回列表
    @RequestMapping(value = "/new", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String,String> getScholarshipXs(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        User user = null;
        List<OperationLog> operationLogs = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        }
        Map<String,String> result = scholarshipXService.getScholarshipXList(user);//保存日志

        return result;
    }

    //学校用户在前台点击查询，返回列表
    @RequestMapping(value = "/select", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> getScholarshipXsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                       @RequestParam(value = "filter") String filter) throws NoSuchUserException {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

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
            ScholarshipDetail scholarshipDetail;
            User user = userService.getUserByJWT(header);
            if (ScholarshipDetails.size() == 0) {
                return null;
            } else {
                for (int i = 0; i < ScholarshipDetails.size(); i++) {
                    scholarshipDetail = ScholarshipDetails.get(i);//前台获取的
                    //更新记录，保存日志
                    String id = scholarshipXService.saveScholarshipDetail(scholarshipDetail, user);
                }
                //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表

                int qualNum = 0;//合格人数
                int unqualNum = 0;//不合格人数
                Timestamp ts = new Timestamp(System.currentTimeMillis());
//                int year = ts.getYear() + 1900;
                int year = Calendar.getInstance().get(Calendar.YEAR);
                if(user.getUserType().equals("2")){
                    school = user.getNode().getNodeId();
                }
                List<ScholarshipX> scholarshipXlist = scholarshipXService.findScholarshipXBySchoolAndYear(school,year);
                if (user.getUserType().equals("2")) {//学校用户
                    for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                        ScholarshipX strX = (ScholarshipX) iter.next();
//                        if (strX.getYear() == year && strX.getSchool().equals(school)) {
                            if (strX.getSchReview().equals("AQ0001")) {
                                qualNum++;
                            } else {
                                unqualNum++;
                            }
//                        }
                    }
                } else {
                    for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                        ScholarshipX strX = (ScholarshipX) iter.next();
//                        if (strX.getYear() == year && strX.getSchool().equals(school)) {
                            if (strX.getCscReview().equals("AQ0001")) {
                                qualNum++;
                            } else {
                                unqualNum++;
                            }
//                        }
                    }
                }


                Scholarship scholarship = scholarshipXService.findScholarshipOne(ScholarshipDetails.get(0).getScholarship().getId());
                if (user.getUserType().equals("2")) {//学校用户
                    scholarship.setSchoolQual((long) qualNum);
                    scholarship.setSchoolUnQual((long) unqualNum);

                } else {//基金委用户进入进行修改时，更新csc的人数
                    scholarship.setCscQual((long) qualNum);
                    scholarship.setCscUnQual((long) unqualNum);

                }
                scholarship.setUpdated(ts);//同时对主表的更新人和更新时间，进行更新
                scholarship.setUpdateBy(user.getUserId());

                scholarshipXService.saveScholarship(scholarship, null);
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
                //查询该学生历史的schReview
                ScholarshipXResultObject scholarshipXLS = scholarshipXService.getScholarshipXAndStuBy(scholarshipDetail.getStudent().getId());
                if (scholarshipXLS != null && scholarshipXLS.getSchReview().equals("AQ0002")) {//上次结果存在且上次结果为不合格
                    scholarshipDetail.setSchResult("AP0002");//恢复
                } else {//上次为空，或者上次为合格
                    scholarshipDetail.setSchResult("AP0001");//继续
                }
                Student student = studentService.getStudentById(studentId);
                scholarshipDetail.setStudent(student);

                String id = scholarshipXService.savenewScholarshipDetail(scholarshipDetail, user);//插入新增记录

                //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
                Iterable scholarshipXlist = scholarshipXService.findScholarshipXAll();
                int qualNum = 0;//合格人数
                int unqualNum = 0;//不合格人数
                Timestamp ts = new Timestamp(System.currentTimeMillis());
//                int year = ts.getYear() + 1900;
                int year = Calendar.getInstance().get(Calendar.YEAR);
                String school = user.getNode().getNodeId();

                for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                    ScholarshipX strX = (ScholarshipX) iter.next();
                    if (strX.getYear() == year && strX.getSchool().equals(school)) {
                        if (strX.getSchReview().equals("AQ0001")) {
                            qualNum++;
                        } else {
                            unqualNum++;
                        }
                    }
                }
                Scholarship scholarship = scholarshipXService.findScholarshipOne(scholarshipDetail.getScholarship().getId());
                scholarship.setSchoolQual((long) qualNum);
                scholarship.setSchoolUnQual((long) unqualNum);
                scholarship.setUpdated(ts);//同时对主表的更新人和更新时间，进行更新
                scholarship.setUpdateBy(user.getUserId());
                scholarshipXService.saveScholarship(scholarship, null);
                ScholarshipXResultObject scholarshipXResult = scholarshipXService.getScholarshipXAndStu(scholarshipDetail.getId());

                return scholarshipXResult;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //删除
    @RequestMapping(value = "/{id}/{log}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> deleteScholarshipDetail(@PathVariable("id") String id, @PathVariable("log") String log, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            User user = userService.getUserByJWT(header);
            String[] id1;
            id1 = id.split(",");
            //获取主表id
            ScholarshipDetail ssD = scholarshipXService.getScholarshipDetailById(id1[1]);
            String scholarshipId = ssD.getScholarship().getId();

            List<ScholarshipXResultObject> scholarshipXResultObjectList = new ArrayList<ScholarshipXResultObject>();
            for (int i = 1; i < id1.length; i++) {
                ScholarshipDetail scholarshipDetail = scholarshipXService.deleteScholarshipDetailById(user, id1[i]);//保存日志
                if (scholarshipDetail == null) {
                    throw new NoSuchAbnormalException("cannot delete the scholarshipX,id=" + id1[i]);
                }
            }
            //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表

            int qualNum = 0;//合格人数
            int unqualNum = 0;//不合格人数
            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            int year = ts.getYear() + 1900;
            int year = Calendar.getInstance().get(Calendar.YEAR);
            String school = user.getNode().getNodeId();
            List<ScholarshipX> scholarshipXlist = scholarshipXService.findScholarshipXBySchoolAndYear(school,year);

            for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                ScholarshipX strX = (ScholarshipX) iter.next();
//                if (strX.getYear() == year && strX.getSchool().equals(school)) {
                    if (strX.getSchReview().equals("AQ0001")) {
                        qualNum++;
                    } else {
                        unqualNum++;
                    }
//                }
            }
            Scholarship scholarship = scholarshipXService.findScholarshipOne(scholarshipId);
            scholarship.setSchoolQual((long) qualNum);
            scholarship.setSchoolUnQual((long) unqualNum);
            scholarship.setUpdated(ts);//同时对主表的更新人和更新时间，进行更新
            scholarship.setUpdateBy(user.getUserId());
            scholarshipXService.saveScholarship(scholarship, null);//对主表进行更新
            Iterable scholarshipXlist1 = scholarshipXService.findScholarshipXAll();
            for (Iterator iter = scholarshipXlist1.iterator(); iter.hasNext(); ) {
                ScholarshipX strX = (ScholarshipX) iter.next();
                if (strX.getYear() == year && strX.getSchool().equals(school)) {
                    ScholarshipXResultObject scholarshipXResult = scholarshipXService.getScholarshipXAndStu(strX.getId());
                    scholarshipXResultObjectList.add(scholarshipXResult);
                }
            }
            return scholarshipXResultObjectList;
        } catch (Exception e) {
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
            ScholarshipDetail scholarshipDetail;
            User user = userService.getUserByJWT(header);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            if (ScholarshipDetails.size() == 0) {
                return null;
            } else {
                for (int i = 0; i < ScholarshipDetails.size(); i++) {
                    scholarshipDetail = ScholarshipDetails.get(i);
                    String id = scholarshipXService.saveScholarshipDetail(scholarshipDetail, user);//保存记录日志
                }
                //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
                String school = user.getNode().getNodeId();
                int qualNum = 0;//合格人数
                int unqualNum = 0;//不合格人数
                String scholarshipId = "";
//                int year = ts.getYear() + 1900;
                int year = Calendar.getInstance().get(Calendar.YEAR);
                List<ScholarshipX> scholarshipXlist = scholarshipXService.findScholarshipXBySchoolAndYear(school, year);
                for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                    ScholarshipX strX = (ScholarshipX) iter.next();
//                    if (strX.getYear() == year && strX.getSchool().equals(school)) {
//                        scholarshipId = strX.getScholarshipId();
                        if (strX.getSchReview().equals("AQ0001")) {
                            qualNum++;
                        } else {
                            unqualNum++;
                        }
//                    }
                }
                scholarshipId = scholarshipXlist.get(0).getScholarshipId();
                Scholarship scholarship = scholarshipXService.findScholarshipOne(scholarshipId);
                scholarship.setSchoolQual((long) qualNum);
                scholarship.setSchoolUnQual((long) unqualNum);
                scholarship.setCscQual((long) qualNum);//提交时把人数，赋值给基金委的对应字段
                scholarship.setCscUnQual((long) unqualNum);//提交时把人数，赋值给基金委的对应字段
                scholarship.setUpdated(ts);//同时对主表的更新人和更新时间，进行更新
                scholarship.setUpdateBy(user.getUserId());
                //对主表的状态进行更新，学校提交状态，和学校提交时间
                scholarship.setSchoolSta("1");//已提交
                scholarship.setSchoolDate(ts);//评审提交时间
                scholarship.setCscSta("0");//设置默认的基金委提交状态为“未提交”
                scholarshipXService.saveScholarship(scholarship, user, "1");//记录提交日志
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
     * 导出奖学金评审信息
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
        bytes = exportService.exportByFilter(tableName, "0", id);
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);

        return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
    }


    // 基金委跳转进来的相关操作
    // 基金委用户在前台点击查询，返回列表
    @RequestMapping(value = "/select/{school}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> getScholarshipXsByConditionsJ(@PathVariable("school") String school, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                        @RequestParam(value = "filter") String filter) throws NoSuchUserException {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

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
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
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
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            Page<ScholarshipX> scholarshipXPage = scholarshipXService.getScholarshipXsPagingByFilterJ(filter, page, size, mode, header, school);
            Page<Map<String, Object>> mapPage = scholarshipXPage.map(new ScholarshipXConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //新增学生时首先校验该学生是否已经存在于奖学金列表中
    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String,String> verifyInsuranceStudent(@PathVariable(value = "studentId") String studentId) {
        try {
            Map<String,String> result = scholarshipXService.verifyScholarshipXStudent(studentId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
