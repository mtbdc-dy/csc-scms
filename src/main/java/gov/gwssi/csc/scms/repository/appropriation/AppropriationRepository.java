package gov.gwssi.csc.scms.repository.appropriation;

import gov.gwssi.csc.scms.domain.appropriation.Appropriation;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by LiZhiSheng on 2015/9/14.
 */
@Repository("appropriationRepository")
public interface AppropriationRepository extends CrudRepository<Appropriation, String>, JpaSpecificationExecutor<Appropriation> {
}
