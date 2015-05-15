package gov.gwssi.csc.scms.controller.statistics;

import com.sun.deploy.net.HttpResponse;
import gov.gwssi.csc.scms.service.statistics.DimTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by WangZhenghua on 2015/5/15.
 * 动态查询统计所有表清单Restful接口
 */
@RestController
@RequestMapping("/statistics")
public class DimTableController {
    @Autowired
    private DimTableService dimTableService;

    private final String RETURN_BLANK_JSON = "[]";

    // 获取资源-动态查询统计所有表清单
    @RequestMapping(value="dimtable",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getAllDimTableJsonData(HttpServletResponse httpResponse){
        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//      httpResponse.setStatus(503);
        String dimTableJsonData = "";
        try{
            dimTableJsonData = dimTableService.getAllDimTableJsonData();
        }catch (Exception e){
            e.printStackTrace();
            return RETURN_BLANK_JSON;
        }
        return dimTableJsonData;
    }

}
