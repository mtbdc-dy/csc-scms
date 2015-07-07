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

import java.util.ArrayList;
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
    //生成机票管理清单
    public List<TicketResultObject> getTicketList(User user) {
        List ticketList;
        List listParameter = new ArrayList();
        List<TicketResultObject> ticketResultObjectList;
        Ticket ticket = new Ticket();
        listParameter.add(user.getUserId());
        ticketDAO.doSt("p_scms_airticket",listParameter);//调用存储生成当年需要预定的机票记录
        int startPosition, pageSize;

        String sql = getSql(user);
        if (sql == null) {
            return null;
        }


            startPosition =FilterObject.OFFSETDEFULT;
            pageSize =FilterObject.PAGESIZEDEFULT;


        ticketResultObjectList = super.getBaseDao().getObjectListByHQL(sql, TicketResultObject.class, startPosition, pageSize);
        for(int i = 0;i<ticketResultObjectList.size();i++){
            TicketResultObject ticketResultObject = ticketResultObjectList.get(i);
            System.out.println("价格==="+ticketResultObject.getPrice());
        }
        return ticketResultObjectList;

    }
    //查询获取机票管理列表
    public List<TicketResultObject> getTicketListByFilter(FilterObject filterObject,User user) {

        List<TicketResultObject> ticketResultObjectList,ticketResultObjectNewList = null;



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

//        if(ticketResultObjectList.size()>0){
//           for(int i = 0;i<ticketResultObjectList.size();i++){
//               TicketResultObject ticketResultObject = ticketResultObjectList.get(i);
//               ticketResultObject.setMod(true);
//               ticketResultObjectNewList.add(ticketResultObject);
//           }
//       }
        return ticketResultObjectList;


    }
    //获取当前用户下的机票管理对应的字段数据 不加查询条件的sql
    private String getSql(User user) {
        StringBuilder sb = new StringBuilder();
        StudentFilterObject filterObject = new StudentFilterObject();
        sb.append(TicketResultObject.getResultObject());

        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Ticket ticket " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = ticket.studentId";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getUserFilter(user));
        return sb.toString();
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
    //保存机票管理修改后的值
    @Transactional
    public Ticket saveTicket(Ticket ticket, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        return ticketRepository.save(ticket);
    }





    }
