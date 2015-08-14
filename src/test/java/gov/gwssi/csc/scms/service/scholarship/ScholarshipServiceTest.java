package gov.gwssi.csc.scms.service.scholarship;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.query.ScholarshipXResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by chen on 2015/7/17.
 */
public class ScholarshipServiceTest extends UnitTestBase {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }



    @Test
    public void testsaveScholarshipDetail() throws Exception {
        ScholarshipXService scholarshipXService = super.getBean("scholarshipXService");

        ScholarshipXResultObject inResultObjectList = scholarshipXService.getScholarshipXAndStu("2015073000000000031");
        Assert.assertNotNull(inResultObjectList);
        System.out.println("list size::" + inResultObjectList);
    }
    @Test
    public void testgetScholarshipXListByFilter() throws Exception {
        ScholarshipXService scholarshipXService = super.getBean("scholarshipXService");
        String body = "{\"schReview\" : \"AV0001\" ," +
                "\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
        StudentFilterObject ticketResultObject;
        List<ScholarshipXResultObject> list1 = null;
        try {
            ticketResultObject=new ObjectMapper().readValue(body, StudentFilterObject.class);
            list1 =  scholarshipXService.getScholarshipXListByFilter(ticketResultObject, null);
            System.out.println("list size::" + list1.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(list1);
    }
//    private ScholarshipXDAO scholarshipXDAO;
//    private TicketDAO ticketDAO;
//    @Test
//    public void testgetScholarshipXAndStu() throws Exception {
//        ScholarshipXService scholarshipXService = super.getBean("scholarshipXService");
//       ScholarshipXResultObject inResultObjectList = scholarshipXService.getScholarshipXAndStu("2015072200000000228");
//        Assert.assertNotNull(inResultObjectList);
//        System.out.println("list size::" + inResultObjectList);
//    }
//
//
//    @Test
//    public void testgetScholarshipXList() throws Exception {
//        ScholarshipXService scholarshipXService = super.getBean("scholarshipXService");
//        List<ScholarshipXResultObject> scholarshipXResultObjectList = scholarshipXService.getScholarshipXList(null);
//        Assert.assertNotNull(scholarshipXResultObjectList);
//        System.out.println("list size::" + scholarshipXResultObjectList.size());
//    }
}