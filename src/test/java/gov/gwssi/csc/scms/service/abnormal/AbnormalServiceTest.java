 package gov.gwssi.csc.scms.service.abnormal;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;

import gov.gwssi.csc.scms.domain.abnormal.Abnormal;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.*;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

 /**
 * Created by lzs on 2015/4/27.
 */
public class AbnormalServiceTest  extends UnitTestBase {
    @Test
    public void getAbnormalAllTest() {
        AbnormalService abnormalService = getBean("abnormalService");
        String body = "{\"cscId\" : \"11\" ," +
                "\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
        StudentFilterObject abnormalResultObject;
        List<AbnormalResultObject> list1 = null;
        try {
            abnormalResultObject=new ObjectMapper().readValue(body, StudentFilterObject.class);
            list1 =  abnormalService.getAbnormalsByFilter(abnormalResultObject,null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(list1);
        if(list1.size()>0) {
            System.out.println("list size::" + list1.size());
            for (AbnormalResultObject sro : list1) {
                System.out.println("==============================");
                System.out.println("studentId::" + sro.getStudentId());
                System.out.println("CscId::" + sro.getCscId());

            }
        }else{
            System.out.println("no date display");
        }
    }
//     @Test
//     public void getAllStudentTest() {
//         AbnormalService abnormalService = getBean("abnormalService");
//         String body = "{\"cscId\" : \"1\" ," +
//                 "\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
//         StudentFilterObject studentResultObject;
//         List<AddStudentResultObject> list1 = null;
//         try {
//             studentResultObject=new ObjectMapper().readValue(body, StudentFilterObject.class);
//             list1 =  abnormalService.getAddStudentsByFilter(studentResultObject, null);
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//         Assert.assertNotNull(list1);
//         System.out.println("list size::" + list1.size());
//         for (AddStudentResultObject sro : list1) {
//             System.out.println("==============================");
//             System.out.println("studentId::" + sro.getStudentId());
//             System.out.println("CscId::" + sro.getCscId());
//
//         }
//     }
     @Test
     public void saveAbnormalTest() {
         AbnormalService abnormalService = getBean("abnormalService");
         Abnormal ab = abnormalService.saveAbnormal(getAbnormalInTest(), getLogList());
         Assert.assertNotNull(ab);
     }
     @Test
     public void updateAbnormalTest() {
         AbnormalService abnormalService = getBean("abnormalService");
         Abnormal ab = abnormalService.getAbnormalById("2015051500000000023");
         ab.setState("1");
         Abnormal abc = abnormalService.updateAbnormal(ab, getLogList());
         Assert.assertNotNull(abc);
     }
     @Test
     public void delAbnormalTest() {
         AbnormalService abnormalService = getBean("abnormalService");
         Abnormal ab = abnormalService.deleteAbnormalById("15", getLogList());
        // Assert.assertNotNull(ab);
        // Assert.assertNull(ab);
     }
     private Abnormal getAbnormalInTest() {

         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         Date date = null;
         try {
             date = sdf.parse("2016-02-02 00:00:00");
         } catch (ParseException e) {
             e.printStackTrace();
         }

//         Student stu = new Student();
         StudentService studentService = getBean("studentService");
         Student student = studentService.getStudentByCscId("15");
         Abnormal ab = new Abnormal();
//         stu.setCscId("csc11000001");
        // ab.setStudent(student);
         ab.setState("0");

         return ab;
     }
     private List<OperationLog> getLogList() {
         List<OperationLog> list = new ArrayList<OperationLog>();

         OperationLog op1 = new OperationLog();
         op1.setMenu("在校生管理");
         op1.setTableEN("basicInfo");
         //op1.setColunmEN("passportName");
         op1.setBefore("beForeName");
         op1.setAfter("afterName");
         op1.setStudentId("1");
         op1.setOptType("1");
         op1.setMenuId("1");
         op1.setNodeId("2");
         list.add(op1);



         return list;
     }
}
