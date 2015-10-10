package gov.gwssi.csc.scms.service.timeset;

import gov.gwssi.csc.scms.dao.timeset.TimeSetDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.universities.DimUniv;
import gov.gwssi.csc.scms.domain.universities.DimUniv_;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.timeset.TimeSetRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


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
        return timeSetRepository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "province", "univId"));
    }
    public Map<String,String> getFreshRegisterTimeSet(String nodeId){

        Map<String,String> result = new HashMap<String, String>();
        DimUniv univ = timeSetRepository.findByUnivId(nodeId);
        if(univ.getNewBegin()!=null && univ.getNewEnd()!=null){
            long newBegin = univ.getNewBegin().getTime();
            long newEnd = univ.getNewEnd().getTime();
            long sysTime = new Date().getTime();
            if(sysTime >= newBegin && sysTime <= newEnd){
                result.put("result","true");
            }else{
                result.put("result","false");
            }
        }else{
            result.put("result","false");
        }
        return result;
    }
    public Map<String,String> getOldRegisterTimeSet(String nodeId){

        Map<String,String> result = new HashMap<String, String>();
        DimUniv univ = timeSetRepository.findByUnivId(nodeId);
        if(univ.getOldBegin()!=null && univ.getOldEnd()!=null){
            long oldBegin = univ.getOldBegin().getTime();
            long oldEnd = univ.getOldEnd().getTime();
            long sysTime = new Date().getTime();
            if(sysTime >= oldBegin && sysTime <= oldEnd){
                result.put("result","true");
            }else{
                result.put("result","false");
            }
        }else{
            result.put("result","false");
        }
        return result;
    }
}
