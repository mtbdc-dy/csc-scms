package gov.gwssi.csc.scms.controller.dynamicReport;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.dynamicReport.Column;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.OriginalConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Report;
import gov.gwssi.csc.scms.domain.dynamicReport.Report.Row;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Table;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ConfigurationRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.UploadFileServer;
import gov.gwssi.csc.scms.service.dynamicReport.DynamicReportService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.awt.print.Pageable;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by WangZhenghua on 2015/5/28.
 * 动态统计查询RESTful接口
 */

@RestController
@RequestMapping("/dynamic")
public class DynamicReportController extends BaseService {

    @Autowired
    private DynamicReportService service;


    @RequestMapping(
            value = "/configurations",
            method = RequestMethod.GET,
            headers = {"Accept=application/json;charset=utf-8"},
            params = {"filter","page","size"}
    )
    public ResponseEntity<Page<Configuration>> getConfigurations(
            @RequestHeader(value = "Authorization") String jwt,
            @RequestParam(value = "filter") String filterJSON,
            @RequestParam(value = "page") Integer pageNo,
            @RequestParam(value = "size") Integer pageSize) throws UnsupportedEncodingException {
        Map<String, Object> user = JWTUtil.decode(jwt);
        assert user != null;
        String userName = String.valueOf(user.get("fullName"));
        String content = URLDecoder.decode(filterJSON, "UTF8");
        Class<Filter> valueType = Filter.class;

        Page<Configuration> configurations = null;
        try {
            Filter filter = new ObjectMapper().readValue(content, valueType);
            configurations = service.getAllConfigurationsByFilterAndUserName(filter, userName,pageNo,pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<Page<Configuration>>(configurations, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/configurations",
            method = RequestMethod.POST,
            headers = "Accept=application/json;charset=utf-8"
    )
    public Configuration createConfigurations(@RequestBody String configJSON) throws Exception {
        System.out.println("DynamicReportController.createConfigurations");
        System.out.println("configJSON = [" + configJSON + "]");
        Configuration configuration;
        try {
            configuration = new ObjectMapper().readValue(configJSON, Configuration.class);
            configuration = service.createConfig(configuration);
        } catch (IOException e) {
            e.printStackTrace();
            throw new Error("系统繁忙, 请稍等片刻后, 重新点击'完成'.");
        }
        return configuration;
    }

    @RequestMapping(
            value = "/configurations/{id}",
            method = RequestMethod.PUT,
            headers = "Accept=application/json;charset=utf-8"
    )
    public Configuration updateConfigurations(@PathVariable(value = "id") String id, @RequestBody String configJSON) throws Exception {
        Configuration configuration;
        try {
            configuration = new ObjectMapper().readValue(configJSON, Configuration.class);
            configuration = service.updateConfig(configuration, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return configuration;
    }

    @RequestMapping(
            value = "/configurations/{id}",
            method = RequestMethod.DELETE,
            headers = "Accept=application/json;charset=utf-8"
    )
    public void deleteConfiguration(@PathVariable(value = "id") String id) {
        try {
            service.deleteConfigurations(id);
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
    public String getReport(@PathVariable(value = "id") String id) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Report report = service.getReport(id);
        return mapper.writeValueAsString(report);
    }

    @RequestMapping(
            value = "/configurations/{id}/report/header",
            method = RequestMethod.GET,
            headers = "Accept=application/json;charset=utf-8"
    )
    public ResponseEntity<List<Row>> getReportHeader(@PathVariable(value = "id") String id) {
        return new ResponseEntity<List<Row>>(service.getReportHeader(id), HttpStatus.OK);
    }

    @RequestMapping(
            value = "/configurations/{id}/report/body",
            params = {"page", "size"},
            method = RequestMethod.GET,
            headers = "Accept=application/json;charset=utf-8"
    )
    public ResponseEntity<Page<Row>> getReportBody(
            @PathVariable(value = "id") String id,
            @RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "size") Integer pageSize) {
        List<Row> content = service.getReportBody(id);
        Integer fromIndex = pageNumber * pageSize;
        Integer toIndex = fromIndex + pageSize;
        toIndex = toIndex < content.size() ? toIndex : content.size();
        List<Row> subContent = content.subList(fromIndex, toIndex);
        Page<Row> page = new PageImpl<Row>(subContent, new PageRequest(pageNumber, pageSize), content.size());
        return new ResponseEntity<Page<Row>>(page, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/configurations/{id}/report/excel",
            //headers = "Accept=application/octet-stream",
            method = RequestMethod.GET
    )
    public Map<String, Object> exportReport(
            @PathVariable(value = "id") String id) {

        byte[] bytes = null;
        Map<String, Object> fileMap=null;

        try {
            bytes = service.export(id);
            //HttpHeaders httpHeaders = new HttpHeaders();
            //httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            //httpHeaders.setContentDispositionFormData("attachment", id + ".xls");
            //return new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.CREATED);
            //上传至文件服务器
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            String fileName = ts.getTime() + ".xls"; // 组装附件名称和格式
            String file = UploadFileServer.uploadFile(fileName, bytes);
            fileMap = new ObjectMapper().readValue(file, Map.class);
            return fileMap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileMap;
    }

    @RequestMapping(
            value = {"/tables"},
            method = RequestMethod.GET,
            headers = {"Accept=application/json;charset=utf-8"}
    )
    public ResponseEntity<Collection<Table>> getTables() {
        Collection<Table> tables = service.getTables();
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
        Table table = service.getTable(id);
        setNullByField(table.getColumns(), "table", Column.class);
        return new ResponseEntity<Table>(table, HttpStatus.OK);
    }
}
