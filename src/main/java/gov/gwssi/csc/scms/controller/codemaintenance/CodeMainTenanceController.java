package gov.gwssi.csc.scms.controller.codemaintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.codemaintenance.CodeMainTenanceConverter;
import gov.gwssi.csc.scms.service.codemaintenance.CodeMainTenanceRegion1Converter;
import gov.gwssi.csc.scms.service.codemaintenance.CodeMainTenanceService;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

/**
 * Created by lzs on 2015/7/13.
 *   代码维护控制器
 */
@RestController
@RequestMapping("/code")
public class CodeMainTenanceController {
    @Autowired
    private CodeMainTenanceService codeMainTenanceService;
    @Autowired
    private UserService userService;
    //点击查询返回代码维护列表
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getALLCode(@RequestParam(value = "chinaName") String chinaName,@RequestParam(value = "tableName") String tableName) {
        //按照分页（默认）要求，返回列表内容
        List allCodeList = null;
        if(chinaName ==null||"null".equals(chinaName)){
            chinaName = "";
        }else{
            try {
                chinaName = URLDecoder.decode(chinaName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if(tableName ==null||"null".equals(tableName)){
            tableName = "";
        }else{
            try {
                tableName = URLDecoder.decode(tableName, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        allCodeList = codeMainTenanceService.findAllCode(tableName, chinaName);

        return allCodeList;
    }
    //获取父节点
    @RequestMapping(value = "/parentId",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getParentCode(@RequestParam(value = "type") String type) {
        //按照分页（默认）要求，返回列表内容
        List detailCodeList = null;

        detailCodeList = codeMainTenanceService.getParentCode(type);

        return detailCodeList;
    }
    //返回代码详细列表
    @RequestMapping(value = "/detail",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getDetailCode(@RequestParam(value = "seq") String seq) {
        //按照分页（默认）要求，返回列表内容
        List detailCodeList = null;

        detailCodeList = codeMainTenanceService.findDetailCode(seq, "", "");

        return detailCodeList;
    }
    // 保存修改的代码
        @RequestMapping(value = "/save",method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
        public CodeDetailResult saveDetailCode(@RequestBody String codeJson) {
            //按照分页（默认）要求，返回列表内容
            String detailCodeList = null;
            CodeDetailResult codeDetailResult = null;
            try {


            ObjectMapper mapper = new ObjectMapper();
               // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            boolean rv = true;
            JsonBody jbosy = new ObjectMapper().readValue(codeJson, JsonBody.class);
           codeDetailResult = mapper.readValue(jbosy.getValue(), CodeDetailResult.class);
                codeDetailResult=  codeMainTenanceService.saveCode(codeDetailResult);
                codeDetailResult = codeMainTenanceService.selectCode(codeDetailResult);
            }catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        return codeDetailResult;
    }
    // 保存新增的代码
    @RequestMapping(value = "/new",method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public CodeDetailResult saveNewDetailCode(@RequestParam(value = "type") String type,@RequestParam(value = "flag") String flag,
                                              @RequestParam(value = "dim") String dim,@RequestBody String codeJson) {
        //按照分页（默认）要求，返回列表内容
        String detailCodeList = null;
        CodeDetailResult codeDetailResult = null;
        try {


            ObjectMapper mapper = new ObjectMapper();
            // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            boolean rv = true;
            JsonBody jbosy = new ObjectMapper().readValue(codeJson, JsonBody.class);
            codeDetailResult = mapper.readValue(jbosy.getValue(), CodeDetailResult.class);
            codeDetailResult.setTABLEEN(dim);

            String zdz=  codeMainTenanceService.saveNewCode(codeDetailResult,type);
            codeDetailResult = codeMainTenanceService.selectCode(codeDetailResult,zdz);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return codeDetailResult;
    }
    //分页查询
    @RequestMapping(
            value = "/newstu",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = { "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getNewStuTimeSet(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User user = userService.getUserByJWT(header);
            Page<CodeMainTenance> codeMainTenancesPage = codeMainTenanceService.getCodeMainTenancesPagingByFilter(filter, page, size,  user);
            Page<Map<String, Object>> mapPage = codeMainTenancesPage.map(new CodeMainTenanceConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //分页查询
    @RequestMapping(
            value = "/region",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = { "page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getRegionFirst(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "flag") String flag,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException {
        try {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);

            Page<CodemaintanenceRegionFirst> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceRegionFirstsPagingByFilter(page, size);
            Page<Map<String, Object>> mapPage = codeMainTenancesPage.map(new CodeMainTenanceRegion1Converter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
