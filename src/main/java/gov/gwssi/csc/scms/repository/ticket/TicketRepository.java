package gov.gwssi.csc.scms.repository.ticket;

import gov.gwssi.csc.scms.domain.ticket.Ticket;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理接口
 */
public interface TicketRepository extends CrudRepository<Ticket,String> {
}
