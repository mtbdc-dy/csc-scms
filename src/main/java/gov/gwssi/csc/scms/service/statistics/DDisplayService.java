package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.domain.statistics.DDisplay;
import gov.gwssi.csc.scms.repository.statistics.DDisplayRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计显示样式Service层
 */
@Service("dDisplayService")
public class DDisplayService extends BaseService{

    @Autowired
    @Qualifier("dDisplayRepository")
    private DDisplayRepository dDisplayRepository;

    public List<DDisplay> findByConfigId(String configId){
        return dDisplayRepository.findByConfigId(configId);
    }

}
