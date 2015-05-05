package gov.gwssi.csc.scms.service.user;

import gov.gwssi.csc.scms.domain.user.Right;
import gov.gwssi.csc.scms.repository.user.RightRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Lei on 2015/5/5.
 */
public class RightService extends BaseService {

    @Autowired
    RightRepository rightRepository;

    public Right getRightByRightId(String rightId) {
        return rightRepository.findOne(rightId);
    }

    public Right saveRight(Right right) {
        right.setRegionId(getBaseDao().getDicIdByClassType(right.getType()));
        return rightRepository.save(right);
    }

}
