package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import gov.gwssi.csc.scms.repository.student.*;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.hibernate.id.enhanced.Optimizer;
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
    @Autowired
    @Qualifier("operationLogService")
    private OperationLogService operationLogService;

    public Student getStudentById(Long id) {
        Student student = studentRepository.findOne(id);
        student.getBasicInfo().setStudent(null);
        if (student.getSchoolfellow() != null)
            student.getSchoolfellow().setStudent(null);
        if (student.getDiscuss() != null)
            student.getDiscuss().setStudent(null);
        if (student.getProfilesHistory() != null)
            student.getProfilesHistory().setStudent(null);
        if (student.getRegistrationInfo() != null)
            student.getProfilesHistory().setStudent(null);
        if (student.getSchoolRoll() != null)
            student.getSchoolRoll().setStudent(null);
        List<Accident> accidents = student.getAccidents();
        if (accidents != null && accidents.size() > 0)
            for (Accident accident : accidents)
                accident.setStudent(null);
        List<RelatedAddress> relatedAddresses = student.getRelatedAddress();
        if (relatedAddresses != null && relatedAddresses.size() > 0)
            for (RelatedAddress relatedAddress : relatedAddresses)
                relatedAddress.setStudent(null);
        List<Grade> grades = student.getGrades();
        if (grades != null && grades.size() > 0)
            for (Grade grade : grades)
                grade.setStudent(null);
        List<GradeAttachment> gradeAttachments = student.getGradeAttachment();
        if ((gradeAttachments != null && gradeAttachments.size() > 0))
            for (GradeAttachment gradeAttachment : gradeAttachments)
                gradeAttachment.setStudent(null);
        return student;
    }

    public Student getStudentByCscId(String scsId) {
        return studentRepository.findByCscId(scsId);
    }

    public List<StudentResultObject> getStudentsByFilter(FilterObject filterObject) {
        List<StudentResultObject> studentList;
        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject);
        if (sql == null) {
            return null;
        }

        try {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            startPosition = 0;
            pageSize = 200;
        }

        studentList = super.getBaseDao().getObjectListByHQL(sql, StudentResultObject.class, startPosition, pageSize);
        return studentList;
    }

    public int getCountByQueryFilter(FilterObject filterObject) {
        String sql = getCountSqlByBody(filterObject);
        if (sql == null) {
            return 0;
        }

        int count = super.getBaseDao().getCountObjectByHQL(sql);
        return count;
    }

    private String getCountSqlByBody(FilterObject filterObject) {
        if (filterObject == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        String tempSql = "select count(*) " +
                "from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                "where student.basicInfo = basicInfo.student " +
                "and student.schoolRoll = schoolRoll.student ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter());
        return sb.toString();
    }

    private String getSqlByBody(FilterObject filterObject) {
        if (filterObject == null)
            return null;

        StringBuilder sb = new StringBuilder();

        sb.append(StudentResultObject.getResultObject());

        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll " +
                "where student.basicInfo = basicInfo.student " +
                "and student.schoolRoll = schoolRoll.student ";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter());
        return sb.toString();
    }

    @Transactional
    public Student saveStudent(Student student, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);

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

    @SuppressWarnings("unchecked")
    public Object getGroupByStudentId(Long studentId, String groupName) {
        if ("basicInfo".equalsIgnoreCase(groupName)) {
            BasicInfo basicInfo = basicInfoService.getBasicInfoByStudentId(studentId);
            return setNullByField(basicInfo, "student", BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(groupName)) {
            SchoolRoll schoolRoll = schoolRollService.getSchoolRollByStudentId(studentId);
            return setNullByField(schoolRoll, "student", SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(groupName)) {
            RegistrationInfo registrationInfo = registrationInfoService.getRegistrationInfoByStudentId(studentId);
            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(groupName)) {
            ProfilesHistory profilesHistory = profilesHistoryService.getProfilesHistoryByStudentId(studentId);
            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(groupName)) {
            Discuss discuss = discussService.getDiscussByStudentId(studentId);
            return setNullByField(discuss, "student", Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(groupName)) {
            Schoolfellow schoolfellow = schoolfellowService.getSchoolfellowByStudentId(studentId);
            return setNullByField(schoolfellow, "student", Schoolfellow.class);
        }
        if ("accident".equalsIgnoreCase(groupName)) {
            List<Accident> list = accidentService.getAccidentByStudentId(studentId);
            return setNullByField(list, "student", Accident.class);
        }
        if ("relatedAddress".equalsIgnoreCase(groupName)) {
            List<RelatedAddress> list = relatedAddressService.getRelatedAddressByStudentId(studentId);
            return setNullByField(list, "student", RelatedAddress.class);
        }
        if ("grade".equalsIgnoreCase(groupName)) {
            List<Grade> list = gradeService.getGradeByStudentId(studentId);
            return setNullByField(list, "student", Grade.class);
        }
        if ("gradeAttachment".equalsIgnoreCase(groupName)) {
            List<GradeAttachment> list = gradeAttachmentService.getGradeAttachmentByStudentId(studentId);
            return setNullByField(list, "student", GradeAttachment.class);
        }
        return null;
    }

    public Student deleteStudentById(Long studentId, List<OperationLog> operationLogs) {
        Student student = getStudentById(studentId);
        if (student == null)
            return null;
        studentRepository.delete(student);
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        return student;
    }

    @SuppressWarnings("unchecked")
    public Object updateGroupByName(String groupName, Object groupObj, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);

        if ("basicInfo".equalsIgnoreCase(groupName)) {
            BasicInfo basicInfo = basicInfoService.updateBasicInfo((BasicInfo) groupObj);
            return setNullByField(basicInfo, "student", BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(groupName)) {
            SchoolRoll schoolRoll = schoolRollService.updateSchoolRoll((SchoolRoll) groupObj);
            return setNullByField(schoolRoll, "student", SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(groupName)) {
            RegistrationInfo registrationInfo = registrationInfoService.updateRegistrationInfo((RegistrationInfo) groupObj);
            return setNullByField(registrationInfo, "student", RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(groupName)) {
            ProfilesHistory profilesHistory = profilesHistoryService.updateProfilesHistory((ProfilesHistory) groupObj);
            return setNullByField(profilesHistory, "student", ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(groupName)) {
            Discuss discuss = discussService.updateDiscuss((Discuss) groupObj);
            return setNullByField(discuss, "student", Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(groupName)) {
            Schoolfellow schoolfellow = schoolfellowService.updateSchoolfellow((Schoolfellow) groupObj);
            return setNullByField(schoolfellow, "student", Schoolfellow.class);
        }
        if ("accident".equalsIgnoreCase(groupName)) {
            List<Accident> list = accidentService.updateAccident((List<Accident>) groupObj);
            return setNullByField(list, "student", Accident.class);
        }
        if ("relatedAddress".equalsIgnoreCase(groupName)) {
            List<RelatedAddress> list = relatedAddressService.updateRelatedAddress((List<RelatedAddress>) groupObj);
            return setNullByField(list, "student", RelatedAddress.class);
        }
        if ("grade".equalsIgnoreCase(groupName)) {
            List<Grade> list = gradeService.updateGrade((List<Grade>) groupObj);
            return setNullByField(list, "student", Grade.class);
        }
        if ("gradeAttachment".equalsIgnoreCase(groupName)) {
            List<GradeAttachment> list = gradeAttachmentService.updateGradeAttachment((List<GradeAttachment>) groupObj);
            return setNullByField(list, "student", GradeAttachment.class);
        }
        return null;
    }
}
