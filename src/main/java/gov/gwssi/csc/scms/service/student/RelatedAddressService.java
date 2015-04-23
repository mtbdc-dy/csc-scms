package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import gov.gwssi.csc.scms.repository.student.RelatedAddressRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("relatedAddressService")
public class RelatedAddressService extends BaseService {

    @Autowired
    @Qualifier("relatedAddressRepository")
    private RelatedAddressRepository relatedAddressRepository;

    public RelatedAddress getRelatedAddressById(Long id) {
        return relatedAddressRepository.findOne(id);
    }

    public RelatedAddress saveRelatedAddress(RelatedAddress relatedAddress) {
        return relatedAddressRepository.save(relatedAddress);
    }

    public Iterable saveRelatedAddress(List<RelatedAddress> relatedAddresses) {
        return relatedAddressRepository.save(relatedAddresses);
    }

    public RelatedAddress updateRelatedAddress(RelatedAddress relatedAddress) {
        RelatedAddress relatedAddress1 = getRelatedAddressById(relatedAddress.getId());
        super.copyFiledValue(RelatedAddress.class, relatedAddress, relatedAddress1);
        return relatedAddressRepository.save(relatedAddress1);
    }

    public Iterable updateRelatedAddress(List<RelatedAddress> relatedAddresses) {
        return relatedAddressRepository.save(relatedAddresses);
    }

    public List<RelatedAddress> getRelatedAddressByStudentId(Long studentId) {
        return relatedAddressRepository.findByStudentId(studentId);
    }

}
