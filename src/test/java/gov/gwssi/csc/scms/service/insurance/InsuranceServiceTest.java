package gov.gwssi.csc.scms.service.insurance;

import gov.gwssi.csc.scms.base.UnitTestBase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.dao.insurance.InsuranceDAO;
import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.InsuranceResultObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.service.ticket.TicketService;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by chen on 2015/7/17.
 */
public class InsuranceServiceTest extends UnitTestBase {

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
    private InsuranceDAO insuranceDAO;
    private TicketDAO ticketDAO;
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
}