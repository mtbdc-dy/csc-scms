package gov.gwssi.csc.scms.controller.statistics;

import gov.gwssi.csc.scms.service.statistics.DimColumnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by WangZhenghua on 2015/5/15.
 * 动态查询统计字段表Restful接口
 */
@RestController
@RequestMapping("/statistics/dimcolumn")
public class DimColumnController {
    @Autowired
    private DimColumnService dimColumnService;

    // 获取资源-动态查询统计所有表清单
    @RequestMapping(value="{tableEn}",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getDimColumnJsonDataByTableEn(@PathVariable(value = "tableEn") String tableEn){
        String jsonData = "";
        if(tableEn != null && !tableEn.equals("")){
            jsonData = dimColumnService.getDimColumnJsonDataByTableEn(tableEn);
        }
        return jsonData;
    }
}
