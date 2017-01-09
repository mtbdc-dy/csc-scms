package gov.gwssi.csc.scms.repository.ticket;

import gov.gwssi.csc.scms.domain.ticket.Ticket;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理接口
 */
public interface TicketRepository extends CrudRepository<Ticket,String> , JpaSpecificationExecutor<Ticket> {
    List<Ticket> findByStudentId(String studentId);
    List<Ticket> findByStudentIdAndStateNot(String studentId,String state);
    @Modifying
    @Query(value = "update Ticket ticket set ticket.pervalidDate = :pervalidDate where ticket.id = :id")
    int updateTicket(@Param("pervalidDate")Date pervalidDate, @Param("id")String id);
}
