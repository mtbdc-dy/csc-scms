package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.student.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Murray on 2015/4/2.
 */
public class BasicInfoTest extends UnitTestBase {

    Student student;

    @Test
    public void saveBasicInfoTest() {
        StudentService studentService = getBean("studentService");
        student = studentService.saveStudent(getStudentInTest());
        Assert.assertNotNull(student);
    }

    @Test
    public void getBasicInfoByStudentTest() {
        StudentService studentService = getBean("studentService");
        BasicInfoService basicInfoService = getBean("basicInfoService");

        Student stu = studentService.getStudentByCscId("CSCBasicInfo");
        BasicInfo basicInfo = basicInfoService.getBasicInfoByStudentId(stu.getId());

        Assert.assertEquals("小白兔", basicInfo.getChineseName());
    }

    @Test
    public void getStudentByBasicInfo() {
        BasicInfoService basicInfoService = getBean("basicInfoService");

        BasicInfo basicInfo = basicInfoService.getBasicInfoById(1L);
        Assert.assertNotNull(basicInfo);

        student = basicInfo.getStudent();
        Assert.assertNotNull(student);
    }

    @Test
    public void updateBasicInfoTest() {
        BasicInfoService basicInfoService = getBean("basicInfoService");

        BasicInfo basicInfo = basicInfoService.getBasicInfoById(1L);
        basicInfo.setChineseName("大灰狼");
        basicInfoService.updateBasicInfo(basicInfo);
        Assert.assertNotNull(basicInfo);
    }

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCscId("CSCBasicInfo");

        BasicInfo bf = new BasicInfo();
        bf.setChineseName("小白兔");
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
        ph.setChnLevel("专业八级");
        stu.setProfilesHistory(ph);

        return stu;
    }
}
