package gov.gwssi.csc.scms.service.appropriation;

import gov.gwssi.csc.scms.dao.appropriation.AppropriationDAO;
import gov.gwssi.csc.scms.dao.scholarshipJ.ScholarshipJDAO;
import gov.gwssi.csc.scms.domain.appropriation.Appropriation;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.appropriation.AppropriationRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipDetailRepository;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by gc on 2015/8/28.
 * 经费统计控制器
 */
@Service("appropriationService")
public class AppropriationService extends AppropriationSpecs
{
    @Autowired
    @Qualifier("appropriationRepository")
    private AppropriationRepository repository;

    public Page<Appropriation> getAppropriationsPagingByFilter(Filter filter, Integer page, Integer size)
    {
         String fundAttr = filter.getFundAttr() == null ? "" : filter.getFundAttr(),
                fundType = filter.getFundType() == null ? "" : filter.getFundType(),
                fundStandard = filter.getFundStandard() == null ? "" : filter.getFundStandard(),
                planned = filter.getPlanned() == null ? "" : filter.getPlanned(),
                 //一期升级 三级变两级 去掉项目属性
//                projectAttr = filter.getProjectAttr() == null ? "" : filter.getProjectAttr(),
                projectAttr = "",
                projectType = filter.getProjectType() == null ? "" : filter.getProjectType(),
                projectName = filter.getProjectName() == null ? "" : filter.getProjectName();

        String sameId = repository.statistic(fundAttr, fundType, fundStandard, planned, projectAttr, projectType, projectName);
        System.out.println(sameId);
        Specification<Appropriation> specA = filterIsLike(sameId);
        return repository.findAll(where(specA), new PageRequest(page, size, Sort.Direction.ASC, "id"));
    }
}
