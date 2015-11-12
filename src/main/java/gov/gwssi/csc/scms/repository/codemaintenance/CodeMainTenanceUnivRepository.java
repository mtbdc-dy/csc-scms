package gov.gwssi.csc.scms.repository.codemaintenance;


import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceRegionSecond;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintanenceUniv;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/24.
 */
@Repository("codeMainTenanceUnivRepository")
public interface CodeMainTenanceUnivRepository
        extends CrudRepository<CodemaintanenceUniv, String>, JpaSpecificationExecutor<CodemaintanenceUniv>
{
    @Procedure
    String changeSortValue(@Param("id")String id, @Param("direction")String direction, @Param("step")Long step);
}
