package gov.gwssi.csc.scms.service.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
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
    public void getTicketListTest() throws JsonProcessingException{
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
