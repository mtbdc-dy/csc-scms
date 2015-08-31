package gov.gwssi.csc.scms.service.appropriation;

import gov.gwssi.csc.scms.dao.appropriation.AppropriationDAO;
import gov.gwssi.csc.scms.dao.scholarshipJ.ScholarshipJDAO;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipDetailRepository;
import gov.gwssi.csc.scms.repository.scholarshipX.ScholarshipRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gc on 2015/8/28.
 * 经费统计控制器
 */
@Service("appropriationService")
public class AppropriationService extends BaseService {
    @Autowired
    @Qualifier("scholarshipDetailRepository")
    private ScholarshipDetailRepository scholarshipDetailRepository;
    @Autowired
    @Qualifier("scholarshipRepository")
    private ScholarshipRepository scholarshipRepository;


    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private AppropriationDAO appropriationDAO;

    //查询经费统计列表
    public List getList(List listParameter){
        //调用存储过程
        String id=appropriationDAO.doSt("p_scms_scholarship", listParameter);
        //根据id找到生成的统计记录
        return appropriationDAO.getListById(id);
    }


    }
