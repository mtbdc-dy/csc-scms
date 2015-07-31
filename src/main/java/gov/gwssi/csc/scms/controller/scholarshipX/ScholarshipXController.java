package gov.gwssi.csc.scms.controller.scholarshipX;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.scholarship.ScholarshipXService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 机票管理控制器
 */
@RestController
@RequestMapping(value = "/scholarshipX")
public class ScholarshipXController {
    @Autowired
    private UserService userService;
    @Autowired
    private ScholarshipXService scholarshipXService;

    //用户在前台点击生奖学金评审列表，返回列表
    @RequestMapping(value = "/new",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> getScholarshipXs(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        User user = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        }
        List<ScholarshipXResultObject> scholarshipXResultObjectList = scholarshipXService.getScholarshipXList(user);
        return scholarshipXResultObjectList;
    }
    //学校用户在前台点击查询，返回列表
            @RequestMapping(value = "/select",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
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

    //修改奖学金评审管理信息
    @RequestMapping(value = "/save", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public List<ScholarshipXResultObject> modScholarshipXdetail(@RequestBody String scholarshipJson, @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
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
                    scholarshipDetail = ScholarshipDetails.get(i);
                    String id = scholarshipXService.saveScholarshipDetail(scholarshipDetail, null);
                }
                //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
                Iterable scholarshipXlist = scholarshipXService.findScholarshipXAll();
                int qualNum = 0;//合格人数
                int unqualNum = 0;//不合格人数
                Timestamp ts = new Timestamp(System.currentTimeMillis());
                int year=ts.getYear()+1900;
                String school=user.getNode().getNodeId();

                for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                    ScholarshipX strX = (ScholarshipX) iter.next();
                    if(strX.getYear()==year && strX.getSchool().equals(school)){
                        if (strX.getSchReview().equals("AQ0001")) {
                            qualNum++;
                        } else {
                            unqualNum++;
                        }
                    }
                }
                Scholarship scholarship = scholarshipXService.findScholarshipOne(ScholarshipDetails.get(0).getScholarship().getId());
                scholarship.setQualNum((long) qualNum);
                scholarship.setUnQualNum((long) unqualNum);
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
    @RequestMapping(value = "/{studentId}",  method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public  ScholarshipXResultObject saveScholarshipX(@PathVariable(value = "studentId") String studentId,
                                             @RequestBody String scholarshipJson,@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
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
                 ScholarshipXResultObject scholarshipXLS =scholarshipXService.getScholarshipXAndStuBy(scholarshipDetail.getStudentId());
                 if(scholarshipXLS!=null && scholarshipXLS.getSchReview().equals("AQ0002")){//上次结果存在且上次结果为不合格
                     scholarshipDetail.setSchResult("AP0002");//恢复
                 }else{//上次为空，或者上次为合格
                     scholarshipDetail.setSchResult("AP0001");//继续
                 }
                 String id = scholarshipXService.savenewScholarshipDetail(scholarshipDetail, null);//插入新增记录

                 //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
                 Iterable scholarshipXlist = scholarshipXService.findScholarshipXAll();
                 int qualNum = 0;//合格人数
                 int unqualNum = 0;//不合格人数
                 Timestamp ts = new Timestamp(System.currentTimeMillis());
                 int year=ts.getYear()+1900;
                 String school=user.getNode().getNodeId();

                 for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                     ScholarshipX strX = (ScholarshipX) iter.next();
                     if(strX.getYear()==year && strX.getSchool().equals(school)){
                         if (strX.getSchReview().equals("AQ0001")) {
                             qualNum++;
                         } else {
                             unqualNum++;
                         }
                     }
                 }
                 Scholarship scholarship = scholarshipXService.findScholarshipOne(scholarshipDetail.getScholarship().getId());
                 scholarship.setQualNum((long) qualNum);
                 scholarship.setUnQualNum((long) unqualNum);
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
    public List<ScholarshipXResultObject> deleteScholarshipDetail(@PathVariable("id") String id, @PathVariable("log") String log,@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//                    JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
            List<OperationLog> operationLogs = mapper.readValue(log, javaType);
            User user = userService.getUserByJWT(header);
            String[] id1;
            id1 = id.split(",");
            List<ScholarshipXResultObject> scholarshipXResultObjectList = new ArrayList<ScholarshipXResultObject>();
            for (int i = 1; i < id1.length; i++) {
                ScholarshipDetail scholarshipDetail = scholarshipXService.deleteScholarshipDetailById(id1[i], operationLogs);
                if (scholarshipDetail == null) {
                    throw new NoSuchAbnormalException("cannot delete the scholarshipX,id=" + id1[i]);
                }
            }
            //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
            Iterable scholarshipXlist = scholarshipXService.findScholarshipXAll();
            int qualNum = 0;//合格人数
            int unqualNum = 0;//不合格人数
            String scholarshipId = "";
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            int year=ts.getYear()+1900;
            String school=user.getNode().getNodeId();

            for (Iterator iter = scholarshipXlist.iterator(); iter.hasNext(); ) {
                ScholarshipX strX = (ScholarshipX) iter.next();

                if(strX.getYear()==year && strX.getSchool().equals(school)){
                    scholarshipId=strX.getScholarshipId();
                    if (strX.getSchReview().equals("AQ0001")) {
                        qualNum++;
                    } else {
                        unqualNum++;
                    }
                }
            }
            Scholarship scholarship = scholarshipXService.findScholarshipOne(scholarshipId);
            scholarship.setQualNum((long) qualNum);
            scholarship.setUnQualNum((long) unqualNum);
            scholarshipXService.saveScholarship(scholarship, null);//对主表进行更新
            Iterable scholarshipXlist1 = scholarshipXService.findScholarshipXAll();
            for (Iterator iter = scholarshipXlist1.iterator(); iter.hasNext(); ) {
                ScholarshipX strX = (ScholarshipX) iter.next();
                if(strX.getYear()==year && strX.getSchool().equals(school)) {
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
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            if (ScholarshipDetails.size() == 0) {
                return null;
            } else {
                for (int i = 0; i < ScholarshipDetails.size(); i++) {
                    scholarshipDetail = ScholarshipDetails.get(i);
                    String id = scholarshipXService.saveScholarshipDetail(scholarshipDetail, null);
                }
                //子表全部保存完成后，对主表的合格，不合格人数进行重新统计并更新主表
                Iterable scholarshipDetaillist = scholarshipXService.findScholarshipDetailAll();
                int qualNum = 0;//合格人数
                int unqualNum = 0;//不合格人数
                for (Iterator iter = scholarshipDetaillist.iterator(); iter.hasNext(); ) {
                    ScholarshipDetail strDetail = (ScholarshipDetail) iter.next();
                    if (strDetail.getSchReview().equals("AQ0001")) {
                        qualNum++;
                    } else {
                        unqualNum++;
                    }
                }
                Scholarship scholarship = scholarshipXService.findScholarshipOne(ScholarshipDetails.get(0).getScholarship().getId());
                scholarship.setQualNum((long) qualNum);
                scholarship.setUnQualNum((long) unqualNum);
                //对主表的状态进行更新，学校提交状态，和学校提交时间
                scholarship.setSchoolSta("1");//已提交
                scholarship.setSchoolDate(ts);//评审提交时间
                scholarship.setCscSta("0");//设置默认的基金委提交状态为“未提交”
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


}
