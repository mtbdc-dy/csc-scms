package gov.gwssi.csc.scms.controller.codemaintenance;

import gov.gwssi.csc.scms.service.codemaintenance.CodeMainTenanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by lzs on 2015/7/13.
 *   代码维护控制器
 */
@RestController
@RequestMapping("/code")
public class CodeMainTenanceController {
    @Autowired
    private CodeMainTenanceService codeMainTenanceService;
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
        allCodeList = codeMainTenanceService.findAllCode(tableName,chinaName);

        return allCodeList;
    }
}
