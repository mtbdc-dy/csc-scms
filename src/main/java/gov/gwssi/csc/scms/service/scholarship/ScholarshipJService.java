package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.dao.scholarshipJ.ScholarshipJDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipJ;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipDetailRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipJRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by gc on 2015/8/18.
 * 奖学金评审服务类--JJW
 */
@Service("scholarshipJService")
public class ScholarshipJService extends ScholarshipJSpecs {
    @Autowired
    @Qualifier("scholarshipDetailRepository")
    private ScholarshipDetailRepository scholarshipDetailRepository;
    @Autowired
    @Qualifier("scholarshipRepository")
    private ScholarshipRepository scholarshipRepository;
    @Autowired
    @Qualifier("scholarshipJRepository")
    private ScholarshipJRepository scholarshipJRepository;

    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ScholarshipJDAO scholarshipJDAO;

    //查询获取奖学金管理列表
    public List findListBy(String year, String state, String province, String univ, String qualified) {
        return scholarshipJDAO.getListBy(year, state, province, univ, qualified);
    }

    //查询获取奖学金详细列表
    public List findDetailListBy(String scholarshipId) {
        return scholarshipJDAO.getDetailListBy(scholarshipId);
    }
    //分页查询
    public Page<ScholarshipJ> getScholarshipJsPagingByFilter(Filter filter,Integer page,Integer size,String mode,User user) {
        Specification<ScholarshipJ> specA = filterIsLike(filter,user);
//        Specification<ScholarshipJ> specB = userIs(user);
        return scholarshipJRepository.findAll(where(specA), new PageRequest(page, size , Sort.Direction.DESC,"state"));
    }
}
