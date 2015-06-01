package gov.gwssi.csc.scms.service.ticket;

import com.fasterxml.jackson.core.JsonProcessingException;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.student.Student;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by lzs on 2015/6/1.
 */
public class TicketTest extends UnitTestBase {
    @Test
    public void getStudentsListTest() throws JsonProcessingException {
        TicketDAO ticketDAO = super.getBean("ticketDAO");
        List<Student> studentList = ticketDAO.getStudentList(null);
        Assert.assertNotNull(studentList);
        System.out.println("list size::" + studentList.size());
    }
}
