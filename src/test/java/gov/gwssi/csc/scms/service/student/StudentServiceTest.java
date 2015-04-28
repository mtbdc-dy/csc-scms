package gov.gwssi.csc.scms.service.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by Murray on 2015/4/2.
 */
public class StudentServiceTest extends UnitTestBase {

    @Test
    public void getStudentsByFilterTest() throws JsonProcessingException {

        StudentService studentService = getBean("studentService");
        String body = "{\"cscId\" : \"csc11000001\",\"passportName\" : \"null\"," +
                "\"planLeaveDateBegin\" : \"\" , \"planLeaveDateEnd\" : \"null\"," +
                "\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
        StudentFilterObject sfo;
        List<StudentResultObject> stus = null;
        try {
            sfo = new ObjectMapper().readValue(body, StudentFilterObject.class);
            stus = studentService.getStudentsByFilter(sfo);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(stus);
        System.out.println("list size::" + stus.size());

        for (StudentResultObject sro : stus) {
            System.out.println("==============================");
            System.out.println("studentId::" + sro.getStudentId());
            System.out.println("CscId::" + sro.getCscId());
            System.out.println("planLeaveDate::" + sro.getPlanLeaveDate());
        }
    }

    @Test
    public void getCountByFilterTest() throws JsonProcessingException {
        StudentService studentService = getBean("studentService");
        String body = "{\"cscId\" : \"csc11000001\",\"passportName\" : \"null\"," +
                "\"planLeaveDateBegin\" : \"null\" , \"planLeaveDateEnd\" : \"2016-09-09 \"}";
        StudentFilterObject sfo;
        int count = 0;
        try {
            sfo = new ObjectMapper().readValue(body, StudentFilterObject.class);
            count = studentService.getCountByQueryFilter(sfo);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("count::" + count);
    }

    @Test
    public void saveStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.saveStudent(getStudentInTest(), null);
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
        System.out.println("RelatedAddress personOrAddress :: " + student.getRelatedAddress().get(0).getAddressOrName());
    }

    @Test
    public void updateStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.getStudentByCscId("csc11000023");
        stu.getBasicInfo().setChineseName("小红");
        Student stu1 = studentService.saveStudent(stu, null);
        Assert.assertNotNull(stu1);
    }

    private Student getStudentInTest() {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse("2016-02-02 00:00:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Student stu = new Student();
        stu.setCscId("csc11000001");

        BasicInfo bf = new BasicInfo();
        bf.setChineseName("阿拉丁");
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
        schoolroll.setPlanLeaveDate(date);
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
