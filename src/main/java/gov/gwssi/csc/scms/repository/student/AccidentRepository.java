package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.Accident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository("accidentRepository")
public interface AccidentRepository extends CrudRepository<Accident, Long> {
}
