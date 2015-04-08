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
    public void updateStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = getStudentInTest();
        stu.getBasicInfo().setChinese_name("大明");
        Student stu1 = studentService.updateStudent(stu);
        Assert.assertNotNull(stu1);
    }

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCsc_id("csc11000022");

        BasicInfo bf = new BasicInfo();
        bf.setId(1000001L);
        bf.setChinese_name("小明");
        bf.setAnnual(2014);
        bf.setContinent_name("亚洲");
        bf.setCountry_name("棒子国");
        stu.setBasicInfo(bf);

        RegistrationInfo ri = new RegistrationInfo();
        ri.setId(1000001L);
        ri.setSubject("古汉语");
        ri.setTeach_language("阿拉伯语");
        stu.setRegistrationInfo(ri);

        Discuss discuss = new Discuss();
        discuss.setId(1000001L);
        discuss.setSubject("科学");
        stu.setDiscuss(discuss);

        SchoolRoll schoolroll = new SchoolRoll();
        schoolroll.setId(1000001L);
        schoolroll.setScholarship_review_year(2013L);
        schoolroll.setAcademic_certificate_NO("NO000001");
        stu.setSchoolRoll(schoolroll);

        List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
        RelatedAddress ra1 = new RelatedAddress();
        RelatedAddress ra2 = new RelatedAddress();
        ra1.setId(1000001L);
        ra1.setType("Address");
        ra1.setAddress_or_name("北京市海淀区");
        //ra1.setStudent(stu);
        ra2.setId(1000002L);
        ra2.setType("person");
        ra2.setAddress_or_name("张三 18800000000");
        ra2.setNature("personInAccedent");
        //ra2.setStudent(stu);
        relatedAddress.add(ra1);
        relatedAddress.add(ra2);
        stu.setRelatedAddress(relatedAddress);

        ProfilesHistory ph = new ProfilesHistory();
        ph.setId(1000001L);
        ph.setNative_language("英语");
        stu.setProfilesHistory(ph);

        return stu;
    }
}
