package gov.gwssi.csc.scms.repository.codemaintenance;


import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionThird;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceTranslate;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceTranslateRepository")
public interface CodeMainTenanceTranslateRepository extends CrudRepository<CodemaintanenceTranslate, String>, JpaSpecificationExecutor<CodemaintanenceTranslate> {
}
