package gov.gwssi.csc.scms.service.statistics;

import gov.gwssi.csc.scms.domain.statistics.DConfig;
import gov.gwssi.csc.scms.repository.statistics.DConfigRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置Service层
 */
@Service("dConfigService")
public class DConfigService extends BaseService{
    @Autowired
    @Qualifier("dConfigRepository")
    private DConfigRepository dConfigRepository;

    public DConfig findById(String id){
        return dConfigRepository.findOne(id);
    }

}
