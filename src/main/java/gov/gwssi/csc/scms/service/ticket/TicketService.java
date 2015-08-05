package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.ticket.TicketRepository;
import gov.gwssi.csc.scms.service.BaseService;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.student.StudentService;
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
    private StudentService studentService;
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
        System.out.println("sql=="+sql);
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
        String userType = user.getUserType();
        StringBuilder sb = new StringBuilder();

        sb.append(TicketResultObject.getResultObject());

        String tempSql = " from Student student,BasicInfo basicInfo, SchoolRoll schoolRoll,Ticket ticket " +
                "where student.id = basicInfo.student  " +
                "and student.id = schoolRoll.student   and student.id = ticket.studentId";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "ticket", userType));
        return sb.toString();
    }
    //保存机票管理修改后的值
    @Transactional
    public Ticket saveTicket(Ticket ticket, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        return ticketRepository.save(ticket);
    }

    //根据Ticket Id获取机票信息
    public Ticket getTicketById(String id) {
        return ticketRepository.findOne(id);
    }

    //根据学生id获取机票信息
    public List<Ticket> getTicketByStudentId(String studentId) {
        return ticketRepository.findByStudentId(studentId);
    }

    //增加机票信息
    public Ticket addTicket(Ticket ticket, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        ticket.setId(getBaseDao().getIdBySequence("SEQ_AIRTICKET"));
        return ticketRepository.save(ticket);
    }

    //删除机票信息
    public Ticket deleteTicketById(User user, String ticketId) {
        Ticket ticket = getTicketById(ticketId);
        if (ticket == null)
            return null;

        try {
            Student student = studentService.getStudentById(ticket.getStudentId());
            //记录日志
            List<OperationLog> operationLogs = new ArrayList<OperationLog>();
            OperationLog operationLog = new OperationLog();

            operationLog.setOptType("3");
            operationLog.setModule("在校生学籍管理");
            operationLog.setModuleId("BM003");
            operationLog.setStudentId(student.getId());
            operationLog.setCscId(student.getCscId());
            operationLog.setPassportName(student.getBasicInfo().getPassportName());
            String ticketJsonStr = "{\"id\":\"" + ticket.getId() + "\",\"studentId\":\"" + ticket.getStudentId() + "\",\"type\":\"" + ticket.getType() + "\",\"airLine\":\"" + ticket.getAirLine()
                    + "\",\"ticketNo\":\"" + ticket.getTicketNo() + "\",\"validdate\":\"" + ticket.getValiddate() + "\",\"applyDate\":\"" + ticket.getApplyDate() + "\",\"flightDate\":\"" + ticket.getFlightDate() + "\",\"leaveCity\":" + ticket.getLeaveCity()
                    + "\",\"price\":\"" + ticket.getPrice() + "\",\"state\":\"" + ticket.getState() + "\",\"remark\":\"" + ticket.getRemark() + "\",\"createBy\":\"" + ticket.getCreateBy() + "\",\"created\":\"" + ticket.getCreated()
                    + "\",\"updateBy\":\"" + ticket.getUpdateBy() + "\",\"updated\":\"" + ticket.getUpdated() + "\"}";
            operationLog.setBefore(ticketJsonStr);
            operationLog.setAfter("");
            operationLog.setColumnCH("");
            operationLog.setColumnEN("");
            operationLog.setTableEN("scms_airticket");
            operationLog.setTableCH("机票信息表");
            operationLog.setNodeId(user.getNode().getNodeId());
            operationLog.setNodeType(user.getNode().getNodeType());
            operationLog.setCreateBy(user.getUserId());
            operationLog.setCreateD(new java.util.Date());

            operationLogs.add(operationLog);
            operationLogService.saveOperationLog(operationLogs);
            ticketRepository.delete(ticket);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    }
