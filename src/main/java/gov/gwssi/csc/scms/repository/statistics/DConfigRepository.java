package gov.gwssi.csc.scms.repository.statistics;

import gov.gwssi.csc.scms.domain.statistics.DConfig;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置操作接口
 */
@Repository("dConfigRepository")
public interface DConfigRepository  extends CrudRepository<DConfig,String> {

}
