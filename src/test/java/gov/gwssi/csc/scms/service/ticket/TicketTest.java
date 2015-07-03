package gov.gwssi.csc.scms.service.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzs on 2015/6/1.
 */
public class TicketTest extends UnitTestBase {
    @Test
    public void getStudentsListTest() throws JsonProcessingException {
        TicketDAO ticketDAO = super.getBean("ticketDAO");
        List<TicketResultObject> studentList = ticketDAO.getStudentList(null);
        Assert.assertNotNull(studentList);
        System.out.println("list size::" + studentList.size());
    }
    @Test
    public void getTicketListTest(){
        TicketService ticketService = super.getBean("ticketService");
        List<TicketResultObject> ticketResultObjectList = ticketService.getTicketList(null);
        Assert.assertNotNull(ticketResultObjectList);
        System.out.println("list size::" + ticketResultObjectList.size());

    }
    @Test
    public void doStTest(){
        TicketDAO ticketDAO = super.getBean("ticketDAO");
        String name = "p_scms_airticket";
        List list = new ArrayList();
        list.add("cbf");
        ticketDAO.doStatement(name, list);
    }
    @Test
    public void timeTest(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        System.out.println("ts=="+ts);

    }
    @Test
    public void getTicketListByFilterTest() throws JsonProcessingException{
        TicketService ticketService = super.getBean("ticketService");
        String body = "{\"cscId\" : \"11\" ," +
                "\"offSet\" : \"0\" , \"pageSize\" : \"2\"}";
        StudentFilterObject ticketResultObject;
        List<TicketResultObject> list1 = null;
        try {
            ticketResultObject=new ObjectMapper().readValue(body, StudentFilterObject.class);
            list1 =  ticketService.getTicketListByFilter(ticketResultObject,null);

        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(list1);
        if(list1.size()>0) {
            System.out.println("list size::" + list1.size());
            for (TicketResultObject sro : list1) {
                System.out.println("==============================");
                System.out.println("studentId::" + sro.getStudentId());
                System.out.println("CscId::" + sro.getCscId());

            }
        }else{
            System.out.println("no date display");
        }
    }
}
