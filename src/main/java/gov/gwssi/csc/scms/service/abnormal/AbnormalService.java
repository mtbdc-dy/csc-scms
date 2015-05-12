package gov.gwssi.csc.scms.service.abnormal;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.*;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.abnormal.AbnormalRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
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
        return abnormalRepository.findOne(id);
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
                "where student.id = basicInfo.student" +
                "and student.id = schoolRoll.student and student.id = abnormal.student ";
        sb.append(tempSql);

        sb.append(new AbnormalFilter((AbnormalFilterObject) filterObject).getFilter(user));
        return sb.toString();
    }
    //获取学校用户异动申请新增申请的学生列表
    public List<AddStudentResultObject> getAddStudentsByFilter(FilterObject filterObject, User user) {
        List<AddStudentResultObject> addStudentResultObjectsList;
        int startPosition, pageSize;

        String sql = getSqlByAddStudentBody(filterObject, user);
        System.out.println(sql);
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

        addStudentResultObjectsList = super.getBaseDao().getObjectListByHQL(sql, AddStudentResultObject.class, startPosition, pageSize);
        return addStudentResultObjectsList;
    }
    //获取新增异动申请学生列表对应的字段数据
    private String getSqlByAddStudentBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;

        StringBuilder sb = new StringBuilder();

        sb.append(AddStudentResultObject.getResultObject());

        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll  " +
                "where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user));
        return sb.toString();
    }
    //保存新增的异动申请
    @Transactional
    public Abnormal saveAbnormal(Abnormal abnormal, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        abnormal.setId(getBaseDao().getIdBySequence("SEQ_ABNORMAL"));
        return abnormalRepository.save(abnormal);
    }
    //更新异动申请状态
    @Transactional
    public Abnormal updateAbnormal(Abnormal abnormal, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);

        return abnormalRepository.save(abnormal);
    }
    //删除异动申请
    public Abnormal deleteAbnormalById(String abnormalId, List<OperationLog> operationLogs) {
        Abnormal abnormal = getAbnormalById(abnormalId);
        if (abnormal == null)
            return null;
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        abnormalRepository.delete(abnormal);
        return abnormal;
    }
}
