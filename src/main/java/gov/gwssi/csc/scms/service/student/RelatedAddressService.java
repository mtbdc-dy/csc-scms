package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import gov.gwssi.csc.scms.repository.student.RelatedAddressRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("relatedAddressService")
public class RelatedAddressService extends BaseService {

    @Autowired
    private RelatedAddressRepository relatedAddressRepository;

    public RelatedAddress getRelatedAddressById(String id) {
        return relatedAddressRepository.findOne(id);
    }

    public RelatedAddress saveRelatedAddress(RelatedAddress relatedAddress) {
        return relatedAddressRepository.save(relatedAddress);
    }

    public Iterable saveRelatedAddress(List<RelatedAddress> relatedAddresses) {
        Iterable iterable = relatedAddresses;
        return relatedAddressRepository.save(iterable);
    }

}
