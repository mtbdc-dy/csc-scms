package gov.gwssi.csc.scms.repository.importLog;

import gov.gwssi.csc.scms.domain.importlog.ImportLog;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by LiZhiSheng on 2015/9/1.
 */
public interface ImportLogRepository extends CrudRepository<ImportLog,String>, JpaSpecificationExecutor<ImportLog> {

}
