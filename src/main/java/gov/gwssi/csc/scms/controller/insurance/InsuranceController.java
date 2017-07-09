package gov.gwssi.csc.scms.controller.insurance;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.dao.insurance.InsuranceDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.InsuranceResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.UploadFileServer;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.export.ExportService;
import gov.gwssi.csc.scms.service.insurance.InsuranceConverter;
import gov.gwssi.csc.scms.service.insurance.InsuranceService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;

//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileUploadException;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by gc on 2015/7/17.
 * 保险管理控制器
 */
@RestController
@RequestMapping(value = "/insurance")
public class InsuranceController {
    private static final String HEADER_AUTHORIZATION = JWTUtil.HEADER_AUTHORIZATION;
    @Autowired
    private ExportService exportService;
    @Autowired
    private UserService userService;
    @Autowired
    private InsuranceService insuranceService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private InsuranceDAO importDao;
    public static Map<String, List> MAP = new HashMap<String, List>();

    //点击查询返回代码维护列表，此API没有用到
    @RequestMapping(value = "/getkey", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getValue(@RequestParam(value = "key") String key) {
        //按照分页（默认）要求，返回列表内容
        List proAndUnivList = null;
        if (key == null || "null".equals(key)) {

            return new ArrayList();
        } else {
            //System.out.println("hehe="+MAP.get(key));
            return MAP.get(key);
        }
        //return null;


    }

    /**
     * 生成投保清单，调用存储过程p_scms_insurance，传入参数’1’表示正式投保清单
     * @param header
     * @return
     * @throws NoSuchUserException
     */
    @RequestMapping(value = "/new", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String,String> getInsurances(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        User user = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        }
        Map<String,String> result = insuranceService.getInsuranceList(user);
        return result;
    }

    //学校用户在前台点击查询，返回列表，此API没有用到
    @RequestMapping(value = "/select", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<InsuranceResultObject> getInsurancesByConditions(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "filter") String filter) throws NoSuchUserException {
        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(filter, StudentFilterObject.class);

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

    /**
     * 新增保险信息
     * @param studentId 学生id
     * @param insuranceJson 保险信息
     * @param header
     * @return
     */
    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public InsuranceResultObject saveInsurance(
            @PathVariable(value = "studentId") String studentId,
            @RequestBody String insuranceJson,
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(insuranceJson, JsonBody.class);

            Insurance insurance = mapper.readValue(jbosy.getValue(), Insurance.class);

            if (insurance == null) {
                throw new NoSuchAbnormalException("cannot generate the Insurance");
            }
            Student student = studentService.getStudentById(studentId);
            insurance.setStudent(student);
            User user = userService.getUserByJWT(header);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            insurance.setCreateBy(user.getUserId());
            insurance.setCreated(ts);
            insurance.setYear(ts.getYear() + 1900);
            insurance.setInsurSta("1");//未导出
            insurance.setPreSta("AV0001");//未导出
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
            String id = insuranceService.saveInsurance(insurance, operationLogs);
            InsuranceResultObject insuranceResult = insuranceService.getInsuranceAndStu(id);
            return insuranceResult;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 删除保险信息
     * @param id 保险id
     * @param log 日志信息
     * @return
     */
    @RequestMapping(value = "/{id}/{log}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public List<InsuranceResultObject> deleteInsurance(@PathVariable("id") String id, @PathVariable("log") String log) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//                    JsonBody jbosy = new ObjectMapper().readValue(log, JsonBody.class);
            List<OperationLog> operationLogs = mapper.readValue(log, javaType);
            String[] id1;
            id1 = id.split(",");
            List<InsuranceResultObject> insuranceResultObjectList = new ArrayList<InsuranceResultObject>();
            for (int i = 1; i < id1.length; i++) {
                InsuranceResultObject insuranceResult = insuranceService.getInsuranceAndStu(id1[i]);
                insuranceResultObjectList.add(insuranceResult);
                insuranceService.deleteInsuranceById(id1[i], operationLogs);
            }
            return insuranceResultObjectList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 导出保险信息，此API没有用到
     * GET /insurance?ids=1,2,3 HTTP/1.1
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

        String tableName = "v_exp_insurance";
        bytes = exportService.exportByFilter(tableName, "0", id, "");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.setContentDispositionFormData("attachment", fileName);
        insuranceService.updateInsurancePresta(id);//导出后，根据传入的id数组进行批量更新导出状态

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
     * 导出保险信息，首先查询得到需导出保险id,配置导出视图v_exp_insurance，调用导出工具类导出保险信息列表
     * 与预计投保名单管理导出API共用，根据模块名称判断是正式投保名单还是预计投保名单
     * 若为正式投保名单导出，模块名称为insurance
     * 若为预计投保名单导出，模块名称为insurancej
     * @param header
     * @param filterJSON 查询条件
     * @param mode 模块名称
     * @return
     * @throws IOException
     */
    @RequestMapping(
            value = "/all",
            method = RequestMethod.GET,
            params = {"mode","filter"}
    )
    public Map<String, Object> exportAllInsurance(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "filter") String filterJSON,
            @RequestParam(value = "mode") String mode
            ) throws IOException {
        byte[] bytes = null;
        Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
        //导出条数受限，更改查询方法
//        String id[] = insuranceService.getAllInsuranceByFilter(filter,mode,header);
        String id[] = insuranceService.getAllInsuranceBySql(filter,mode,header);

        String tableName = "v_exp_insurance";
        bytes = exportService.exportByFilter(tableName, "0", id, "");
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        String fileName = tableName + ts.getTime() + ".xls"; // 组装附件名称和格式
        //上传至文件服务器
        String file = UploadFileServer.uploadFile(fileName, bytes);
        Map<String, Object> fileMap = new ObjectMapper().readValue(file, Map.class);
        //导出条数受限，更改批量更新导出状态方法
//        insuranceService.updateInsurancePresta(id);//导出后，根据传入的id数组进行批量更新导出状态
        if(id.length>0){
            insuranceService.updateInsurancePrestaBySql(id);
        }
        return fileMap;
    }

    /**
     * 导入保险信息
     * POST /insurance
     *
     * @return
     */
    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity importInsurance(@RequestParam(value = "filename") String filename,
                                          @RequestParam(value = "key") String key,
                                          HttpServletRequest request) {
//        System.out.println("request = " + request);
        try {
            System.out.println("filename = " + URLDecoder.decode(filename, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Calendar cale = Calendar.getInstance();
        cale.setTime(new Date());   // 当前年
        int year = cale.get(Calendar.YEAR);

        System.out.println("InsuranceController.importInsurance");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println("isMultipart = " + isMultipart);
        List<String> list1 = new ArrayList<String>();
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();

            File repository = (File) request.getSession().getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);

            try {
                List<FileItem> items = upload.parseRequest(request);
                System.out.println("items = " + items);
                list1 = importDao.check(items.get(0), year);
                MAP.put(key, list1);
                if (list1.size() > 0 && !"成功导入".equals(list1.get(0))) {
                    System.out.println("list1 = " + list1);

                    return new ResponseEntity<List<String>>(list1, HttpStatus.OK);
                }
                List<String> list = importDao.doImport(items.get(0), year);
                // return new ResponseEntity<List<String>>(list1, HttpStatus.OK);

                // Vector<Vector<String>> list = importDao.doExcelImport(items.get(0));
//                System.out.println("list = " + list.size());
//                System.out.println("list = " + list);
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<List<String>>(list1, HttpStatus.OK);
    }

/*    @RequestMapping(
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity options() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Access-Control-Allow-Origin", "*");
        httpHeaders.add("Allow", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        return new ResponseEntity(httpHeaders, HttpStatus.OK);
    }*/

    /**
     * 查询保险信息列表，查询预计保险信息列表
     * 与预计投保名单管理查询API共用，根据模块名称判断是正式投保名单还是预计投保名单
     * 若为正式投保名单查询，模块名称为insurance，查询条件设置保险种类字段insurSta为‘1’
     * 若为预计投保名单查询，模块名称为insurancej，查询条件设置保险种类字段insurSta为‘0’
     * @param header
     * @param mode 模块名称
     * @param fields 查询字段
     * @param page 第几页
     * @param size 每页记录数
     * @param filterJSON 查询条件
     * @return
     * @throws IOException
     */
    @Transactional
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "field", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getInsurances(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "field") String[] fields,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {

        try {
            Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
            Page<Insurance> insurancePage = insuranceService.getInsurancesPagingByFilter(filter, page, size, mode, header);
            Page<Map<String, Object>> mapPage = insurancePage.map(new InsuranceConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     * 统计保险列表状态（未导出，已导出，已反馈）
     * 与预计投保名单管理统计保险列表状态API共用，根据模块名称判断是正式投保名单还是预计投保名单。
     * @param header
     * @param mode 模块名称
     * @param filterJSON 查询条件
     * @return
     * @throws IOException
     */
    @RequestMapping(
            value = "/statusNum",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "filter"})
    public Map<String,Long> getInsurancesStateSum(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        Map<String, Long> result=new HashMap<String,Long >();
        try {
            Filter filter = new ObjectMapper().readValue(filterJSON, Filter.class);
            result = insuranceService.getInsurancesStateSum(header,filter,mode);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 新增学生时首先校验该学生是否已经存在于保险列表中
     * @param studentId 学生id
     * @return
     */
    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Map<String,String> verifyInsuranceStudent(@PathVariable(value = "studentId") String studentId) {
        try {
           Map<String,String> result = insuranceService.verifyInsuranceStudent(studentId);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
