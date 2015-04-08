package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.student.Discuss;
import gov.gwssi.csc.scms.repository.student.DiscussRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Murray on 4/3/2015.
 */
@Service("discussService")
public class DiscussService extends BaseService {

    @Autowired
    private DiscussRepository discussRepository;

    public Discuss getDiscussById(Long id) {
        return discussRepository.findOne(id);
    }

    public Discuss saveDiscuss(Discuss discuss) {
        return discussRepository.save(discuss);
    }
}
