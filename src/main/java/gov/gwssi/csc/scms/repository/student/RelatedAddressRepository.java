package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Repository(value = "relatedAddressRepository")
public interface RelatedAddressRepository extends CrudRepository<RelatedAddress, String> {
    public List<RelatedAddress> findByStudentId(Long studentId);
}
