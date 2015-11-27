package gov.gwssi.csc.scms.controller.codemaintenance;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.codemaintenance.*;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.query.CodeDetailResult;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.codemaintenance.*;
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
 * 代码维护控制器
 */
@RestController
@RequestMapping("/code")
public class CodeMainTenanceController
{
    @Autowired
    private CodeMainTenanceService codeMainTenanceService;
    @Autowired
    private UserService            userService;

    //点击查询返回代码维护列表
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getALLCode(@RequestParam(value = "chinaName") String chinaName, @RequestParam(value = "tableName") String tableName)
    {
        //按照分页（默认）要求，返回列表内容
        List allCodeList = null;
        chinaName = DecodeName(chinaName);
        tableName = DecodeName(tableName);
        allCodeList = codeMainTenanceService.findAllCode(tableName, chinaName);

        return allCodeList;
    }

    private String DecodeName(String name)
    {
        if (name == null || "null".equals(name))
        {
            name = "";
        } else
        {
            try
            {
                name = URLDecoder.decode(name, "utf-8");
            } catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return name;
    }

    //获取父节点
    @RequestMapping(value = "/parentId", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getParentCode(@RequestParam(value = "type") String type)
    {
        //按照分页（默认）要求，返回列表内容
        List detailCodeList = null;

        detailCodeList = codeMainTenanceService.getParentCode(type);

        return detailCodeList;
    }

    //返回代码详细列表
    @RequestMapping(value = "/detail", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getDetailCode(@RequestParam(value = "seq") String seq)
    {
        //按照分页（默认）要求，返回列表内容
        List detailCodeList = null;

        detailCodeList = codeMainTenanceService.findDetailCode(seq, "", "");

        return detailCodeList;
    }

    // 保存修改的代码
    @RequestMapping(
            value = "/save",
            method = RequestMethod.PUT,
            headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public CodeDetailResult saveDetailCode(@RequestBody String codeJson)
    {
        //按照分页（默认）要求，返回列表内容
        CodeDetailResult codeDetailResult;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody     jBody  = new ObjectMapper().readValue(codeJson, JsonBody.class);
            codeDetailResult = mapper.readValue(jBody.getValue(), CodeDetailResult.class);
            codeDetailResult = codeMainTenanceService.saveCode(codeDetailResult);
            codeDetailResult = codeMainTenanceService.selectCode(codeDetailResult);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return codeDetailResult;
    }

    @RequestMapping(
            value = "/sort",
            method = RequestMethod.PUT,
            headers = "Accept=application/json; charset=utf-8;")
    public void changeSortValue(
            @RequestBody String body
    ) throws IOException
    {
        ObjectMapper mapper  = new ObjectMapper();
        Map          bodyMap = mapper.readValue(body, Map.class);

        System.out.println("body = [" + bodyMap + "]");
        String id        = (String) bodyMap.get("id");
        String direction = String.valueOf(bodyMap.get("direction"));
        Long   step      = Long.parseLong(bodyMap.get("step").toString());

        codeMainTenanceService.changeUniversitySortValue(id, direction, step);
    }

    // 保存新增的代码
    @RequestMapping(value = "/new", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public CodeDetailResult saveNewDetailCode(@RequestParam(value = "type") String type, @RequestParam(value = "flag") String flag,
            @RequestParam(value = "dim") String dim, @RequestBody String codeJson)
    {
        //按照分页（默认）要求，返回列表内容
        String           detailCodeList   = null;
        CodeDetailResult codeDetailResult = null;
        try
        {
            ObjectMapper mapper = new ObjectMapper();
            // mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            boolean  rv    = true;
            JsonBody jbosy = new ObjectMapper().readValue(codeJson, JsonBody.class);
            codeDetailResult = mapper.readValue(jbosy.getValue(), CodeDetailResult.class);
            codeDetailResult.setTABLEEN(dim);

            String zdz = codeMainTenanceService.saveNewCode(codeDetailResult, type);
            codeDetailResult = codeMainTenanceService.selectCode(codeDetailResult, zdz);
        } catch (Exception e)
        {
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
            params = {"page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getNewStuTimeSet(
            @RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException
    {
        try
        {
            Filter                    filter               = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);
            User                      user                 = userService.getUserByJWT(header);
            Page<CodeMainTenance>     codeMainTenancesPage = codeMainTenanceService.getCodeMainTenancesPagingByFilter(filter, page, size, user);
            Page<Map<String, Object>> mapPage              = codeMainTenancesPage.map(new CodeMainTenanceConverter());
            return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //分页查询
    @RequestMapping(
            value = "/region",
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"page", "size", "filter"})
    public ResponseEntity<Page<Map<String, Object>>> getRegionFirst(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "type") String type,
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "size") Integer size,
            @RequestParam(value = "filter") String filterJSON) throws IOException
    {

        try
        {
            Filter filter = new ObjectMapper().readValue(URLDecoder.decode(filterJSON, "utf-8"), Filter.class);

            if ("dim_region".equals(name) && "A".equals(type))
            {
                Page<CodemaintanenceRegionFirst> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceRegionFirstsPagingByFilter(page, size);
                Page<Map<String, Object>>        mapPage              = codeMainTenancesPage.map(new CodeMainTenanceRegion1Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_region".equals(name) && "E".equals(type))
            {
                Page<CodemaintanenceRegionSecond> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceRegionSecondsPagingByFilter(page, size);
                Page<Map<String, Object>>         mapPage              = codeMainTenancesPage.map(new CodeMainTenanceRegion2Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_region".equals(name) && "P".equals(type))
            {
                Page<CodemaintanenceRegionThird> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceRegionThirdsPagingByFilter(page, size);
                Page<Map<String, Object>>        mapPage              = codeMainTenancesPage.map(new CodeMainTenanceRegion3Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_univ".equals(name) && "S".equals(type))
            {
                Page<CodemaintanenceUniv> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceUnivsPagingByFilter(page, size);
                Page<Map<String, Object>> mapPage              = codeMainTenancesPage.map(new CodeMainTenanceUnivConverter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_project".equals(name) && "BC".equals(type))
            {
                Page<CodemaintanenceProjectFirst> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceProjectFirstsPagingByFilter(page, size);
                Page<Map<String, Object>>         mapPage              = codeMainTenancesPage.map(new CodeMainTenanceProject1Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_project".equals(name) && "T".equals(type))
            {
                Page<CodemaintanenceProjectSecond> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceProjectSecondsPagingByFilter(page, size);
                Page<Map<String, Object>>          mapPage              = codeMainTenancesPage.map(new CodeMainTenanceProject2Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_project".equals(name) && "U".equals(type))
            {
                Page<CodemaintanenceProjectThird> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceProjectThirdsPagingByFilter(page, size);
                Page<Map<String, Object>>         mapPage              = codeMainTenancesPage.map(new CodeMainTenanceProject3Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_anml".equals(name) && "AB".equals(type))
            {
                Page<CodemaintanenceAnmlFirst> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceAnmlFirstsPagingByFilter(page, size);
                Page<Map<String, Object>>      mapPage              = codeMainTenancesPage.map(new CodeMainTenanceAnml1Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_anml".equals(name) && "AC".equals(type))
            {
                Page<CodemaintanenceAnmlSecond> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceAnmlSecondsPagingByFilter(page, size);
                Page<Map<String, Object>>       mapPage              = codeMainTenancesPage.map(new CodeMainTenanceAnml2Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_subject".equals(name) && "V".equals(type))
            {
                Page<CodemaintanenceSubjectFirst> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceSubjectFirstsPagingByFilter(page, size);
                Page<Map<String, Object>>         mapPage              = codeMainTenancesPage.map(new CodeMainTenanceSubject1Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_subject".equals(name) && "W".equals(type))
            {
                Page<CodemaintanenceSubjectSecond> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceSubjectSecondsPagingByFilter(page, size);
                Page<Map<String, Object>>          mapPage              = codeMainTenancesPage.map(new CodeMainTenanceSubject2Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_subject".equals(name) && "X".equals(type))
            {
                Page<CodemaintanenceSubjectThird> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceSubjectThirdsPagingByFilter(page, size);
                Page<Map<String, Object>>         mapPage              = codeMainTenancesPage.map(new CodeMainTenanceSubject3Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_dept".equals(name) && "BI".equals(type))
            {
                Page<CodemaintanenceDeptFirst> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceDeptFirstsPagingByFilter(page, size);
                Page<Map<String, Object>>      mapPage              = codeMainTenancesPage.map(new CodeMainTenanceDept1Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else if ("dim_dept".equals(name) && "BJ".equals(type))
            {
                Page<CodemaintanenceDeptSecond> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceDeptSecondsPagingByFilter(page, size);
                Page<Map<String, Object>>       mapPage              = codeMainTenancesPage.map(new CodeMainTenanceDept2Converter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            } else
            {
                Page<CodemaintanenceTranslate> codeMainTenancesPage = codeMainTenanceService.getCodemaintanenceTranslatesPagingByFilter(page, size, type);
                Page<Map<String, Object>>      mapPage              = codeMainTenancesPage.map(new CodeMainTenanceTranslateConverter());
                return new ResponseEntity<Page<Map<String, Object>>>(mapPage, HttpStatus.OK);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
