package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository(value = "relatedAddressRepository")
public interface RelatedAddressRepository extends CrudRepository<RelatedAddress, String> {
}
