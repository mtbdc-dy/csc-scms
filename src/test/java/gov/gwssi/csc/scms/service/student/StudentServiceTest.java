package gov.gwssi.csc.scms.service.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.student.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class StudentServiceTest extends UnitTestBase {

    @Test
    public void getStudentsByConditionsTest() throws JsonProcessingException {

        String body = "{\"csc_id\" : \"222\",\"passport_name\" : \"Jams\"}";
        StudentFilterObject sfo = null;
        try {
            sfo = new ObjectMapper().readValue(body, StudentFilterObject.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("CSC_ID::" + sfo.getCsc_id());
//        Assert.assertEquals("222", sfo.getCsc_id());
//        Assert.assertEquals("Jams", sfo.getPassport_name());
//        Assert.assertNull(sfo.getContinent_name());
    }

    @Test
    public void saveStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.saveStudent(getStudentInTest());
        Assert.assertNotNull(stu);
    }

    @Test
    public void getStudentByScsIdTest() {
        StudentService studentService = getBean("studentService");
        Student student = studentService.getStudentByCscId("csc11000001");
        student.getRelatedAddress();
        Assert.assertNotNull(student);
        System.out.println("BasicInfo ID ::" + student.getBasicInfo().getId());
        System.out.println("BasicInfo ChineseName ::" + student.getBasicInfo().getChineseName());
    }

    @Test
    public void updateStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.getStudentByCscId("csc11000023");
        stu.getBasicInfo().setChineseName("小红");
        Student stu1 = studentService.updateStudent(stu);
        Assert.assertNotNull(stu1);
    }

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCscId("csc11000001");

        BasicInfo bf = new BasicInfo();
        bf.setChineseName("小明");
        bf.setAnnual(2014);
        bf.setContinent("亚洲");
        bf.setCountry("棒子国");
        stu.setBasicInfo(bf);

        RegistrationInfo ri = new RegistrationInfo();
        ri.setSubject("古汉语");
        ri.setTeachLanguage("阿拉伯语");
        stu.setRegistrationInfo(ri);

        Discuss discuss = new Discuss();
        discuss.setSubject("科学");
        stu.setDiscuss(discuss);

        SchoolRoll schoolroll = new SchoolRoll();
        schoolroll.setScholarshipYear(2013L);
        stu.setSchoolRoll(schoolroll);

        ProfilesHistory ph = new ProfilesHistory();
        ph.setNativeLanguage("英语");
        stu.setProfilesHistory(ph);

        List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
        RelatedAddress ra1 = new RelatedAddress();
        RelatedAddress ra2 = new RelatedAddress();
        ra1.setType("Address");
        ra1.setAddressOrName("北京市海淀区");
        ra1.setStudent(stu);
        ra2.setType("person");
        ra2.setAddressOrName("张三 18800000000");
        ra2.setNature("personInAccedent");
        ra2.setStudent(stu);
        relatedAddress.add(ra1);
        relatedAddress.add(ra2);
        stu.setRelatedAddress(relatedAddress);

        return stu;
    }
}
