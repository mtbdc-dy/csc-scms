package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceDeptFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceDept1Repository")
public interface CodeMainTenanceDept1Repository extends CrudRepository<CodemaintanenceDeptFirst, String>, JpaSpecificationExecutor<CodemaintanenceDeptFirst> {
}
