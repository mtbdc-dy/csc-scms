package gov.gwssi.csc.scms.repository.warning;

import gov.gwssi.csc.scms.domain.warning.Warning;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("warningRepository")
public interface WarningRepository extends CrudRepository<Warning, String> {
    Warning findByStudentId(String studentId);
}
