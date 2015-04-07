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
import java.util.Arrays;
import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class StudentServiceTest extends UnitTestBase {

    //StudentService studentService = getBean(StudentService.class);

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

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCsc_id("0000001");

        BasicInfo bf = new BasicInfo();
        bf.setId(1000000L);
        bf.setChinese_name("小明");
        bf.setAnnual(2014);
        bf.setContinent_name("亚洲");
        bf.setCountry_name("棒子国");
        stu.setBasicInfo(bf);

        RegistrationInfo ri = new RegistrationInfo();
        ri.setId(1000000L);
        ri.setSubject("古汉语");
        ri.setTeach_language("阿拉伯语");
        stu.setRegistrationInfo(ri);

        Discuss discuss = new Discuss();
        discuss.setId(122222L);
        discuss.setSubject("科学");

        SchoolRoll schoolroll = new SchoolRoll();
        schoolroll.setId(100000L);
        schoolroll.setScholarship_review_year(2013L);
        schoolroll.setAcademic_certificate_NO("NO000001");

        List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
        RelatedAddress ra1 = new RelatedAddress();
        ra1.setId(1000000L);
        ra1.setType("Address");
        ra1.setAddress_or_name("北京市海淀区");
        relatedAddress.add(ra1);

        RelatedAddress ra2 = new RelatedAddress();
        ra2.setId(1000001L);
        ra2.setType("person");
        ra2.setAddress_or_name("张三 18800000000");
        ra2.setNature("personInAccedent");
        relatedAddress.add(ra2);

        stu.setRelatedAddress(relatedAddress);

        stu.setAccident(null);
        return stu;
    }
}
