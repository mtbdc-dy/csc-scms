package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.*;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by WangZishi on 3/25/2015.
 */
@Service(value = "studentService")
public class StudentService extends BaseService {

    @Autowired
    @Qualifier("studentRepository")
    private StudentRepository studentRepository;
    @Autowired
    private BasicInfoService basicInfoService;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private RegistrationInfoService registrationInfoService;
    @Autowired
    private RelatedAddressService relatedAddressService;
    @Autowired
    private SchoolRollService schoolRollService;
    @Autowired
    private AccidentService accidentService;
    @Autowired
    private ProfilesHistoryService profilesHistoryService;
    @Autowired
    private SchoolfellowService schoolfellowService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private GradeAttachmentService gradeAttachmentService;


    public Student getStudentById(Long id) {
        return studentRepository.findOne(id);
    }

    public Student getStudentByCscId(String scsId) {
        return studentRepository.findByCscId(scsId);
    }

    public List<StudentResultObject> getStudentsByFilter(FilterObject filterObject) {
        List<StudentResultObject> studentList;

        String sql = getSqlByBody(filterObject);
        if (sql == null) {
            return null;
        }

        studentList = super.getBaseDao().getObjectListByNativeSQLAndType(sql, StudentResultObject.class);
        return studentList;
    }

    public int getCountByQueryFilter(FilterObject filterObject) {
        String sql = getCountSqlByBody(filterObject);
        if (sql == null) {
            return 0;
        }

        int count = super.getBaseDao().getCountByNativeSQL(sql);
        return count;
    }

    private String getCountSqlByBody(FilterObject filterObject) {
        if (filterObject == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String tempSql = "select count(*) " +
                "from SCMS_Student student,SCMS_Basic_Info basicInfo, SCMS_SchoolRoll schoolRoll " +
                "where student.basicInfo = basicInfo.studentId " +
                "and student.schoolRoll = schoolRoll.studentId ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter());
        return sb.toString();
    }

    private String getSqlByBody(FilterObject filterObject) {
        if (filterObject == null)
            return null;

        StringBuilder sb = new StringBuilder();

        sb.append(StudentResultObject.getResultObject());

        String tempSql = " from SCMS_Student student,SCMS_Basic_Info basicInfo, SCMS_SchoolRoll schoolRoll " +
                "where student.basicInfo = basicInfo.studentId " +
                "and student.schoolRoll = schoolRoll.studentId ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter());
        return sb.toString();
    }

    @Transactional
    public Student updateStudent(Student student) {
        return saveStudent(student);
    }

    @Transactional
    public Student saveStudent(Student student) {
        if (student.getBasicInfo() != null)
            basicInfoService.saveBasicInfo(student.getBasicInfo());
        if (student.getDiscuss() != null)
            discussService.saveDiscuss(student.getDiscuss());
        if (student.getRegistrationInfo() != null)
            registrationInfoService.saveRegistrationInfo(student.getRegistrationInfo());
        if (student.getSchoolRoll() != null)
            schoolRollService.saveSchoolRoll(student.getSchoolRoll());
        if (student.getProfilesHistory() != null)
            profilesHistoryService.saveProfilesHistory(student.getProfilesHistory());
        if (student.getRelatedAddress() != null && !student.getRelatedAddress().isEmpty())
            relatedAddressService.saveRelatedAddress(student.getRelatedAddress());
        if (!(student.getAccidents() == null || student.getAccidents().isEmpty()))
            accidentService.saveAccidents(student.getAccidents());
        if (!(student.getGrades() == null || student.getGrades().isEmpty()))
            gradeService.saveGrade(student.getGrades());
        if (!(student.getGradeAttachment() == null || student.getGradeAttachment().isEmpty()))
            gradeAttachmentService.saveGradeAttachment(student.getGradeAttachment());
        if (student.getSchoolfellow() != null)
            schoolfellowService.saveSchoolfellow(student.getSchoolfellow());

        return studentRepository.save(student);
    }
}
