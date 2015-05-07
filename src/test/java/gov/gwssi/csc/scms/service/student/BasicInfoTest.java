package gov.gwssi.csc.scms.service.student;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.student.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Murray on 2015/4/2.
 * 学生基本信息单元测试
 */
public class BasicInfoTest extends UnitTestBase {

    Student student;

    @Test
    public void saveBasicInfoTest() {
        StudentService studentService = getBean("studentService");
        student = studentService.saveStudent(getStudentInTest(), null);
        Assert.assertNotNull(student);
    }

    @Test
    public void getBasicInfoByStudentTest() {
        BasicInfoService basicInfoService = getBean("basicInfoService");

        BasicInfo basicInfo = basicInfoService.getBasicInfoByStudentId("2015042829");

        Assert.assertEquals("小白兔", basicInfo.getChineseName());
        Assert.assertNotNull(basicInfo.getStudent());
    }

    @Test
    public void updateBasicInfoTest() {
        BasicInfoService basicInfoService = getBean("basicInfoService");

        BasicInfo basicInfo = basicInfoService.getBasicInfoById("2015050700000000025");

        System.out.println("basicInfo passportName :: "+ basicInfo.getPassportName());

        basicInfo.setChineseName("大灰狼");
        basicInfo.setPassportName("큰바보");
        basicInfoService.updateBasicInfo(basicInfo);
        Assert.assertNotNull(basicInfo);
    }

    private Student getStudentInTest() {
        Student stu = new Student();
        stu.setCscId("CSCBasicInfo");

        BasicInfo bf = new BasicInfo();
        bf.setChineseName("kkk");
        bf.setPassportName("큰바보,ありがとうございます");
        bf.setAnnual(2014);
        bf.setContinent("亚洲");
        bf.setCountry("棒子国");
        stu.setBasicInfo(bf);

        RegistrationInfo ri = new RegistrationInfo();
        ri.setSubject("古汉语");
        ri.setTeachLanguage("CN");
        stu.setRegistrationInfo(ri);

        Discuss discuss = new Discuss();
        discuss.setSubject("科学");
        stu.setDiscuss(discuss);

        SchoolRoll schoolroll = new SchoolRoll();
        schoolroll.setRegisterYear("2013");
        stu.setSchoolRoll(schoolroll);
        schoolroll.setRegisted(false);
        schoolroll.setLeaveChina(false);
        schoolroll.setCurrentUniversity("123");

        ProfilesHistory ph = new ProfilesHistory();
        ph.setNativeLanguage("英语");
        ph.setChnLevel("专业八级");
        stu.setProfilesHistory(ph);

        return stu;
    }
}
