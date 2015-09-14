package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Column;
import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.dynamicReport.Table;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ReportConfigurationRepository;
import gov.gwssi.csc.scms.repository.dynamicReport.TableRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置Service层
 */
@Service("dConfigService")
public class DynamicReportService extends DynamicReportSpecs {
    @Qualifier("reportConfigurationRepository")
    @Autowired
    private ReportConfigurationRepository reportConfigurationRepository;

    @Qualifier("tableRepository")
    @Autowired
    private TableRepository tableRepository;

    public Page<ReportConfiguration> getAllConfigurationsByFilter(Filter filter) {

        return reportConfigurationRepository.findAll(filterIsLike(filter), new PageRequest(0, 20));
    }

    @Transactional
    public void getColumns() {
        for (Table table : tableRepository.findAll()) {
            System.out.println(table.getColumns().size());
        }
    }

    @Transactional
    public Set<Table> getTables() {
        Set<Table> tables = new HashSet<Table>((ArrayList<Table>)tableRepository.findAll());
        for (Table table : tables) {
//            坑爹不能在session里面设置设置引用为null
//            setNullByField(table.getColumns(), "table", Column.class);
        }
        return tables;
    }

    @Transactional
    public Table getTable(String id){
        Table table = tableRepository.findOne(id);
        // 用于执行 Columns 的查询
        table.getColumns().size();
        return table;
    }

//    public ReportConfiguration findById(String id){
//        return reportConfigurationRepository.findOne(id);
//    }

//    public List<ReportConfiguration> findDConfigList(){
//        List<ReportConfiguration> list = new ArrayList<ReportConfiguration>();
//        Iterable<ReportConfiguration> iterable = reportConfigurationRepository.findAll();
//        if(iterable != null){
//            for (ReportConfiguration reportConfiguration : iterable){
//                list.add(reportConfiguration);
//            }
//        }
//        return list;
//    }


}
