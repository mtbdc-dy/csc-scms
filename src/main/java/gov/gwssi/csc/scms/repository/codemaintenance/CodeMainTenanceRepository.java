package gov.gwssi.csc.scms.repository.codemaintenance;

import gov.gwssi.csc.scms.domain.codemaintenance.CodeMainTenance;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/10.
 */
@Repository("codeMainTenanceRepository")
public interface CodeMainTenanceRepository extends CrudRepository<CodeMainTenance, String>, JpaSpecificationExecutor<CodeMainTenance> {


}
