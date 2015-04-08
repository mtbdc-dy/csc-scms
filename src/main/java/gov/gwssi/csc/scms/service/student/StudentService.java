package gov.gwssi.csc.scms.service.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.queryfilter.StudentFilterObject;
import gov.gwssi.csc.scms.domain.queryfilter.StudentQueryFilter;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.repository.student.*;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.io.IOException;
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


    public Student getStudentByID(String id) {
        return studentRepository.findOne(id);
    }

    public List getStuInfoList() {
        List<Student> studentList = new ArrayList<Student>();
        String sql = "select t.project_name, t.continent_name," +
                "t.country_name,t.passport_name from SCMS_BASIC_INFO t ";
        System.out.println("super.baseDAO============" + super.baseDAO);
        studentList = super.getBaseDao().queryListBySql(sql);
//        for (Student stu : studentRepository.findAll()){
//            studentList.add(stu);
//        }

        return studentList;
    }

    public List<Student> getStudentsByQueryFilter(String body) {
        List<Student> studentList;
        String sql = getSqlByBody(body);
        if (sql == null) return null;
        studentList = super.getBaseDao().queryListBySql(sql);
        return studentList;
    }

    private String getSqlByBody(String body) {
        StringBuilder sb = new StringBuilder();
        String tempSql = "select student.* from scms_student student " +
                "left join scms_basic_info basicinfo on student/.basicinfo = basicinfo.student " +
                "left join scms_registration_info registrationinfo on student.registrationinfo = registrationinfo.student " +
                "left join scms_student discuss on student.discuss = discuss.id " +
                "left join scms_schoolroll schoolroll on student.schoolroll = schoolroll.id " +
                "left join scms_related_address relatedaddress on student.relatedaddress = relatedaddress.student " +
                "left join scms_accident accident on student.accident = accident.student " +
                "where 1 = 1 ";
        sb.append(tempSql);

        StudentFilterObject sto = null;
        try {
            sto = new ObjectMapper().readValue(body, StudentFilterObject.class);
            sb.append(new StudentQueryFilter(sto).getQueryFilter());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    private Student constractStudent(Student student) {
        student.setAccident(accidentService.getAccidentsByStudent(student));
        student.setRelatedAddress(relatedAddressService.getRelatedAddressByStudent(student));
        student.setRegistrationInfo(registrationInfoService.getRegistrationInfoByStudent(student));
        student.setBasicInfo(basicInfoService.getBasicInfoByStudent(student));
        student.setDiscuss(discussService.getDiscussByStudent(student));
        student.setSchoolRoll(schoolRollService.getSchoolRollByStudent(student));
        student.setProfilesHistory(profilesHistoryService.getProfilesHistoryByStudent(student));
        return student;
    }

    @Transient
    public Student saveStudent(Student student) {
        if (student.getBasicInfo() != null)
            basicInfoService.saveBasicInfo(student.getBasicInfo());
        if (student.getDiscuss() != null)
            discussService.saveDiscuss(student.getDiscuss());
        if (student.getRegistrationInfo() != null)
            registrationInfoService.saveRegistrationInfo(student.getRegistrationInfo());
        if (!student.getRelatedAddress().isEmpty())
            relatedAddressService.saveRelatedAddress(student.getRelatedAddress());
        if (student.getSchoolRoll() != null)
            schoolRollService.saveSchoolRoll(student.getSchoolRoll());
        if (!student.getRelatedAddress().isEmpty())
            accidentService.saveAccidents(student.getAccident());
        if (student.getProfilesHistory() != null)
            profilesHistoryService.saveProfilesHistory(student.getProfilesHistory());
        return studentRepository.save(student);
    }

}
