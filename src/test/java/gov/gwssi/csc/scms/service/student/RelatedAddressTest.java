package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.student.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class RelatedAddressTest extends UnitTestBase {

    Student student;

    @Test
    public void saveRelatedAddressTest() {
        StudentService studentService = getBean("studentService");
        student = studentService.saveStudent(getStudentInTest(),null);
        Assert.assertNotNull(student);
    }

    @Test
    public void getRelatedAddressByStudentTest() {
        RelatedAddressService relatedAddressService = getBean("relatedAddressService");

        List<RelatedAddress> relatedAddress = relatedAddressService.getRelatedAddressByStudentId(1L);
        Assert.assertNotNull(relatedAddress);

        for(RelatedAddress ra : relatedAddress){
            System.out.println(ra.getAddressOrName());
        }
    }

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCscId("CSCRealted01");

        BasicInfo bf = new BasicInfo();
        bf.setChineseName("灰姑娘");
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
        schoolroll.setRegisterYear("2013");
        stu.setSchoolRoll(schoolroll);

        ProfilesHistory ph = new ProfilesHistory();
        ph.setNativeLanguage("英语");
        ph.setChnLevel("专业八级");
        stu.setProfilesHistory(ph);

        RelatedAddress ra1 = new RelatedAddress();
        ra1.setAddressOrName("this for address");
        ra1.setStudent(stu);
        RelatedAddress ra2 = new RelatedAddress();
        ra2.setAddressOrName("this for name");
        ra2.setStudent(stu);

        List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
        relatedAddress.add(ra1);
        relatedAddress.add(ra2);
        stu.setRelatedAddress(relatedAddress);

        return stu;
    }
}
