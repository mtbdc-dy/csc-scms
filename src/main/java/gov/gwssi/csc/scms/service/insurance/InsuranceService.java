
package gov.gwssi.csc.scms.service.insurance;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.dao.insurance.InsuranceDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.InsuranceResultObject;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.insurance.InsuranceRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by gc on 2015/7/17.
 * 保险服务类
 */
@Service("insuranceService")
public class InsuranceService extends InsuranceSpecs {
    @Autowired
    @Qualifier("insuranceRepository")
    private InsuranceRepository insuranceRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private UserService userService;
    @Autowired
    private InsuranceDAO insuranceDAO;
    @Autowired
    private BaseDAO baseDAO;

    //生成保险管理清单
    public Map<String, String> getInsuranceList(User user) {
        List listParameter = new ArrayList();
        String userId = user.getUserId();
        listParameter.add(userId);
        List<InsuranceResultObject> InsuranceResultObjectList;
        listParameter.add("1");//传入“1”：正式
        insuranceDAO.doSt("p_scms_insurance", listParameter);//调用存储生成当年需要投保的保单记录
//        int startPosition, pageSize;
//
//        String sql = getSql(user);
//        if (sql == null) {
//            return null;
//        }
//
//
//        startPosition = FilterObject.OFFSETDEFULT;
//        pageSize = FilterObject.PAGESIZEDEFULT;
//
//
//        InsuranceResultObjectList = super.getBaseDao().getObjectListByHQL(sql, InsuranceResultObject.class, startPosition, pageSize);
//        return InsuranceResultObjectList;
        Map<String, String> result = new HashMap<String, String>();
        result.put("result", "success");
        return result;

    }

    //查询获取保险列表
    public List<InsuranceResultObject> getInsuranceListByFilter(FilterObject filterObject, User user) {

        List<InsuranceResultObject> InsuranceResultObjectList;


        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        if (sql == null) {
            return null;
        }

        try {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
        }

        InsuranceResultObjectList = super.getBaseDao().getObjectListByHQL(sql, InsuranceResultObject.class, startPosition, pageSize);
        return InsuranceResultObjectList;


    }

    //获取当前用户下的保险管理对应的字段数据 不加查询条件的sql
    private String getSql(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(InsuranceResultObject.getResultObject());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int year = ts.getYear() + 1900;
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Insurance Insurance " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = Insurance.student.id and  Insurance.insurSta ='1'";//正式的
        sb.append(tempSql);
        sb.append(" and Insurance.year = '").append(year).append("'");//只显示当前年份的
        return sb.toString();
    }

    //获取机票管理列表对应的字段数据
    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;
        StringBuilder sb = new StringBuilder();
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int year = ts.getYear() + 1900;
        sb.append(InsuranceResultObject.getResultObject());
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Insurance Insurance " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = Insurance.student.id and  Insurance.insurSta ='1'";//正式的
        sb.append(tempSql);
        sb.append(" and Insurance.year = '").append(year).append("'");//只显示当前年份的
        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "insurance", ""));
        return sb.toString();
    }

    //保存新增的保险记录
    @Transactional
    public String saveInsurance(Insurance insurance, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        insurance.setId(getBaseDao().getIdBySequence("SEQ_INSURANCE"));
        insuranceRepository.save(insurance);
        return insurance.getId();
    }

    // 根据id查询insuranceAndStu
    public InsuranceResultObject getInsuranceAndStu(String id) throws Exception {
        //返回界面包含学生信息 根据保险id查出
        StringBuilder sb = new StringBuilder();
        sb.append(InsuranceResultObject.getResultObject());
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, Insurance Insurance " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student and student.id = Insurance.student.id  and  Insurance.insurSta ='1'";//正式的
        sb.append(tempSql);
        sb.append(" and Insurance.id = '").append(id).append("'");
        List<InsuranceResultObject> insuranceList = super.getBaseDao().getObjectListByHQL(sb.toString(), InsuranceResultObject.class, 0, 1);
        InsuranceResultObject insuranceResultObject = null;
        if (null == insuranceList || insuranceList.size() == 0) {
            throw new NoSuchAbnormalException("cannot find the insurance, please refresh the page!");
        } else {
            insuranceResultObject = insuranceList.get(0);
        }
        return insuranceResultObject;
    }

    public Insurance getInsuranceById(String id) {
        return insuranceRepository.findById(id);
    }

    //删除保险记录
    public void deleteInsuranceById(String id, List<OperationLog> operationLogs) {
        Insurance insurance = getInsuranceById(id);
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        insuranceRepository.delete(insurance);
    }

    //导出后更新保险导出状态
    public void updateInsurancePresta(String[] id) {
        for (int i = 0; i < id.length; i++) {
            Insurance insurance = getInsuranceById(id[i]);
            if("AV0001".equals(insurance.getPreSta())){
                insurance.setPreSta("AV0002");
                insuranceRepository.save(insurance);
            }
        }


    }

    public List<Insurance> findInsuranceByStduentId(String studentId) {
        return insuranceRepository.findByStudentIdOrderByYearDesc(studentId);
    }


    public int getStuInsurance(String studentid, int year) {
        return insuranceDAO.getStuInsurance(studentid, year);
    }

    //分页查询
    @Transactional
    public Page<Insurance> getInsurancesPagingByFilter(Filter filter, Integer page, Integer size, String mode, String header) {
        try {
            User user = userService.getUserByJWT(header);
            Specification<Insurance> specA = filterIsLike(filter, user, mode);
            Specification<Insurance> specB = userIs(user);
            return insuranceRepository.findAll(where(specA).and(specB), new PageRequest(page, size));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String[] getAllInsuranceByFilter(Filter filter,String mode, String header) {
        List<Insurance> insurances;
        try {
            User user = userService.getUserByJWT(header);
            Specification<Insurance> specA = filterIsLike(filter, user, mode);
            Specification<Insurance> specB = userIs(user);
            insurances = insuranceRepository.findAll(where(specA).and(specB));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String result[]=new String[insurances.size()];
        for(int i=0;i<insurances.size();i++){
            String id = insurances.get(i).getId();
            result[i] = id;
        }
        return result;
    }

    //统计保险状态 已导出 未导出 已反馈
    @Transactional
    public Map<String, Long> getInsurancesStateSum(String header,Filter filter,String mode) {
        long zs = 0;
        long yfk = 0;
        long jjwwdc = 0;
        long jjwydc = 0;
        try {
            User user = userService.getUserByJWT(header);
            Specification<Insurance> specA = filterIsLike(filter, user, mode);
            Specification<Insurance> specB = userIs(user);
            zs = insuranceRepository.count(where(specA).and(specB));
            yfk = insuranceRepository.count(where(specA).and(specB).and(stateIs("AV0003")));
            jjwwdc = insuranceRepository.count(where(specA).and(specB).and(stateIs("AV0001")));
            jjwydc = insuranceRepository.count(where(specA).and(specB).and(stateIs("AV0002")));
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("zs", zs);
        result.put("yfk", yfk);
        result.put("jjwwdc", jjwwdc);
        result.put("jjwydc", jjwydc);
        return result;

    }


    //新增学生时首先校验该学生是否已经存在于保险列表中
    public Map<String, String> verifyInsuranceStudent(String studentId) {
        Map<String, String> result = new HashMap<String, String>();
        Insurance insurance = insuranceRepository.findByStudentIdAndInsurSta(studentId, "1");
        if (insurance != null) {
            result.put("result", "failed");
        } else {
            result.put("result", "success");
        }
        return result;
    }
}
