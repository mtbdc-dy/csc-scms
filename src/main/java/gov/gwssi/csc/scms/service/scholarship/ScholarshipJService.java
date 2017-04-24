package gov.gwssi.csc.scms.service.scholarship;

import gov.gwssi.csc.scms.dao.scholarshipJ.ScholarshipJDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.scholarship.Scholarship;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List findDetailListExport(String scholarshipId) {
        return scholarshipJDAO.getDetailListExport(scholarshipId);
    }

    //分页查询
    public Page<ScholarshipJ> getScholarshipJsPagingByFilter(Filter filter,Integer page,Integer size,String mode) {
        Specification<ScholarshipJ> specA = filterIsLike(filter);
        return scholarshipJRepository.findAll(where(specA), new PageRequest(page, size));
    }

    //得到所有查询结果的SCHOLARSHIPID,用于全部导出
    public String[] getAllScholarshipJsByFilter(Filter filter) {
        Specification<ScholarshipJ> specA = filterIsLike(filter);
        List<ScholarshipJ> scholarshipJList = scholarshipJRepository.findAll(where(specA).and(stateIs('2')));
        String result[]=new String[scholarshipJList.size()];
        for(int i=0;i<scholarshipJList.size();i++){
            String id = scholarshipJList.get(i).getScholarshipId();
            result[i] = id;
        }
        return result;
    }

    //统计未上报已上报学校数量
    public Map<String,Long> getScholarshipJSchoolNum(Filter filter){
        long unSubmitted = 0;
        long submitted = 0;
        long sum = 0;
        Specification<ScholarshipJ> specA = filterIsLike(filter);
        sum = scholarshipJRepository.count(where(specA));
        unSubmitted = scholarshipJRepository.count(where(specA).and(stateIs('1')));
        submitted = scholarshipJRepository.count(where(specA).and(stateIs('2')));
        List<ScholarshipJ> scholarshipJs = scholarshipJRepository.findAll(where(specA));
        long cscQualCount =0;
        long cscUnQualCount =0;
        for(int i=0;i<scholarshipJs.size();i++){
            cscQualCount += scholarshipJs.get(i).getCscQual();
            cscUnQualCount += scholarshipJs.get(i).getCscUnQual();
        }
        Map<String,Long> result = new HashMap<String, Long>();
        result.put("sum",sum);
        result.put("unSubmitted",unSubmitted);
        result.put("submitted",submitted);
        result.put("cscQualCount",cscQualCount);
        result.put("cscUnQualCount",cscUnQualCount);
        return result;
    }
    @Transactional
    public void sendBacking(String scholarshipId){
        try {
            Scholarship scholarship = scholarshipRepository.findById(scholarshipId);
            scholarship.setSchoolSta("3"); //该校该年度记录的状态为3（已退回）
            scholarship.setCscQual(scholarship.getSchoolQual());
            scholarship.setCscUnQual(scholarship.getSchoolUnQual());//基金委合格、不合格人数，改为与院校一致
            scholarshipRepository.save(scholarship);
            scholarshipDetailRepository.sendBacking(scholarshipId);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }
}
