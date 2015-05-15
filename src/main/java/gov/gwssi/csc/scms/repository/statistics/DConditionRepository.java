package gov.gwssi.csc.scms.repository.statistics;

import gov.gwssi.csc.scms.domain.statistics.DCondition;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计条件配置操作接口
 */
@Repository("dConditionRepository")
public interface DConditionRepository  extends CrudRepository<DCondition,String> {
    List<DCondition> findByConfigId(String configId);
}
