package gov.gwssi.csc.scms.controller.dynamicReport;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.service.dynamicReport.DStatisticsHandlerService;
import gov.gwssi.csc.scms.service.dynamicReport.DimTableService;
import gov.gwssi.csc.scms.service.dynamicReport.DynamicReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/28.
 * 动态统计查询RESTful接口
 */

@RestController
@RequestMapping("/dynamic")
public class DynamicReportController {

    @Autowired
    private DynamicReportService dynamicReportService;


    @RequestMapping(
            value = "/configurations",
            method = RequestMethod.GET,
            headers = {"Accept=application/json;charset=utf-8"},
            params = {"filter"}
    )
    public ResponseEntity<Page<ReportConfiguration>> getConfigurations(
            @RequestParam(value = "filter") String filterJSON) throws UnsupportedEncodingException {
        String content = URLDecoder.decode(filterJSON, "UTF8");
        Class<Filter> valueType = Filter.class;

        Page<ReportConfiguration> reportConfigurations = null;
        try {
            Filter filter = new ObjectMapper().readValue(content, valueType);
            reportConfigurations = dynamicReportService.getAllConfigurationsByFilter(filter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Page<ReportConfiguration>>(reportConfigurations, HttpStatus.OK);
    }

//    /**
//     * 动态查询统计中初始化的table列表以及对应表下的column列表
//     */
//    @RequestMapping(value="tables",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
//    public List<TablesJson> getTables(){
//        List<TablesJson> tablesJson = null;
//        try{
//            tablesJson = dimTableService.getTables();
//        } catch (Exception e){
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//        return tablesJson;
//    }
//
//    /**
//     * 接收动态查询统计配置Json
//     */
//    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
//    public ReturnStatistics putStatistics(@RequestBody String statisticsJson){
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            JsonBody jsonBody = new ObjectMapper().readValue(statisticsJson, JsonBody.class);
//
//            ReportConfiguration dConfig = new ObjectMapper().readValue(jsonBody.getDConfig(),ReportConfiguration.class);
//
//            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, DTableList.class);
//            List<DTableList> dTableList = mapper.readValue(jsonBody.getDTableList(), javaType);
//
//            javaType = mapper.getTypeFactory().constructParametricType(List.class, DCondition.class);
//            List<DCondition> dConditions = mapper.readValue(jsonBody.getDConditions(), javaType);
//
//            javaType = mapper.getTypeFactory().constructParametricType(List.class, DDisplay.class);
//            List<DDisplay> dDisplays = mapper.readValue(jsonBody.getDDisplays(), javaType);
//
//            // 将读取的dConfig dTableList dConditions dDisplays 依次入库,并返回ReturnStatistics
//            return dStatisticsHandlerService.save(dConfig,dTableList,dConditions,dDisplays);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

}
