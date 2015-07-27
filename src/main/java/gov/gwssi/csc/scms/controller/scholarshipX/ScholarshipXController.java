package gov.gwssi.csc.scms.controller.scholarshipX;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipX;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
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
    //用户在前台点击生成机票管理列表，返回列表
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

//    //保存保险信息
//    @RequestMapping(value = "/{studentId}",  method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
//    public  ScholarshipXResultObject saveScholarshipX(@PathVariable(value = "studentId") String studentId,
//                                             @RequestBody String scholarshipXJson,@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//
//            JsonBody jbosy = new ObjectMapper().readValue(scholarshipXJson, JsonBody.class);
//
//            ScholarshipX scholarshipX = mapper.readValue(jbosy.getValue(), ScholarshipX.class);
//
//            if (scholarshipX == null) {
//                throw new NoSuchAbnormalException("cannot generate the ScholarshipX" );
//            }
//            scholarshipX.setStudentId(studentId);
//            User user = userService.getUserByJWT(header);
//            Timestamp ts = new Timestamp(System.currentTimeMillis());
//            scholarshipX.setCreateBy(user.getUserId());
//            scholarshipX.setCreated(ts);
//            scholarshipX.setYear(ts.getYear()+1900);
//            scholarshipX.setInsurSta("1");//未导出
//            scholarshipX.setPreSta("AV0001");//未导出
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
//            String id = scholarshipXService.saveScholarshipX(scholarshipX, operationLogs);
//            ScholarshipXResultObject  scholarshipXResult = scholarshipXService.getScholarshipXAndStu(id);
//            return scholarshipXResult;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }
//

    //删除保险信息
//    @RequestMapping(value = "/{id}/{log}",method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
//    public List<ScholarshipXResultObject> deleteScholarshipX(@PathVariable("id") String id, @PathVariable("log") String log) {
//        try {
//            ObjectMapper mapper = new ObjectMapper();
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
////                    JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
//            List<OperationLog> operationLogs  = mapper.readValue(log, javaType);
//            String[] id1;
//            id1=id.split(",");
//            ScholarshipX scholarshipX = new  ScholarshipX();
//            List<ScholarshipXResultObject> scholarshipXResultObjectList=new ArrayList<ScholarshipXResultObject>();
//            for (int i = 1; i <id1.length ; i++) {
//                ScholarshipXResultObject  scholarshipXResult = scholarshipXService.getScholarshipXAndStu(id1[i]);
//                scholarshipXResultObjectList.add(scholarshipXResult);
//                scholarshipX =  scholarshipXService.deleteScholarshipXById(id1[i], operationLogs);
//                if (scholarshipX == null) {
//                    throw new NoSuchAbnormalException("cannot delete the scholarshipX,id="+id1[i] );
//                }
//
//            }
//
//            return scholarshipXResultObjectList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

}
