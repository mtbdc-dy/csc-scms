package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectSecond;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectThird;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceProject3Repository")
public interface CodeMainTenanceProject3Repository extends CrudRepository<CodemaintanenceProjectThird, String>, JpaSpecificationExecutor<CodemaintanenceProjectThird> {
}
