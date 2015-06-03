package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.ticket.TicketRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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
    @Autowired
    private TicketDAO ticketDAO;
    public List<TicketResultObject> getTicketListByFilter(FilterObject filterObject,User user) {
        List ticketList;
        List<TicketResultObject> ticketResultObjectList;
        TicketResultObject ticketResultObject = null;
        Ticket ticket = new Ticket();
        ticketList = ticketDAO.getStudentList(user);
        for(int i=0;i<ticketList.size();i++){
            //ticketResultObject = (TicketResultObject) ticketList.get(i);
            HashMap hashMap = (HashMap) ticketList.get(i);
            ticket.setState("1");
           // ticket.setId(getBaseDao().getIdBySequence("SEQ_AIRTICKET"));
            //ticket.setStudentId(ticketResultObject.getId());
            ticket.setStudentId((String) hashMap.get("ID"));
            ticket.setType((String) hashMap.get("TRAVELTYPE"));
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            ticket.setCreated(ts);
            saveTicket(ticket, null);
        }
        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        if (sql == null) {
            return null;
        }

        try {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            startPosition =FilterObject.OFFSETDEFULT;
            pageSize =FilterObject.PAGESIZEDEFULT;
        }

        ticketResultObjectList = super.getBaseDao().getObjectListByHQL(sql, TicketResultObject.class, startPosition, pageSize);
        return ticketResultObjectList;


    }
    //获取机票管理列表对应的字段数据
    private String getSqlByBody(FilterObject filterObject, User user) {
        if (filterObject == null)
            return null;

        StringBuilder sb = new StringBuilder();

        sb.append(TicketResultObject.getResultObject());

        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Ticket ticket " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = ticket.studentId";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user));
        return sb.toString();
    }
    //保存机票管理记录
    @Transactional
    public void saveTicket(Ticket ticket, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        ticket.setId(getBaseDao().getIdBySequence("SEQ_AIRTICKET"));
         ticketRepository.save(ticket);
    }





    }
