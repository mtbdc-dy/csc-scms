package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.RelatedAddress;
import gov.gwssi.csc.scms.repository.student.RelatedAddressRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("relatedAddressService")
public class RelatedAddressService extends BaseService {

    @Autowired
    @Qualifier("relatedAddressRepository")
    private RelatedAddressRepository relatedAddressRepository;

    public RelatedAddress getRelatedAddressById(String id) {
        return relatedAddressRepository.findOne(id);
    }

    public Iterable saveRelatedAddress(List<RelatedAddress> relatedAddresses) {
        for (RelatedAddress relatedAddress : relatedAddresses)
            relatedAddress.setId(getBaseDao().getIdBySequence("SEQ_RELATED_ADDRESS"));
        return relatedAddressRepository.save(relatedAddresses);
    }

    public RelatedAddress updateRelatedAddress(RelatedAddress relatedAddress) {
        relatedAddress.setStudent(getRelatedAddressById(relatedAddress.getId()).getStudent());
        return relatedAddressRepository.save(relatedAddress);
    }

    public List<RelatedAddress> updateRelatedAddress(List<RelatedAddress> relatedAddresses) {
        List<RelatedAddress> list = new ArrayList<RelatedAddress>();
        for (RelatedAddress relatedAddress : relatedAddresses)
            list.add(updateRelatedAddress(relatedAddress));
        return list;
    }

    public List<RelatedAddress> getRelatedAddressByStudentId(String studentId) {
        return relatedAddressRepository.findByStudentId(studentId);
    }

}
