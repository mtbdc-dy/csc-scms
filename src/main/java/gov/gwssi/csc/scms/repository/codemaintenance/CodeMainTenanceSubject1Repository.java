package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceAnmlFirst;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceSubjectFirst;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceSubject1Repository")
public interface CodeMainTenanceSubject1Repository extends CrudRepository<CodemaintanenceSubjectFirst, String>, JpaSpecificationExecutor<CodemaintanenceSubjectFirst> {
}
