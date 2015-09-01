package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.repository.dynamicReport.ReportConfigurationRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置Service层
 */
@Service("dConfigService")
public class DynamicReportService extends DynamicReportSpecs{
    @Qualifier("reportConfigurationRepository")
    @Autowired
    private ReportConfigurationRepository reportConfigurationRepository;

    public Page<ReportConfiguration> getAllConfigurationsByFilter (Filter filter){

        return reportConfigurationRepository.findAll(filterIsLike(filter), new PageRequest(0, 20));
    }

    public ReportConfiguration findById(String id){
        return reportConfigurationRepository.findOne(id);
    }

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
