package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceAnmlFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceAnml1Repository")
public interface CodeMainTenanceAnml1Repository extends CrudRepository<CodemaintanenceAnmlFirst, String>, JpaSpecificationExecutor<CodemaintanenceAnmlFirst> {
}
