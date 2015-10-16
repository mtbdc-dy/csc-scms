package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceDeptFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceDeptSecond;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceDept2Repository")
public interface CodeMainTenanceDept2Repository extends CrudRepository<CodemaintanenceDeptSecond, String>, JpaSpecificationExecutor<CodemaintanenceDeptSecond> {
}
