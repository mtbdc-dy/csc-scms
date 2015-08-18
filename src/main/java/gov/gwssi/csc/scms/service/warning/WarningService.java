package gov.gwssi.csc.scms.service.warning;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.WarningResultObject;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.domain.warning.Warning;
import gov.gwssi.csc.scms.repository.warning.WarningRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tianjing on 2015/7/17.
 */
@Service(value = "WarningService")
public class WarningService extends BaseService {

    @Autowired
    @Qualifier("warningRepository")
    private WarningRepository warningRepository;
    @Autowired
    private OperationLogService operationLogService;

    public Warning getWarningById(String id) {
        return warningRepository.findOne(id);
    }

    public Warning getWarningByStudentId(String studentId) {
        return warningRepository.findByStudentId(studentId);
    }

    public List<WarningResultObject> getWarningByFilter(FilterObject filterObject, User user) {
        List<WarningResultObject> studentList;
        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        System.out.println("getWarningSql===" + sql);
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

        studentList = super.getBaseDao().getObjectListByHQL(sql, WarningResultObject.class, startPosition, pageSize);
        return studentList;
    }

    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;

        //获取查询结果集
        String sqlStr = WarningResultObject.getResultObject();

        //添加查询实体
        sqlStr += " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, Warning warning " +
                "where student.id = basicInfo.student " +
                "and student.id = schoolRoll.student " +
                "and student.id = warning.studentId ";

        //添加查询条件，并返回完整SQL语句
        return sqlStr + new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "", "");
    }

    //保存
    @Transactional
    public String saveWarning(Warning warning, List<OperationLog> operationLogs) throws Exception {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        warning.setWarningId(getBaseDao().getIdBySequence("SEQ_WARNING"));
        //保存新增的预警名单人员
        warningRepository.save(warning);
        return warning.getWarningId();
    }

    // 根据id查询warningAndStu
    public WarningResultObject getWarningAndStu(String id) throws Exception {
        //返回界面包含学生信息 根据异动id查出
        StringBuilder sb = new StringBuilder();
        sb.append(WarningResultObject.getResultObject());
        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll, Warning warning " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student and student.id = warning.studentId ";
        sb.append(tempSql);
        sb.append(" and warning.warningId = '").append(id).append("'");
        List<WarningResultObject> warningList = super.getBaseDao().getObjectListByHQL(sb.toString(), WarningResultObject.class, 0, 1);
        WarningResultObject warningResultObject = null;
        if (null == warningList || warningList.size() == 0) {
            throw new NoSuchWarningException("cannot find the warning, please refresh the page!");
        } else {
            warningResultObject = warningList.get(0);
        }
        return warningResultObject;
    }

    //删除
    public Warning deleteWarningById(String id, List<OperationLog> operationLogs) {
        Warning warning = getWarningById(id);
        if (warning == null)
            return null;
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        warningRepository.delete(warning);
        return warning;
    }
}
