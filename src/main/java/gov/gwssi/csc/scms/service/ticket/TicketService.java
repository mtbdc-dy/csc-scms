package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.dao.ticket.TicketDAO;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.FilterObject;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.ticket.TicketSort;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.ticket.TicketRepository;
import gov.gwssi.csc.scms.repository.ticket.TicketSortRepository;
import gov.gwssi.csc.scms.service.log.OperationLogService;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理服务类
 */
@Service("ticketService")
public class TicketService extends TicketSortSpecs {
    @Autowired
    @Qualifier("ticketRepository")
    private TicketRepository ticketRepository;
    @Autowired
    @Qualifier("ticketSortRepository")
    private TicketSortRepository ticketSortRepository;
    @Autowired
    private OperationLogService operationLogService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private BaseDAO baseDAO;

    //生成机票管理清单
    public List<TicketResultObject> getTicketList(User user) {
        List ticketList;
        List listParameter = new ArrayList();
        List<TicketResultObject> ticketResultObjectList;
        Ticket ticket = new Ticket();
        listParameter.add(user.getUserId());
        ticketDAO.doSt("p_scms_airticket", listParameter);//调用存储生成当年需要预定的机票记录
        int startPosition, pageSize;

        String sql = getSql(user);
        if (sql == null) {
            return null;
        }


        startPosition = FilterObject.OFFSETDEFULT;
        pageSize = FilterObject.PAGESIZEDEFULT;


        ticketResultObjectList = super.getBaseDao().getObjectListByHQL(sql, TicketResultObject.class, startPosition, pageSize);
        for (int i = 0; i < ticketResultObjectList.size(); i++) {
            TicketResultObject ticketResultObject = ticketResultObjectList.get(i);
            System.out.println("价格===" + ticketResultObject.getPrice());
        }
        return ticketResultObjectList;

    }

    //查询获取机票管理列表
    public List<TicketResultObject> getTicketListByFilter(FilterObject filterObject, User user) {

        List<TicketResultObject> ticketResultObjectList, ticketResultObjectNewList = null;


        int startPosition, pageSize;

        String sql = getSqlByBody(filterObject, user);
        System.out.println("sql==" + sql);
        if (sql == null) {
            return null;
        }

        try {
            startPosition = Integer.parseInt(filterObject.getOffSet());
            pageSize = Integer.parseInt(filterObject.getPageSize());
        } catch (NumberFormatException ne) {
            ne.printStackTrace();
            startPosition = FilterObject.OFFSETDEFULT;
            pageSize = FilterObject.PAGESIZEDEFULT;
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
                "and student.id = schoolRoll.student   and student.id = ticket.student.id";
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
                " where student.id = basicInfo.student  " +
                " and student.id = schoolRoll.student   and student.id = ticket.student.id";
        sb.append(tempSql);

        sb.append(new StudentFilter((StudentFilterObject) filterObject).getFilter(user, "ticket", userType));
        return sb.toString();
    }

    //保存机票管理修改后的值
    @Transactional
    public Ticket saveTicket(Ticket ticket, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        Ticket retTicket = ticketRepository.save(ticket);
        return retTicket;
    }

    @Transactional
    public List<Ticket> updateTicket(List<Ticket> tickets, User user) {
        Ticket ticketInDB;
        List<Ticket> list = new ArrayList<Ticket>();
        for (Ticket ticket : tickets) {
            ticketInDB = ticketRepository.findOne(ticket.getId());
            ticketInDB.setUpdateBy(user.getId());
            ticketInDB.setUpdated(new Date());
            ticketInDB.setValiddate(ticket.getValiddate());
            ticketInDB.setPervalidDate(ticket.getPervalidDate());
            ticketInDB.setApplyDate(ticket.getApplyDate());
            ticketInDB.setLeaveCity(ticket.getLeaveCity());
            ticketInDB.setRemark(ticket.getRemark());
            list.add(ticketInDB);
        }
        return list;
    }

    //根据Ticket Id获取机票信息
    public Ticket getTicketById(String id) {
        return ticketRepository.findOne(id);
    }

    //根据学生id获取机票信息
    public List<Ticket> getTicketByStudentId(String studentId) {
        List<Ticket> tickets = ticketRepository.findByStudentId(studentId);
        for (int i = 0; i < tickets.size(); i++) {
            tickets.get(i).setStudent(null);
        }
        return tickets;
    }

    //增加机票信息
    public Ticket addTicket(Ticket ticket, List<OperationLog> operationLogs) {
        //记录日志
        operationLogService.saveOperationLog(operationLogs);
        ticket.setId(getBaseDao().getIdBySequence("SEQ_AIRTICKET"));
        return ticketRepository.save(ticket);
    }

    //删除机票信息
    public Ticket deleteTicketById(User user, String ticketId, String studentId) {
        Ticket ticket = getTicketById(ticketId);
        if (ticket == null)
            return null;

        try {
            Student student = studentService.getStudentById(studentId);
            //记录日志
            List<OperationLog> operationLogs = new ArrayList<OperationLog>();
            OperationLog operationLog = new OperationLog();

            operationLog.setOptType("3");
            operationLog.setModule("在校生学籍管理");
            operationLog.setModuleId("BG0003");
            operationLog.setStudentId(student.getId());
            operationLog.setCscId(student.getCscId());
            operationLog.setPassportName(student.getBasicInfo().getPassportName());
            String before = ticket.getValiddate() + "/" + ticket.getApplyDate() + "/" + ticket.getLeaveCity() + "/" + baseDAO.getNameCHByTranslateId(ticket.getType()) + "/" + ticket.getFlightDate() + "/" + ticket.getAirLine() + "/" + ticket.getPrice() + "/" + ticket.getTicketNo();
            operationLog.setBefore(before);
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
            ticket.setStudent(null);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //修改机票State为已导出
    public void updateTicketState(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            Ticket ticket = ticketRepository.findOne(ids[i]);
            if("AT0002".equals(ticket.getState())){
                ticket.setState("AT0005");
                ticketRepository.save(ticket);
            }
        }
    }

    //分页查询
//    @Transactional
//    public Page<Ticket> getTicketsPagingByFilter(Filter filter, Integer page, Integer size, String mode, String header) {
//        try {
//            User user = userService.getUserByJWT(header);
//            Specification<Ticket> specA = filterIsLike(filter, user);
//            Specification<Ticket> specB = userIs(user);
//            return ticketRepository.findAll(where(specA).and(specB), new PageRequest(page, size));
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

    //分页查询 增加院校排序
    @Transactional
    public Page<TicketSort> getTicketsPagingByFilterSort(Filter filter, Integer page, Integer size, String mode, String header) {
        try {
            User user = userService.getUserByJWT(header);
            Specification<TicketSort> specA = filterIsLike(filter, user);
            Specification<TicketSort> specB = userIs(user);
            return ticketSortRepository.findAll(where(specA).and(specB), new PageRequest(page, size, Sort.Direction.ASC,"customSort"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public String[] getAllTicketsByFilter(Filter filter,String header) {
        List<TicketSort> ticketSorts;
        try {
            User user = userService.getUserByJWT(header);
            Specification<TicketSort> specA = filterIsLike(filter, user);
            Specification<TicketSort> specB = userIs(user);
            ticketSorts = ticketSortRepository.findAll(where(specA).and(specB));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        String result[]=new String[ticketSorts.size()];
        for(int i=0;i<ticketSorts.size();i++){
            String id = ticketSorts.get(i).getId();
            result[i] = id;
        }
        return result;
    }

    //生成机票管理清单
    public String getStNo(User user) {
        List listParameter = new ArrayList();
        listParameter.add(user.getUserId());
        String no = getBaseDao().doStatementForRtn("p_scms_airticket", listParameter);//调用存储生成当年需要预定的机票记录
        return no;
    }

    @Transactional
    public Map<String,Long> getTicketsStateSum(String header,Filter filter){
        long zs = 0;
        long wtj = 0;
        long ytj = 0;
        long yfk = 0;
        long jjwwdc = 0;
        long jjwydc = 0;
        try {
            User user = userService.getUserByJWT(header);
            Specification<TicketSort> specA = filterIsLike(filter, user);
            Specification<TicketSort> specB = userIs(user);
            zs = ticketSortRepository.count(where(specA).and(specB));
            wtj = ticketSortRepository.count(where(specA).and(specB).and(stateIs("AT0001")));
            yfk = ticketSortRepository.count(where(specA).and(specB).and(stateIs("AT0003")));
            jjwwdc = ticketSortRepository.count(where(specA).and(specB).and(stateIs("AT0002")));
            jjwydc = ticketSortRepository.count(where(specA).and(specB).and(stateIs("AT0005")));
            ytj = jjwwdc + jjwydc;

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Map<String, Long> result = new HashMap<String, Long>();
        result.put("zs", zs);
        result.put("wtj", wtj);
        result.put("ytj", ytj);
        result.put("yfk", yfk);
        result.put("jjwwdc", jjwwdc);
        result.put("jjwydc", jjwydc);
        return result;
    }

}
