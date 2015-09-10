package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.ReportConfiguration;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZhenghua on 2015/5/14.
 * 动态查询统计配置操作接口
 */
@Repository
public interface ReportConfigurationRepository extends CrudRepository<ReportConfiguration, String>, JpaSpecificationExecutor<ReportConfiguration> {
}
