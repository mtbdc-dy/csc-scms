package gov.gwssi.csc.scms.controller.statistics;

import gov.gwssi.csc.scms.domain.statistics.TablesJson;
import gov.gwssi.csc.scms.service.statistics.DimColumnService;
import gov.gwssi.csc.scms.service.statistics.DimTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/28.
 * 动态统计查询RESTful接口
 */

@RestController
@RequestMapping("/api/codeTable")
public class DStatisticsController {

    @Autowired
    private DimTableService dimTableService;

    @RequestMapping(value="tables",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TablesJson> getTables(){
        List<TablesJson> tablesJsons = null;
        try{
            tablesJsons = dimTableService.getTables();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tablesJsons;
    }

}
