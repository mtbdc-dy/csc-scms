package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * 报表配置域对象仓库
 * Created by wangzishi on 15/10/9.
 */
@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String>, JpaSpecificationExecutor<Configuration> {
    @Procedure
    String newId(@Param("seqName") String seqName);
}
