package gov.gwssi.csc.scms.controller.insurance;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.corba.se.spi.orbutil.fsm.Input;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.InsuranceResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.insurance.InsuranceService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.Buffer;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 机票管理控制器
 */
@RestController
@RequestMapping(value = "/insurance")
public class InsuranceController {
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private InsuranceService insuranceService;
    //用户在前台点击生成机票管理列表，返回列表
    @RequestMapping(value = "/new",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<InsuranceResultObject> getInsurances(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        User user = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        }
        List<InsuranceResultObject> insuranceResultObjectList = insuranceService.getInsuranceList(user);
        return insuranceResultObjectList;
    }
    //学校用户在前台点击查询，返回列表
            @RequestMapping(value = "/select",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
            public List<InsuranceResultObject> getInsurancesByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                   @RequestParam(value = "filter") String filter) throws NoSuchUserException {
                try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

                    User user = userService.getUserByJWT(header);
                    String userid = user.getUserId();
            //按照分页（默认）要求，返回列表内容
            List<InsuranceResultObject> insuranceResultObjects = insuranceService.getInsuranceListByFilter(sfo, user);
            return insuranceResultObjects;
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

    //保存保险信息
    @RequestMapping(value = "/{studentId}",  method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public  InsuranceResultObject saveInsurance(@PathVariable(value = "studentId") String studentId,
                                             @RequestBody String insuranceJson,@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(insuranceJson, JsonBody.class);

            Insurance insurance = mapper.readValue(jbosy.getValue(), Insurance.class);

            if (insurance == null) {
                throw new NoSuchAbnormalException("cannot generate the Insurance" );
            }
            insurance.setStudentId(studentId);
            User user = userService.getUserByJWT(header);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            insurance.setCreateBy(user.getUserId());
            insurance.setCreated(ts);
            insurance.setYear(ts.getYear()+1900);
            insurance.setInsurSta("1");//未导出
            insurance.setPreSta("AV0001");//未导出
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            String id = insuranceService.saveInsurance(insurance, operationLogs);
            InsuranceResultObject  insuranceResult = insuranceService.getInsuranceAndStu(id);
            return insuranceResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //删除保险信息
    @RequestMapping(value = "/{id}/{log}",method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public List<InsuranceResultObject> deleteInsurance(@PathVariable("id") String id, @PathVariable("log") String log) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//                    JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
            List<OperationLog> operationLogs  = mapper.readValue(log, javaType);
            String[] id1;
            id1=id.split(",");
            Insurance insurance = new  Insurance();
            List<InsuranceResultObject> insuranceResultObjectList=new ArrayList<InsuranceResultObject>();
            for (int i = 1; i <id1.length ; i++) {
                InsuranceResultObject  insuranceResult = insuranceService.getInsuranceAndStu(id1[i]);
                insuranceResultObjectList.add(insuranceResult);
                insurance =  insuranceService.deleteInsuranceById(id1[i], operationLogs);
                if (insurance == null) {
                    throw new NoSuchAbnormalException("cannot delete the insurance,id="+id1[i] );
                }

            }

            return insuranceResultObjectList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //导出保险信息
    @RequestMapping(value = "/{ids}/{tablename}/{log}", method = RequestMethod.GET, headers = "Accept=application/vnd.ms-excel; charset=utf-8")
    public void exportInsurance(@PathVariable("ids") String ids, @PathVariable("tablename") String tablename, @PathVariable("log") String log,HttpServletResponse httpServletResponse ) {
        OutputStream outputStream =null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//                    JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
            List<OperationLog> operationLogs = mapper.readValue(log, javaType);
            outputStream = httpServletResponse.getOutputStream();
            exportService.exportByfilter(tablename, ids, outputStream);
//            wb.write(outputStream);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String fileName = "tablename_"+ts;
            httpServletResponse.getOutputStream();
           // httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");// 组装附件名称和格式
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally
        {
            try
            {
                outputStream.flush();
                outputStream.close();
            }
            catch (IOException e)
            {}
        }
//        return outputStream.
    }


}
