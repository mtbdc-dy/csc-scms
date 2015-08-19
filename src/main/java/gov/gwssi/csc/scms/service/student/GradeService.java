package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.Grade;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.student.GradeRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 2015/4/16.
 */
@Service("gradeService")
public class GradeService extends BaseService {

    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    @Qualifier("grateRepository")
    private GradeRepository gradeRepository;
    @Autowired
    private BaseDAO baseDAO;

    public Grade getGradeById(String id) {
        return gradeRepository.findOne(id);
    }

    public List<Grade> saveGrade(List<Grade> grades) {
        for (Grade grade : grades)
            grade.setId(getBaseDao().getIdBySequence("SEQ_GRADE"));
        return (List<Grade>) gradeRepository.save(grades);
    }

    public Grade updateGrade(Grade grade) {
        grade.setStudent(getGradeById(grade.getId()).getStudent());
        return gradeRepository.save(grade);
    }

    public List<Grade> updateGrade(List<Grade> grades) {
        List<Grade> list = new ArrayList<Grade>();
        for (Grade grade : grades)
            list.add(updateGrade(grade));
        return list;
    }

    public List<Grade> getGradeByStudentId(String studentId) {
        return gradeRepository.findByStudentId(studentId);
    }

    public Grade saveGradeAndLog(Grade grade, List<OperationLog> operationLogs) throws Exception {
        operationLogService.saveOperationLog(operationLogs);
        grade.setId(getBaseDao().getIdBySequence("SEQ_GRADE"));
        return gradeRepository.save(grade);
    }

    public Grade editGrade(Grade grade, List<OperationLog> operationLogs) throws Exception {
        operationLogService.saveOperationLog(operationLogs);
        return gradeRepository.save(grade);
    }


    public Grade deleteGradeById(Student student, User user, String gradeId) {
        Grade grade = getGradeById(gradeId);
        if (grade == null)
            return null;

        List<OperationLog> operationLogs = new ArrayList<OperationLog>();
        OperationLog operationLog = new OperationLog();

        operationLog.setOptType("3");
        operationLog.setModule("在校生学籍管理");
        operationLog.setModuleId("BM003");
        operationLog.setStudentId(student.getId());
        operationLog.setCscId(student.getCscId());
        operationLog.setPassportName(student.getBasicInfo().getPassportName());
        String before = grade.getAnnual() + "/" + baseDAO.getNameCHByTranslateId(grade.getTerm()) + "/" + grade.getCourse() + "/" + grade.getGrade();
        operationLog.setBefore(before);
        operationLog.setAfter("");
        operationLog.setColumnCH("");
        operationLog.setColumnEN("");
        operationLog.setTableEN("scms_grade");
        operationLog.setTableCH("学生成绩表");
        operationLog.setNodeId(user.getNode().getNodeId());
        operationLog.setNodeType(user.getNode().getNodeType());
        operationLog.setCreateBy(user.getUserId());
        operationLog.setCreateD(new java.util.Date());

        operationLogs.add(operationLog);
        operationLogService.saveOperationLog(operationLogs);
        gradeRepository.delete(grade);
        grade.setStudent(null);
        return grade;
    }
}
