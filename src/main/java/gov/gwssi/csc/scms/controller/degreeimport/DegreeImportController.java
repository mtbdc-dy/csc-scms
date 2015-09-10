package gov.gwssi.csc.scms.controller.degreeimport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.dao.degreeimportexcle.DegreeImportDao;
import gov.gwssi.csc.scms.dao.importExcle.ImportDao;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.importlog.ImportLog;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.importlog.ImportLogConverter;
import gov.gwssi.csc.scms.service.importlog.ImportLogService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by LiZhiSheng on 2015/8/18.
 */
@RestController
@RequestMapping(value = "/degreeimport")
public class DegreeImportController {
    @Autowired
    private UserService userService;
    @Autowired
    private DegreeImportDao importDao;
    @Autowired
    private ImportLogService importLogService;
//public static Map<String,List> MAP = new HashMap<String, List>();
    //点击查询返回代码维护列表
//    @RequestMapping(value = "/degree", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
//    public List getALLCode(@RequestParam(value = "begin") String begin, @RequestParam(value = "end") String end) {
//        //按照分页（默认）要求，返回列表内容
//        List proAndUnivList = null;
//        if (begin == null || "null".equals(begin)) {
//            begin = "";
//        }
//        if (end == null || "null".equals(end) || "undefined".equals(end)) {
//            end = "";
//        }
//        proAndUnivList = importDao.getList(begin, end);
//        //System.out.println("hehe="+MAP.get("111"));
//        return proAndUnivList;
//    }
    //分页查询
    @RequestMapping(
            value = "/degree",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"mode", "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getNewStuTimeSet(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "mode") String mode,

            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User user = userService.getUserByJWT(header);
            Page<ImportLog> importLogsPage = importLogService.getImportLogsPagingByFilter(filter, page, size, mode, user,"2");
            Page<Map<String, Object>> mapPage = importLogsPage.map(new ImportLogConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //点击查询返回代码维护列表
    @RequestMapping(value = "/getkey", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getValue(@RequestParam(value = "key") String key) {
        //按照分页（默认）要求，返回列表内容
        List proAndUnivList = null;
//        if (key == null || "null".equals(key)) {
//
//            return new ArrayList();
//        }else{
//            //System.out.println("hehe="+MAP.get(key));
//           // return MAP.get(key);
//        }
        return null;



    }
    //保存数据到数据库
    @RequestMapping(
            method = RequestMethod.POST,
            headers = "Accept=application/json; charset=utf-8"
    )
    public void importInsurance(@RequestParam(value = "filename") String filename,
                                                        @RequestParam(value = "key") String key,
                                                        HttpServletRequest request,
                                                        @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header
    ) throws Exception {
//        System.out.println("request = " + request);
        String fileName = "";
        try {
            System.out.println("filename = " + URLDecoder.decode(filename, "utf-8"));
            fileName = URLDecoder.decode(filename, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        User user = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        } catch (NoSuchUserException e) {
            e.printStackTrace();
        }
        String userName = user.getFullName();
        System.out.println("InsuranceController.importInsurance");
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        System.out.println("isMultipart = " + isMultipart);
        String id = importDao.saveInitFile(fileName,userName);
        if (isMultipart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();

            File repository = (File) request.getSession().getServletContext().getAttribute("javax.servlet.context.tempdir");
            factory.setRepository(repository);

            ServletFileUpload upload = new ServletFileUpload(factory);
//            if (1 == 1) {
//                System.out.println("111");
//                throw new IOException();
//            }

            try {
                List<FileItem> items = upload.parseRequest(request);
                System.out.println("items = " + items);
                List<String> list1 = importDao.check(items.get(0));
                //MAP.put(key,list1);
                if (list1.size() > 0&&!"成功导入".equals(list1.get(0))) {
                    System.out.println("list1 = " + list1);
                    importDao.updateFile(id, 0, "0",list1);
                   //return new ResponseEntity<List<String>>(list1, HttpStatus.OK);
                }else{
                    Vector<Vector<String>> list = importDao.doExcelImport(items.get(0));
                    System.out.println("list = " + list.size());
                    System.out.println("list = " + list);
                    importDao.updateFile(id, list.size() - 2, "1",list1);
                }

               // importDao.saveOpt(fileName, list.size() - 2, userName);
//                if (list1.size() > 0&&!"成功导入".equals(list1.get(0))) {
//                    importDao.updateFile(id, 0, "0");
//                }else{
//
//                }
            } catch (FileUploadException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
//        return new ResponseEntity<List<String>>(new ArrayList<String>() {
//        }, HttpStatus.NOT_ACCEPTABLE);
    }
}
