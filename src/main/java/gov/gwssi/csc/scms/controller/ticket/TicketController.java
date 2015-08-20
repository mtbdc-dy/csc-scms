package gov.gwssi.csc.scms.controller.ticket;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.ticket.NoSuchTicketException;
import gov.gwssi.csc.scms.service.ticket.TicketService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzs on 2015/5/29.
 * 机票管理控制器
 */
@RestController
@RequestMapping(value = "/ticket")
public class TicketController {
    @Autowired
    private UserService userService;
    @Autowired
    private TicketService ticketService;

    @Autowired
    private StudentService studentService;
    //学校用户在前台点击生成机票管理列表，返回列表
    @RequestMapping(value = "/new",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<TicketResultObject> getTickets(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) throws NoSuchUserException {
        User user = null;
        try {
            user = userService.getUserByJWT(header);
        } catch (RequestHeaderError requestHeaderError) {
            requestHeaderError.printStackTrace();
        } catch (UserIdentityError userIdentityError) {
            userIdentityError.printStackTrace();
        }
        List<TicketResultObject> ticketResultObjectList = ticketService.getTicketList(user);
        return ticketResultObjectList;
    }
    //学校用户在前台点击查询，返回列表
            @RequestMapping(value = "/select",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
            public List<TicketResultObject> getTicketsByConditions(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                                                                   @RequestParam(value = "filter") String filter) throws NoSuchUserException {
                try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

//            User user = userService.getUserByUserIdAndEnable(userId, User.ENABLE);
//            if (user == null) {
//                throw new NoSuchUserException(userId);
//            }
                    User user = userService.getUserByJWT(header);
                    String userid = user.getUserId();
            //按照分页（默认）要求，返回列表内容
            List<TicketResultObject> ticketResultObjects = ticketService.getTicketListByFilter(sfo,user);
            return ticketResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
                    throw new RuntimeException(uee);
        } catch (IOException e) {
            e.printStackTrace();
                    throw new RuntimeException(e);
        } catch (UserIdentityError userIdentityError) {
                    userIdentityError.printStackTrace();
                    throw new RuntimeException(userIdentityError);
                } catch (RequestHeaderError requestHeaderError) {
                    requestHeaderError.printStackTrace();
                    throw new RuntimeException(requestHeaderError);
                }
//        catch (NoSuchUserException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
    //修改机票管理
    @RequestMapping(value = "/save",method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public List<Ticket> modTicket(@RequestBody String ticketJson,@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Ticket.class);
            List<Ticket> tickets = mapper.readValue(jbosy.getValue(), javaType);
            List<Ticket> newTickets = new ArrayList<Ticket>();
            Ticket ticket = new Ticket();
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            User user = userService.getUserByJWT(header);
            //Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);
            if (tickets.size()==0) {
                return null;
            } else {
              //  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
              //  List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
                for(int i = 0;i<tickets.size();i++){
                    ticket = tickets.get(i);
                    ticket.setUpdateBy(user.getUserId());
                    ticket.setUpdated(ts);
                    Ticket oldTicket = ticketService.getTicketById(ticket.getId());
                    Student student = oldTicket.getStudent();
                    ticket.setStudent(student);
                    Ticket hqTicket = ticketService.saveTicket(ticket, null);
                    newTickets.add(hqTicket);
                }

                return newTickets;

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    //学校用户提交机票管理
    @RequestMapping(value = "/sub",method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public List<Ticket> subTicket(@RequestBody String ticketJson,@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Ticket.class);
            List<Ticket> tickets = mapper.readValue(jbosy.getValue(), javaType);
            List<Ticket> newTickets = new ArrayList<Ticket>();
            //Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);
            Ticket ticket = new Ticket();
            User user = userService.getUserByJWT(header);
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            if (tickets.size()==0) {
                return null;
            } else {
                //  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
                //  List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
                for(int i = 0;i<tickets.size();i++){
                    ticket = tickets.get(i);
                    ticket.setUpdateBy(user.getUserId());
                    ticket.setUpdated(ts);
                    ticket.setState("AT0002");//订票状态待修改成对应的代码值
                    Ticket oldTicket = ticketService.getTicketById(ticket.getId());
                    Student student = oldTicket.getStudent();
                    ticket.setStudent(student);
                    Ticket hqTicket = ticketService.saveTicket(ticket, null);
                    newTickets.add(hqTicket);
                }

                return newTickets;

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public Object getStudentGroupById(@PathVariable(value = "studentId") String studentId) {
        try {
            return ticketService.getTicketByStudentId(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    //新增机票信息
    @RequestMapping(value = "/{studentId}", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public Ticket putTicket(@PathVariable(value = "studentId") String studentId, @RequestBody String ticketJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);

            Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);

            if (ticket == null) {
                throw new NoSuchTicketException("cannot generate the ticket");
            }
            Student student = studentService.getStudentById(studentId);
            ticket.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            ticket = ticketService.addTicket(ticket, operationLogs);
            ticket.setStudent(null);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //删除机票信息
    @RequestMapping(value = "/{ticketId}/{studentId}", method = RequestMethod.DELETE, headers = "Accept=application/json; charset=utf-8")
    public Ticket deleteAccident(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header, @PathVariable(value = "ticketId") String ticketId, @PathVariable(value = "studentId") String studentId) {
        try {
            User user = userService.getUserByJWT(header);
            Ticket ticket = ticketService.deleteTicketById(user, ticketId, studentId);
            if (ticket == null) {
                throw new NoSuchTicketException("cannot delete the accident");
            }
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //修改
    @RequestMapping(value = "/{studentId}", method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Ticket editTicket(@PathVariable(value = "studentId") String studentId, @RequestBody String ticketJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);

            Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);

            if (ticket == null) {
                throw new NoSuchTicketException("cannot edit the ticket");
            }
            Student student = studentService.getStudentById(studentId);
            ticket.setStudent(student);

            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
            List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);

            ticket = ticketService.saveTicket(ticket, operationLogs);
            ticket.setStudent(null);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    }
