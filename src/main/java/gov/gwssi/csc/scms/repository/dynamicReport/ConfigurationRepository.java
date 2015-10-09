package gov.gwssi.csc.scms.repository.dynamicReport;

import gov.gwssi.csc.scms.domain.dynamicReport.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * Created by wangzishi on 15/10/9.
 */
@Repository
public interface ConfigurationRepository extends CrudRepository<Configuration, String> {
}
