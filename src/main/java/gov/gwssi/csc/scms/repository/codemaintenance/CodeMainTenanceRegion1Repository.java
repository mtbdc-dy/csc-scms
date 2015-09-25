package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceRegion1Repository")
public interface CodeMainTenanceRegion1Repository extends CrudRepository<CodemaintanenceRegionFirst, String>, JpaSpecificationExecutor<CodemaintanenceRegionFirst> {
}
