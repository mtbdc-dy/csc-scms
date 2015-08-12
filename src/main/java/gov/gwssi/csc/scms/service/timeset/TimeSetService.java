package gov.gwssi.csc.scms.service.timeset;

import gov.gwssi.csc.scms.dao.timeset.TimeSetDAO;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by LiZhiSheng on 2015/8/10.
 */
@Service("timeSetService")
public class TimeSetService extends BaseService {
    @Autowired
    private TimeSetDAO timeSetDAO;
    public List findProAndUniv(String pro,String univ){
        return timeSetDAO.getNewStudentTimeSetList(pro, univ);
    }
    @Transactional
    public int setTime(String userName,String begin,String end,String ids){
        return timeSetDAO.setTime(userName, begin, end, ids);
    }
}
