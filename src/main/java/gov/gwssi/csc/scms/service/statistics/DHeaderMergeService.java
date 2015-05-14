package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.domain.statistics.DHeaderMerge;
import gov.gwssi.csc.scms.repository.statistics.DHeaderMergeRepository;
import gov.gwssi.csc.scms.repository.statistics.DHeaderRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计表头合并Service层
 */
@Service("dHeaderMergeService")
public class DHeaderMergeService extends BaseService{
    @Autowired
    @Qualifier("dHeaderMergeRepository")
    private DHeaderMergeRepository dHeaderMergeRepository;

    public List<DHeaderMerge> findByConfigId(String configId){
        return dHeaderMergeRepository.findByConfigId(configId);
    }
}
