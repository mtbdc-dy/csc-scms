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
public class AppropriationService extends AppropriationSpecs {
    @Autowired
    @Qualifier("scholarshipDetailRepository")
    private ScholarshipDetailRepository scholarshipDetailRepository;
    @Autowired
    @Qualifier("scholarshipRepository")
    private ScholarshipRepository scholarshipRepository;

    @Autowired
    @Qualifier("appropriationRepository")
    private AppropriationRepository appropriationRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private AppropriationDAO appropriationDAO;

    //查询经费统计列表
//    public List getList(List listParameter){
//        //调用存储过程
//        String id=appropriationDAO.doSt("p_scms_stats_appropriation", listParameter);
//        //根据id找到生成的统计记录
//        return appropriationDAO.getListById(id);
//    }

    public Page<Appropriation> getAppropriationsPagingByFilter(Filter filter,Integer page,Integer size) {
        List listParameter = new ArrayList();
/**
 * 存储参数的顺序
 * listParameter.add("");
 listParameter.add(appropriation);
 listParameter.add("");
 listParameter.add(planned);
 listParameter.add(projectAttr);
 listParameter.add(projectType);
 listParameter.add(projectName);
 */
        listParameter.add("");
        if (filter.getAppropriation()!= null) {
            listParameter.add(filter.getAppropriation());
        }else{
            listParameter.add("");
        }
        listParameter.add("");
        if (filter.getPlanned()!=null) {
            listParameter.add(filter.getPlanned());
        }else{
            listParameter.add("");
        }

        if (filter.getProjectAttr()!=null) {
            listParameter.add(filter.getProjectAttr());
        }else{
            listParameter.add("");
        }
        if (filter.getProjectType()!=null) {
            listParameter.add(filter.getProjectType());
        }else{
            listParameter.add("");
        }
        if (filter.getProjectName()!=null) {
            listParameter.add(filter.getProjectName());
        }else {
            listParameter.add("");
        }

        System.out.println(listParameter);
        String sameId = getBaseDao().doStatementForRtn("p_scms_stats_appropriation", listParameter);
        System.out.println(sameId);
        Specification<Appropriation> specA = filterIsLike(sameId);
        return  appropriationRepository.findAll(where(specA), new PageRequest(page, size,Sort.Direction.ASC,"id"));
    }


    }
