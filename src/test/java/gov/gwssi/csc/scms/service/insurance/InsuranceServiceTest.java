package gov.gwssi.csc.scms.service.insurance;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.insurance.InsuranceDAO;
import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.insurance.Insurance;
import gov.gwssi.csc.scms.domain.query.InsuranceResultObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.user.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

/**
 * Created by chen on 2015/7/17.
 */
public class InsuranceServiceTest extends UnitTestBase {

    private InsuranceDAO insuranceDAO;
    private TicketDAO ticketDAO;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetInsuranceList() throws Exception {//��������ɱ�����Ϣ��
        InsuranceService insuranceService = super.getBean("insuranceService");
        List<InsuranceResultObject> inResultObjectList = insuranceService.getInsuranceList(null);
        Assert.assertNotNull(inResultObjectList);
        System.out.println("list size::" + inResultObjectList.size());

    }

    @Test
    public void testGetInsuranceListByFilter() throws Exception {
        InsuranceService insuranceService = super.getBean("insuranceService");
        String body = "{\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
        StudentFilterObject ticketResultObject;
        List<InsuranceResultObject> list1 = null;
        try {
            ticketResultObject=new ObjectMapper().readValue(body, StudentFilterObject.class);
            list1 =  insuranceService.getInsuranceListByFilter(ticketResultObject,null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(list1);
    }

    @Test
    public void testticketDAO() throws Exception {

        List exportList = ticketDAO.getStudentList(null);
//        List exportList=ticketDAO.getStudentList(null);
        System.out.println("exportList.size----" + exportList.size());
    }

    @Test
    public void testgetInsuranceAndStu() throws Exception {
        InsuranceService insuranceService = super.getBean("insuranceService");
       InsuranceResultObject inResultObjectList = insuranceService.getInsuranceAndStu("2015072200000000228");
        Assert.assertNotNull(inResultObjectList);
        System.out.println("list size::" + inResultObjectList);
    }


    @Test
    public void testgetInsuranceList() throws Exception {
        InsuranceService insuranceService = super.getBean("insuranceService");
        List<InsuranceResultObject> insuranceResultObjectList = insuranceService.getInsuranceList(null);
        Assert.assertNotNull(insuranceResultObjectList);
        System.out.println("list size::" + insuranceResultObjectList.size());
    }

    @Test
    public void insurancePagingTest(){
        InsuranceService insuranceService = super.getBean("insuranceService");
        UserService userService = getBean(UserService.class);
        User user = userService.getUserByUserId("q0922");
        Filter filter = new Filter();
//        filter.setInsuranceState("AV0001");
        //Page<Insurance> insurancePage = insuranceService.getInsurancesPagingByFilter(filter, 0, 10, "insurance", user);
       // System.out.println(insurancePage);
    }
}