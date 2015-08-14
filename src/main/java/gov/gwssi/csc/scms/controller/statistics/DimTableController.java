package gov.gwssi.csc.scms.controller.statistics;

import gov.gwssi.csc.scms.service.statistics.DimTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Wang Zhenghua on 2015/5/15.
 * 动态查询统计所有表清单Restful接口
 */
@RestController
@RequestMapping("/statistics")
public class DimTableController {
    @Autowired
    private DimTableService dimTableService;

    // 获取资源-动态查询统计所有表清单
    @RequestMapping(value="dimtable",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public String getAllDimTableJsonData() {
        String dimTableJsonData;
        try{
            dimTableJsonData = dimTableService.getAllDimTableJsonData();
        }catch (Exception e){
            e.printStackTrace();
            return "[]";
        }
        return dimTableJsonData;
    }

}
