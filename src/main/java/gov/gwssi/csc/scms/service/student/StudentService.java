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

import java.util.ArrayList;
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

    public List getStuInfoList() {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "select t.projectname, t.continent," +
                "t.country,t.passportname from SCMS_BASIC_INFO t ";
        System.out.println("super.baseDAO============" + super.baseDAO);
        studentList = super.getBaseDao().queryListBySql(sql);
//        for (Student stu : studentRepository.findAll()){
//            studentList.add(stu);
//        }
        return studentList;
    }

    public List<StudentResultObject> getStudentsByFilter(FilterObject filterObject) {
        List<StudentResultObject> studentList;

        String sql = getSqlByBody(filterObject);
        if (sql == null) {
            return null;
        }

        studentList = super.getBaseDao().queryListBySql(sql);
        return studentList;
    }

    public Long getCountByQueryFilter(FilterObject filterObject) {
        Long count = 0L;

        String sql = getCountSqlByBody(filterObject);
        if (sql == null) {
            return count;
        }

        count = super.getBaseDao().getCountBySql(sql);
        return count;
    }

    private String getCountSqlByBody(FilterObject filterObject) {
        if (filterObject == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String tempSql = "select count(*) " +
                "from scms_student student " +
                "left join scms_basic_info basicinfo on student.basicinfo = basicinfo.studentid " +
                "left join scms_registration_info registrationinfo on student.registrationinfo = registrationinfo.studentid " +
                "where 1 = 1 ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter());
        return sb.toString();
    }

    private String getSqlByBody(FilterObject filterObject) {
        if (filterObject == null)
            return null;

        StringBuilder sb = new StringBuilder();

        sb.append(StudentResultObject.getResultObject());

        String tempSql = " from scms_student student " +
                "left join scms_basic_info basicinfo on student.basicinfo = basicinfo.studentid " +
                "left join scms_registration_info registrationinfo on student.registrationinfo = registrationinfo.studentid " +
                "where 1 = 1 ";
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
