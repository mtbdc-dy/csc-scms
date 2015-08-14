package gov.gwssi.csc.scms.controller.statistics;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.statistics.*;
import gov.gwssi.csc.scms.service.statistics.DStatisticsHandlerService;
import gov.gwssi.csc.scms.service.statistics.DimTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/28.
 * 动态统计查询RESTful接口
 */

@RestController
@RequestMapping("/statistics")
public class DStatisticsController {

    @Autowired
    private DimTableService dimTableService;

    @Autowired
    private DStatisticsHandlerService dStatisticsHandlerService;

    /**
     * 动态查询统计中初始化的table列表以及对应表下的column列表
     */
    @RequestMapping(value="tables",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
    public List<TablesJson> getTables(){
        List<TablesJson> tablesJson = null;
        try{
            tablesJson = dimTableService.getTables();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tablesJson;
    }

    /**
     * 接收动态查询统计配置Json
     */
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public ReturnStatistics putStatistics(@RequestBody String statisticsJson){
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonBody jsonBody = new ObjectMapper().readValue(statisticsJson, JsonBody.class);

            DConfig dConfig = new ObjectMapper().readValue(jsonBody.getDConfig(),DConfig.class);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, DTableList.class);
            List<DTableList> dTableList = mapper.readValue(jsonBody.getDTableList(), javaType);

            javaType = mapper.getTypeFactory().constructParametricType(List.class, DCondition.class);
            List<DCondition> dConditions = mapper.readValue(jsonBody.getDConditions(), javaType);

            javaType = mapper.getTypeFactory().constructParametricType(List.class, DDisplay.class);
            List<DDisplay> dDisplays = mapper.readValue(jsonBody.getDDisplays(), javaType);

            // 将读取的dConfig dTableList dConditions dDisplays 依次入库,并返回ReturnStatistics
            return dStatisticsHandlerService.save(dConfig,dTableList,dConditions,dDisplays);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
