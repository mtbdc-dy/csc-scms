package gov.gwssi.csc.scms.repository.dynamicReport.ASMS;

import gov.gwssi.csc.scms.domain.dynamicReport.ASMSConfiguration.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 报表配置域对象仓库
 * Created by wangzishi on 15/10/9.
 */
@Repository(value = "ASMSConfigurationRepository")
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
