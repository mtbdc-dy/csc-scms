package gov.gwssi.csc.scms.repository.codemaintenance;
import gov.gwssi.csc.scms.domain.codemaintenance.CodemaintenanceAgency;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by TianJ on 2016/12/23.
 */
@Repository("CodeMainTenanceAgencyRepository")
public interface CodeMainTenanceAgencyRepository extends CrudRepository<CodemaintenanceAgency, String>, JpaSpecificationExecutor<CodemaintenanceAgency> {
}
