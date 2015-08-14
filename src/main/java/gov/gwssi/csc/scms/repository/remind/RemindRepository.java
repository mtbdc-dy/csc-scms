package gov.gwssi.csc.scms.repository.remind;

import gov.gwssi.csc.scms.domain.remind.Remind;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianj on 2015/8/12.
 */
@Repository("remindRepository")
public interface RemindRepository extends CrudRepository<Remind, String> {
}
