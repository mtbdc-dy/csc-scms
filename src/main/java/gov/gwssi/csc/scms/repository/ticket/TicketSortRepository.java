package gov.gwssi.csc.scms.repository.ticket;

import gov.gwssi.csc.scms.domain.ticket.TicketSort;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理接口
 */
public interface TicketSortRepository extends CrudRepository<TicketSort,String> , JpaSpecificationExecutor<TicketSort> {
    List<TicketSort> findByStudentId(String studentId);
}
