package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectSecond;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceSubject2Repository")
public interface CodeMainTenanceSubject2Repository extends CrudRepository<CodemaintanenceSubjectSecond, String>, JpaSpecificationExecutor<CodemaintanenceSubjectSecond> {
}
