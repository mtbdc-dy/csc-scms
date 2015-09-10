package gov.gwssi.csc.scms.repository.ticket;

import gov.gwssi.csc.scms.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理接口
 */
public interface TicketRepository extends CrudRepository<Ticket,String> , JpaSpecificationExecutor<Ticket> {
    List<Ticket> findByStudentId(String studentId);
}
