package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.DDisplay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计显示样式操作接口
 */
@Repository("dDisplayRepository")
public interface DDisplayRepository extends CrudRepository<DDisplay,String> {
    List<DDisplay> findByConfigId(String configId);
}
