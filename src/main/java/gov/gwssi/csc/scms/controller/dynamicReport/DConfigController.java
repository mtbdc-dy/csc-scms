package gov.gwssi.csc.scms.controller.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.service.dynamicReport.DynamicReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/16.
 * 动态查询统计配置表REST接口
 */
@RestController
@RequestMapping("/statistics")
public class DConfigController {
//    @Autowired
//    private DynamicReportService dynamicReportService;

//    @RequestMapping(value="dconfiglist",method = RequestMethod.GET, headers = "Accept=application/json;charset=utf-8")
//    public List<ReportConfiguration> getDConfigList(HttpServletResponse httpResponse){
//        httpResponse.setHeader("Access-Control-Allow-Origin", "*");
//        return dynamicReportService.findDConfigList();
//    }

}
