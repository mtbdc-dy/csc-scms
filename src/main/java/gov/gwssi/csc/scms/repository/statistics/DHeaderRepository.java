package gov.gwssi.csc.scms.repository.statistics;

import gov.gwssi.csc.scms.domain.statistics.DHeader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计表头配置信息操作接口
 */
@Repository("dHeaderRepository")
public interface DHeaderRepository  extends CrudRepository<DHeader,String> {
    List<DHeader> findByConfigId(String configId);
}
