package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectSecond;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectThird;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceSubject3Repository")
public interface CodeMainTenanceSubject3Repository extends CrudRepository<CodemaintanenceSubjectThird, String>, JpaSpecificationExecutor<CodemaintanenceSubjectThird> {
}
