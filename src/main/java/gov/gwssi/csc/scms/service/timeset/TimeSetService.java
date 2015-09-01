package gov.gwssi.csc.scms.service.timeset;

import gov.gwssi.csc.scms.dao.timeset.TimeSetDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.universities.DimUniv;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.timeset.TimeSetRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by LiZhiSheng on 2015/8/10.
 */
@Service("timeSetService")
public class TimeSetService extends TimeSetSpecs {
    @Autowired
    private TimeSetDAO timeSetDAO;
    @Autowired
    @Qualifier("timeSetRepository")
    private TimeSetRepository timeSetRepository;
    public List findProAndUniv(String pro,String univ){
        return timeSetDAO.getNewStudentTimeSetList(pro, univ);
    }
    @Transactional
    public int setTime(String userName,String begin,String end,String ids){
        return timeSetDAO.setTime(userName, begin, end, ids);
    }
    public List findOldProAndUniv(String pro,String univ){
        return timeSetDAO.getOldStudentTimeSetList(pro, univ);
    }
    @Transactional
    public int setOldTime(String userName,String begin,String end,String ids){
        return timeSetDAO.setOldTime(userName, begin, end, ids);
    }
    public Page<DimUniv> getDimUnivsPagingByFilter(Filter filter,Integer page,Integer size,String mode,User user) {
        Specification<DimUniv> specA = filterIsLike(filter,user);
//        Specification<Ticket> specB = userIs(user);
        return timeSetRepository.findAll(where(specA), new PageRequest(page, size));
    }
}
