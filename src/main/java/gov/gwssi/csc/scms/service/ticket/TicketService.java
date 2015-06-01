package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.repository.ticket.TicketRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理服务类
 */
@Service("ticketService")
public class TicketService extends BaseService {
    @Autowired
    @Qualifier("ticketRepository")
    private TicketRepository ticketRepository;
    @Autowired
    private OperationLogService operationLogService;




}
