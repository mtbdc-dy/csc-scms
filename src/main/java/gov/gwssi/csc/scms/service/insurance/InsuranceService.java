package gov.gwssi.csc.scms.service.insurance;

import gov.gwssi.csc.scms.dao.insurance.InsuranceDAO;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.InsuranceResultObject;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.insurance.InsuranceRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.abnormal.NoSuchAbnormalException;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gc on 2015/7/17.
 * 保险服务类
 */
@Service("insuranceService")
public class InsuranceService extends BaseService {
    @Autowired
    @Qualifier("insuranceRepository")
    private InsuranceRepository insuranceRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private InsuranceDAO insuranceDAO;
    //生成保险管理清单
    public List<InsuranceResultObject> getInsuranceList(User user) {
        List listParameter = new ArrayList();
        List<InsuranceResultObject> InsuranceResultObjectList;
        listParameter.add("1");//传入“1”：正式
        insuranceDAO.doSt("p_scms_insurance",listParameter);//调用存储生成当年需要投保的保单记录
        int startPosition, pageSize;

        String sql = getSql(user);
        if (sql == null) {
            return null;
        }


            startPosition =FilterObject.OFFSETDEFULT;
            pageSize =FilterObject.PAGESIZEDEFULT;


        InsuranceResultObjectList = super.getBaseDao().getObjectListByHQL(sql, InsuranceResultObject.class, startPosition, pageSize);
        return InsuranceResultObjectList;

    }
    //查询获取机票管理列表
    public List<InsuranceResultObject> getInsuranceListByFilter(FilterObject filterObject,User user) {

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
            startPosition =FilterObject.OFFSETDEFULT;
            pageSize =FilterObject.PAGESIZEDEFULT;
        }

        InsuranceResultObjectList = super.getBaseDao().getObjectListByHQL(sql, InsuranceResultObject.class, startPosition, pageSize);
        return InsuranceResultObjectList;


    }
    //获取当前用户下的保险管理对应的字段数据 不加查询条件的sql
    private String getSql(User user) {
        StringBuilder sb = new StringBuilder();
        sb.append(InsuranceResultObject.getResultObject());
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        int year=ts.getYear()+1900;
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Insurance Insurance " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = Insurance.studentId and  Insurance.insurSta ='1'";//正式的
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
        int year=ts.getYear()+1900;
        sb.append(InsuranceResultObject.getResultObject());
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Insurance Insurance " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = Insurance.studentId and  Insurance.insurSta ='1'";//正式的
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
    public InsuranceResultObject getInsuranceAndStu(String id) throws Exception{
        //返回界面包含学生信息 根据保险id查出
        StringBuilder sb = new StringBuilder();
        sb.append(InsuranceResultObject.getResultObject());
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, Insurance Insurance " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student and student.id = Insurance.studentId  and  Insurance.insurSta ='1'";//正式的
        sb.append(tempSql);
        sb.append(" and Insurance.id = '").append(id).append("'");
        List<InsuranceResultObject> insuranceList = super.getBaseDao().getObjectListByHQL(sb.toString(), InsuranceResultObject.class, 0, 1);
        InsuranceResultObject insuranceResultObject = null;
        if(null == insuranceList || insuranceList.size()==0){
            throw new NoSuchAbnormalException("cannot find the insurance, please refresh the page!" );
        }else{
            insuranceResultObject = insuranceList.get(0);
        }
        return insuranceResultObject;
    }

    public Insurance getInsuranceById(String id) {
        return insuranceRepository.findById(id);
    }
    //删除保险记录
    public Insurance deleteInsuranceById(String id, List<OperationLog> operationLogs) {
        Insurance insurance = getInsuranceById(id);
        if (insurance == null)
            return null;
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        insuranceRepository.delete(insurance);
        return insurance;
    }
    public int getStuInsurance(String studentid,int year) {
        return insuranceDAO.getStuInsurance(studentid,year);
    }
    }
