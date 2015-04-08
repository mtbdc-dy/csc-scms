package gov.gwssi.csc.scms.service.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.queryfilter.StudentFilterObject;
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
        System.out.println("CSC_ID::" + sfo.getCsc_id());
        Assert.assertEquals("222", sfo.getCsc_id());
        Assert.assertEquals("Jams", sfo.getPassport_name());
        Assert.assertNull(sfo.getContinent_name());
    }

    @Test
    public void saveStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.saveStudent(getStudentInTest());
        Assert.assertNotNull(stu);
    }

    @Test
    public void getStudentByIDTest() {
        StudentService studentService = getBean("studentService");
        Student student = studentService.getStudentByID("csc11000022");
        student.getRelatedAddress();
        Assert.assertNotNull(student);
        System.out.println("BasicInfo ID ::" + student.getBasicInfo().getId());
        System.out.println("BasicInfo ChineseName ::" + student.getBasicInfo().getChinese_name());

        System.out.println("==================");
        List<RelatedAddress> relatedAddress = student.getRelatedAddress();
        for (RelatedAddress ra : relatedAddress)
            System.out.println(ra.getId() + "::" + ra.getAddress_or_name());
    }

    @Test
    public void updateStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.getStudentByID("csc11000023");
        stu.getBasicInfo().setChinese_name("小红");
        Student stu1 = studentService.updateStudent(stu);
        Assert.assertNotNull(stu1);
    }

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCsc_id("csc11000023");

        BasicInfo bf = new BasicInfo();
        bf.setChinese_name("小明");
        bf.setAnnual(2014);
        bf.setContinent_name("亚洲");
        bf.setCountry_name("棒子国");
        stu.setBasicInfo(bf);

        RegistrationInfo ri = new RegistrationInfo();
        ri.setSubject("古汉语");
        ri.setTeach_language("阿拉伯语");
        stu.setRegistrationInfo(ri);

        Discuss discuss = new Discuss();
        discuss.setSubject("科学");
        stu.setDiscuss(discuss);

        SchoolRoll schoolroll = new SchoolRoll();
        schoolroll.setScholarship_review_year(2013L);
        schoolroll.setAcademic_certificate_NO("NO000001");
        stu.setSchoolRoll(schoolroll);

        List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
        RelatedAddress ra1 = new RelatedAddress();
        RelatedAddress ra2 = new RelatedAddress();
        ra1.setType("Address");
        ra1.setAddress_or_name("北京市海淀区");
        //ra1.setStudent(stu);
        ra2.setType("person");
        ra2.setAddress_or_name("张三 18800000000");
        ra2.setNature("personInAccedent");
        //ra2.setStudent(stu);
        relatedAddress.add(ra1);
        relatedAddress.add(ra2);
        stu.setRelatedAddress(relatedAddress);

        ProfilesHistory ph = new ProfilesHistory();
        ph.setNative_language("英语");
        stu.setProfilesHistory(ph);

        return stu;
    }
}
