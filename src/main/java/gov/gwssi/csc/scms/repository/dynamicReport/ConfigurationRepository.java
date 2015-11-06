package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration.Configuration;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

/**
 * 报表配置域对象仓库
 * Created by wangzishi on 15/10/9.
 */
@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String>, JpaSpecificationExecutor<Configuration> {
    @Procedure
    String newId(@Param("seqName") String seqName);

    @Procedure
    String generateStatisticsSQL(@Param("configId") String configId);

    @Procedure
    String generateQuerySQL(@Param("configId") String configId);

    @Procedure
    List test(@Param("in") String in);
}
