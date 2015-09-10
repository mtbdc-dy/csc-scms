package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.DHeaderMerge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计表头合并配置信息操作接口
 */
@Repository("dHeaderMergeRepository")
public interface DHeaderMergeRepository extends CrudRepository<DHeaderMerge,String> {
    List<DHeaderMerge> findByConfigId(String configId);
}
