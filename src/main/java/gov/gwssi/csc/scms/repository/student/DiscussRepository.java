package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Discuss;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository(value = "discussRepository")
public interface DiscussRepository extends CrudRepository<Discuss, Long> {
}
