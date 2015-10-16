package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceProjectSecond;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceProject2Repository")
public interface CodeMainTenanceProject2Repository extends CrudRepository<CodemaintanenceProjectSecond, String>, JpaSpecificationExecutor<CodemaintanenceProjectSecond> {
}
