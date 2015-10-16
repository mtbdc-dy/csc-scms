package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceProject1Repository")
public interface CodeMainTenanceProject1Repository extends CrudRepository<CodemaintanenceProjectFirst, String>, JpaSpecificationExecutor<CodemaintanenceProjectFirst> {
}
