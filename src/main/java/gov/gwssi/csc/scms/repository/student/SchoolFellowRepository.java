package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.SchoolFellow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Murray on 2015/4/16.
 */
@Repository(value = "basicInfoRepository")
public interface SchoolFellowRepository extends CrudRepository<SchoolFellow, Long> {
}

