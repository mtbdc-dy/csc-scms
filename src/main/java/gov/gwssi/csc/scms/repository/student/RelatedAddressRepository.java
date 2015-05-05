package gov.gwssi.csc.scms.repository.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 * 数据操作接口
 */
@Repository(value = "relatedAddressRepository")
public interface RelatedAddressRepository extends CrudRepository<RelatedAddress, String> {
    List<RelatedAddress> findByStudentId(String studentId);
}
