package gov.gwssi.csc.scms.controller.sturegimport;

import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.dao.importExcle.ImportDao;
import gov.gwssi.csc.scms.dao.importExcle.ImportStuRegDAO;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * Created by LiZhiSheng on 2015/8/18.
 */
@RestController
@RequestMapping(value = "/sturegimport")
public class StuRegImportController {
    @Autowired
    private UserService userService;
    @Autowired
   private ImportDao importDao;
    @Autowired
    private ImportStuRegDAO importStuRegDao;
//public static Map<String,List> MAP = new HashMap<String, List>();
    //点击查询返回代码维护列表
    @RequestMapping(value = "/stureg", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getALLCode(@RequestParam(value = "begin") String begin, @RequestParam(value = "end") String end) {
        //按照分页（默认）要求，返回列表内容
        List proAndUnivList = null;
        if (begin == null || "null".equals(begin)) {
            begin = "";
        }
        if (end == null || "null".equals(end) || "undefined".equals(end)) {
            end = "";
        }
        proAndUnivList = importDao.getList(begin, end);
        //System.out.println("hehe="+MAP.get("111"));
        return proAndUnivList;
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
        String id = importStuRegDao.saveInitFile(fileName,userName);
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
                List<String> list1 = importStuRegDao.check(items.get(0));
                //MAP.put(key,list1);
                if (list1.size() > 0&&!"成功导入".equals(list1.get(0))) {
                    System.out.println("list1 = " + list1);
                    importStuRegDao.updateFile(id, 0, "0",list1);
                   //return new ResponseEntity<List<String>>(list1, HttpStatus.OK);
                }else{
                    List<String> list = importStuRegDao.doImport(items.get(0));
                    System.out.println("list = " + list.size());
                    System.out.println("list = " + list);
                    importStuRegDao.updateFile(id, list.size(), "1",list1);
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
