package gov.gwssi.csc.scms.controller.monitor;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.monitor.Monitor;
import gov.gwssi.csc.scms.domain.monitor.MonitorDay;
import gov.gwssi.csc.scms.domain.monitor.MonitorMonth;
import gov.gwssi.csc.scms.service.monitor.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/monitor")
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    //动态监控
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Monitor addMonitorUsers(@RequestBody String bodyJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonBody jbosy = new ObjectMapper().readValue(bodyJson, JsonBody.class);

            Monitor monitor= mapper.readValue(jbosy.getValue(), Monitor.class);
            monitor = monitorService.monitorUsersCount(monitor);
            return monitor;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/hour", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<Monitor> queryMonitors(@RequestParam("beginTime") String beginTime,
                                       @RequestParam("endTime") String endTime) {
        try {
            return monitorService.getMonitors(beginTime,endTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/day", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<MonitorDay> queryDayMonitors(@RequestParam("beginTime") String beginTime,
                                       @RequestParam("endTime") String endTime) {
        try {
            return monitorService.getDayMonitors(beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/month", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<MonitorMonth> queryMonthMonitors(@RequestParam("beginTime") String beginTime,
                                             @RequestParam("endTime") String endTime) {
        try {
            return monitorService.getMonthMonitors(beginTime, endTime);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


}
