package gov.gwssi.csc.scms.service.warning;

import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.WarningResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.domain.warning.Warning;
import gov.gwssi.csc.scms.repository.warning.WarningRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.students.StudentConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by tianjing on 2015/7/17.
 */
@Service(value = "WarningService")
public class WarningService extends BaseService {

    @Autowired
    @Qualifier("warningRepository")
    private WarningRepository warningRepository;
    @Autowired
    private StudentService studentService;

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
                "and student.id = warning.student.id";

        //添加查询条件，并返回完整SQL语句
        return sqlStr + new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "", "");
    }

    //保存
    public Map<String, Object> saveWarning(Warning warning, String studentId) throws Exception {
        //保存新增的预警名单人员
        warning.setWarningId(getBaseDao().getIdBySequence("SEQ_WARNING"));
        warningRepository.save(warning);
        //与student主表建立关联
        Student student = studentService.getStudentById(studentId);
        student.setWarning(warning);
        studentService.updateStudent(student);

        String[] fields = {"id","warning.warningId", "cscId", "basicInfo.passportName", "schoolRoll.certificateNO", "basicInfo.country", "basicInfo.gender", "basicInfo.birthday", "schoolRoll.arrivalDate", "schoolRoll.planLeaveDate", "schoolRoll.majorUniversity", "warning.addTime", "warning.addUserName", "warning.addReason"};
        StudentConverter studentConverter = new StudentConverter(fields);
        Map<String, Object> map = studentConverter.convert(student);
        return map;
    }


    //删除
    @Transactional
    public Warning deleteWarningById(String id, String studentId) {
        Warning warning = getWarningById(id);
        if (warning == null)
            return null;
        try{
            Student student = studentService.getStudentById(studentId);
            student.setWarning(null);
            studentService.updateStudent(student);
            warningRepository.delete(warning);
        }catch (Exception e){
            e.printStackTrace();
        }
        return warning;
    }
}
