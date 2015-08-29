package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import org.junit.Test;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * Created by tianj on 2015/8/29.
 */
public class TicketServiceTest extends UnitTestBase {

    @Test
    public void testGetTicketsPagingByFilter() throws Exception {
        TicketService ticketService = getBean(TicketService.class);
        Filter filter = new Filter();
        filter.setTicketState("AT0001");
        Page<Ticket> ticketPage = ticketService.getTicketsPagingByFilter(filter);
        Page<Map<String, Object>> mapPage = ticketPage.map(new TicketConverter());
        System.out.println("mapPage = " + mapPage);
    }
}