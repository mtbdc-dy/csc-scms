package gov.gwssi.csc.scms.service.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.DCondition;
import gov.gwssi.csc.scms.repository.dynamicReport.DConditionRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计条件配置Service层
 */
@Service("dConditonService")
public class DConditionService extends BaseService{
    @Autowired
    @Qualifier("dConditionRepository")
    private DConditionRepository dConditonRepository;

    public List<DCondition> findByConfigId(String configId){
        return dConditonRepository.findByConfigId(configId);
    }

}
