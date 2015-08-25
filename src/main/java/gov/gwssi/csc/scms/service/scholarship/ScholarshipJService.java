package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.dao.scholarshipJ.ScholarshipJDAO;
import gov.gwssi.csc.scms.domain.scholarship.ScholarshipDetail;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipDetailRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/8/18.
 * 奖学金评审服务类--JJW
 */
@Service("scholarshipJService")
public class ScholarshipJService extends BaseService {
    @Autowired
    @Qualifier("scholarshipDetailRepository")
    private ScholarshipDetailRepository scholarshipDetailRepository;
    @Autowired
    @Qualifier("scholarshipRepository")
    private ScholarshipRepository scholarshipRepository;


    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private ScholarshipJDAO scholarshipJDAO;

    //查询获取奖学金管理列表
    public List findListBy(String year,String state,String province,String univ,String qualified){
        return scholarshipJDAO.getListBy(year, state, province, univ,qualified);
    }

    //查询获取奖学金详细列表
    public List findDetailListBy(String scholarshipId){
        return scholarshipJDAO.getDetailListBy(scholarshipId);
    }

    }
