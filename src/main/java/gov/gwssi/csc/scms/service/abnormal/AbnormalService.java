package gov.gwssi.csc.scms.service.abnormal;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.*;
import gov.gwssi.csc.scms.domain.user.Project;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.abnormal.AbnormalRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.student.NoSuchStudentException;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangZhenghua on 2015/4/24.
 */

@Service("abnormalService")
public class AbnormalService extends BaseService {
    @Autowired
    @Qualifier("abnormalRepository")
    private AbnormalRepository abnormalRepository;
    @Autowired
    private OperationLogService operationLogService;
    public Abnormal getAbnormalById(String id) {
        return abnormalRepository.findById(id);
    }
//    @Autowired
//    private StudentService studentService;
//获取学校用户异动申请列表
    public List<AbnormalResultObject> getAbnormalsByFilter(FilterObject filterObject, User user) {
        List<AbnormalResultObject> abnormalList;
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

        abnormalList = super.getBaseDao().getObjectListByHQL(sql, AbnormalResultObject.class, startPosition, pageSize);
        return abnormalList;
    }
    //获取异动申请列表对应的字段数据
    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;

        StringBuilder sb = new StringBuilder();

        sb.append(AbnormalResultObject.getResultObject());

        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, Abnormal abnormal " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student and student.id = abnormal.studentId ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user));
        //增加条件 基金委用户 查已提交的
        sb.append(getUserFilter(user));
        return sb.toString();
    }

    //增加条件 基金委用户 查已提交的
    public String getUserFilter(User user) {
        //用户类别：1基金委；2学校；
        String userType = user.getUserType();
        StringBuilder sb = new StringBuilder();
        if ("1".equals(userType)) {
            sb.append(" and abnormal.state !='AS0001'");//状态不为 未提交
        }
        if ("2".equals(userType)) {
            sb.append(" and schoolRoll.currentUniversity = '").append(user.getNode().getNodeId()).append("' ");
            return sb.toString();
        }
        return sb.toString();
    }


    //保存新增的异动申请
    @Transactional
    public String saveAbnormal(Abnormal abnormal, List<OperationLog> operationLogs) throws Exception{
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        abnormal.setId(getBaseDao().getIdBySequence("SEQ_ABNORMAL"));
        //保存异动
         abnormalRepository.save(abnormal);

        return abnormal.getId();
    }
    // 根据id查询abnormalAndStu
    public AbnormalResultObject getAbnormalAndStu(String id) throws Exception{
        //返回界面包含学生信息 根据异动id查出
        StringBuilder sb = new StringBuilder();
        sb.append(AbnormalResultObject.getResultObject());
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, Abnormal abnormal " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student and student.id = abnormal.studentId ";
        sb.append(tempSql);
        sb.append(" and abnormal.id = '").append(id).append("'");
        List<AbnormalResultObject> abnormalList = super.getBaseDao().getObjectListByHQL(sb.toString(), AbnormalResultObject.class, 0, 1);
        AbnormalResultObject abnormalResultObject = null;
        if(null == abnormalList || abnormalList.size()==0){
            throw new NoSuchAbnormalException("cannot find the abnormal, please refresh the page!" );
        }else{
            abnormalResultObject = abnormalList.get(0);
        }
        return abnormalResultObject;
    }

    //更新异动申请状态
    @Transactional
    public Abnormal updateAbnormal(Abnormal abnormal, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);

        return abnormalRepository.save(abnormal);
    }
    //删除异动申请
    public Abnormal deleteAbnormalById(String id, List<OperationLog> operationLogs) {
        Abnormal abnormal = getAbnormalById(id);
        if (abnormal == null)
            return null;
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        abnormalRepository.delete(abnormal);
        return abnormal;
    }
}
