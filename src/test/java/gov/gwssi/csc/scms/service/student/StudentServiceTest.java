package gov.gwssi.csc.scms.service.student;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            stus = studentService.getStudentsByFilter(sfo, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(stus);
        System.out.println("list size::" + stus.size());

        for (StudentResultObject sro : stus) {
            System.out.println("==============================");
            System.out.println("studentId::" + sro.getId());
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
            count = studentService.getCountByQueryFilter(sfo, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("count::" + count);
    }

    @Test
    public void saveStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.saveStudent(getStudentInTest(), getLogList());
        Assert.assertNotNull(stu);

    }

    @Test
    public void getStudentByScsIdTest() {
        StudentService studentService = getBean("studentService");
        Student student = studentService.getStudentByCscId("csc11000001");
        Assert.assertNotNull(student);

        ObjectMapper mp = new ObjectMapper();
        String json = null;
        try {
            json = mp.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("ERROR======");
        }
        System.out.println("Json :" + json);
    }

    @Test
    public void getStudentByIdTest() throws Exception {
        StudentService studentService = getBean("studentService");
        Student student = studentService.getStudentById("2015061600000000341");
        Assert.assertNotNull(student);

        ObjectMapper mp = new ObjectMapper();
        String json = null;
        try {
            json = mp.writeValueAsString(student);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            System.out.println("ERROR======");
        }
        System.out.println("Json :" + json);
    }

    @Test
    public void updateStudentTest() {
        StudentService studentService = getBean("studentService");
        Student stu = studentService.getStudentByCscId("csc11000023");
        stu.getBasicInfo().setChineseName("小红");
        Student stu1 = studentService.saveStudent(stu, null);
        Assert.assertNotNull(stu1);
    }

//    @Test
//    public void updateGroupByName() throws Exception {
//        StudentService studentService = getBean("studentService");
//        String id="2";
//        String body = "{\"value\":\"{\\\"registerState\\\":\\\"123\\\"}\",\"user\":\"11111111\",\"log\":\"[]\"}";
//        String group = "shoolRoll";
//        ObjectMapper mapper = new ObjectMapper();
//        JsonBody jbosy = new ObjectMapper().readValue(body, JsonBody.class);
//        //Json转成对象 包含修改后的信息
//        Object groupObj = updateStudentGroup(group, jbosy.getValue());
//
//        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
//        List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
//        groupObj = studentService.updateGroupByName(id, group, groupObj, operationLogs);
//    }

    private Object updateStudentGroup(String group, String body) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Object groupObject = null;

        if ("basicInfo".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, BasicInfo.class);
        }
        if ("schoolRoll".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, SchoolRoll.class);
        }
        if ("registrationInfo".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, RegistrationInfo.class);
        }
        if ("profilesHistory".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, ProfilesHistory.class);
        }
        if ("discuss".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, Discuss.class);
        }
        if ("schoolfellow".equalsIgnoreCase(group)) {
            groupObject = mapper.readValue(body, Schoolfellow.class);
        }
        if ("accident".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Accident.class);
            groupObject = mapper.readValue(body, javaType);
        }
        if ("relatedAddress".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, RelatedAddress.class);
            groupObject = mapper.readValue(body, javaType);
        }
        if ("grade".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Grade.class);
            groupObject = mapper.readValue(body, javaType);
        }
        if ("gradeAttachment".equalsIgnoreCase(group)) {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, GradeAttachment.class);
            groupObject = mapper.readValue(body, javaType);
        }
        return groupObject;
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
        ri.setTeachLanguage("EN");
        stu.setRegistrationInfo(ri);

        Discuss discuss = new Discuss();
        discuss.setSubject("科学");
        stu.setDiscuss(discuss);

        SchoolRoll schoolroll = new SchoolRoll();
        schoolroll.setRegisterYear(2012);
        schoolroll.setPlanLeaveDate(date);
        schoolroll.setCurrentUniversity("ET001");
        schoolroll.setLeaveChina("0");
        stu.setSchoolRoll(schoolroll);

        ProfilesHistory ph = new ProfilesHistory();
        ph.setNativeLanguage("英语");
        stu.setProfilesHistory(ph);

        List<RelatedAddress> relatedAddress = new ArrayList<RelatedAddress>();
        RelatedAddress ra1 = new RelatedAddress();
        RelatedAddress ra2 = new RelatedAddress();
        ra1.setType("Add");
        ra1.setAddressOrName("北京市海淀区");
        ra1.setStudent(stu);
        ra2.setType("per");
        ra2.setAddressOrName("张三 18800000000");
        ra2.setNature("personInAccedent");
        ra2.setStudent(stu);
        relatedAddress.add(ra1);
        relatedAddress.add(ra2);
        stu.setRelatedAddress(relatedAddress);

        return stu;
    }

    private List<OperationLog> getLogList() {
        List<OperationLog> list = new ArrayList<OperationLog>();

        OperationLog op1 = new OperationLog();
        op1.setModule("在校生管理");
        op1.setTableEN("basicInfo");
        //op1.setColunmEN("passportName");
        op1.setBefore("beForeName");
        op1.setAfter("afterName");
        op1.setStudentId("2005042828");
        list.add(op1);

        OperationLog op2 = new OperationLog();
        op2.setModule("离校生管理");
        op2.setTableEN("relatedAddress");
        //op2.setColunmEN("personName");
        op2.setBefore("beForeAddress");
        op2.setAfter("afterAddress");
        op2.setStudentId("2005042828");
        list.add(op2);

        return list;
    }

    @Test
    public void testGetStudentById() throws Exception {
        StudentService studentService = getBean("studentService");
        Date a = new Date();
        Student student = studentService.getStudentById("2015061600000000341");
        Date b = new Date();
        System.out.println("costs: " + (b.getTime() - a.getTime()) + "ms.");
    }
}
