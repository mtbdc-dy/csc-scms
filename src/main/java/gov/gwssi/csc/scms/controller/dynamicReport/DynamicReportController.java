package gov.gwssi.csc.scms.controller.dynamicReport;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.dynamicReport.Column;
import gov.gwssi.csc.scms.domain.dynamicReport.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Table;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.dynamicReport.DynamicReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Collection;

/**
 * Created by WangZhenghua on 2015/5/28.
 * 动态统计查询RESTful接口
 */

@RestController
@RequestMapping("/dynamic")
public class DynamicReportController extends BaseService {

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

    @RequestMapping(
            value = "/configurations",
            method = {RequestMethod.PUT, RequestMethod.POST},
            headers = "Accept=application/json;charset=utf-8"
    )
    public String createConfigurations(@RequestBody String configJSON) throws IOException {
        System.out.println("DynamicReportController.createConfigurations");
        System.out.println("configJSON = [" + configJSON + "]");
        try {
            OriginalConfiguration configuration = new ObjectMapper().readValue(configJSON, OriginalConfiguration.class);
            System.out.println("configuration = " + configuration);
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        return null;
    }

    @RequestMapping(
            value = "/configurations/{id}",
            method = RequestMethod.DELETE,
            headers = "Accept=application/json;charset=utf-8"
    )
    public void deleteConfiguration(@PathVariable(value = "id") String id) {
        try {
            dynamicReportService.deleteConfigurations(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(
            value = "/configurations/{id}/report",
            method = RequestMethod.GET,
            headers = "Accept=application/json;charset=utf-8"
    )
    public String getReport(@PathVariable(value = "id") String id){

        return null;
    }

    @RequestMapping(
            value = {"/tables"},
            method = RequestMethod.GET,
            headers = {"Accept=application/json;charset=utf-8"}
    )
    public ResponseEntity<Collection<Table>> getTables() {
        Collection<Table> tables = dynamicReportService.getTables();
        for (Table table : tables) {
            table.setColumns(null);
        }
        return new ResponseEntity<Collection<Table>>(tables, HttpStatus.OK);
    }

    @RequestMapping(
            value = {"/tables/{id}"},
            method = RequestMethod.GET,
            headers = {"Accept=application/json;charset=utf-8"}
    )
    public ResponseEntity<Table> getTable(@PathVariable(value = "id") String id) {
        Table table = dynamicReportService.getTable(id);
        setNullByField(table.getColumns(), "table", Column.class);
        return new ResponseEntity<Table>(table, HttpStatus.OK);
    }
}
