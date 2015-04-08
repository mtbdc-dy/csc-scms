package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import gov.gwssi.csc.scms.domain.student.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository(value = "relatedAddressRepository")
public interface RelatedAddressRepository extends CrudRepository<RelatedAddress, String> {

    public List<RelatedAddress> findByStudent(String studentID);
}
