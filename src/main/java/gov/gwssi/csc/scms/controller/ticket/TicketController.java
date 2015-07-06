package gov.gwssi.csc.scms.controller.ticket;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.ticket.TicketService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserService;
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
    //学校用户在前台点击生成机票管理列表，返回列表
    @RequestMapping(value = "/new",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<TicketResultObject> getTickets(@RequestParam(value = "userId") String userId) throws NoSuchUserException {
         User user = userService.getUserByUserIdAndEnable("123", "1");
            if (user == null) {
                throw new NoSuchUserException(userId);
            }
        List<TicketResultObject> ticketResultObjectList = ticketService.getTicketList(user);
        return ticketResultObjectList;
    }
    //学校用户在前台点击查询，返回列表
            @RequestMapping(value = "/select",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
            public List<TicketResultObject> getTicketsByConditions(@RequestParam(value = "filter") String filter, @RequestParam(value = "userId") String userId) throws NoSuchUserException {
                try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

            User user = userService.getUserByUserIdAndEnable("123", User.ENABLE);
            if (user == null) {
                throw new NoSuchUserException(userId);
            }

            //按照分页（默认）要求，返回列表内容
            List<TicketResultObject> ticketResultObjects = ticketService.getTicketListByFilter(sfo,user);
            return ticketResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
                    throw new RuntimeException(uee);
        } catch (IOException e) {
            e.printStackTrace();
                    throw new RuntimeException(e);
        }
//        catch (NoSuchUserException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
    //修改机票管理
    @RequestMapping(value = "/save",method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public List<Ticket> modTicket(@RequestBody String ticketJson, @RequestParam(value = "userId") String userId) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Ticket.class);
            List<Ticket> tickets = mapper.readValue(jbosy.getValue(), javaType);
            List<Ticket> newTickets = new ArrayList<Ticket>();
            Ticket ticket = new Ticket();
            Timestamp ts = new Timestamp(System.currentTimeMillis());

            //Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);
            if (tickets.size()==0) {
                return null;
            } else {
              //  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
              //  List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
                for(int i = 0;i<tickets.size();i++){
                    ticket = tickets.get(i);
                    ticket.setUpdateBy(userId);
                    ticket.setUpdated(ts);
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
    public List<Ticket> subTicket(@RequestBody String ticketJson, @RequestParam(value = "userId") String userId) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);
            JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, Ticket.class);
            List<Ticket> tickets = mapper.readValue(jbosy.getValue(), javaType);
            List<Ticket> newTickets = new ArrayList<Ticket>();
            //Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);
            Ticket ticket = new Ticket();
            Timestamp ts = new Timestamp(System.currentTimeMillis());
            if (tickets.size()==0) {
                return null;
            } else {
                //  JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
                //  List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
                for(int i = 0;i<tickets.size();i++){
                    ticket = tickets.get(i);
                    ticket.setUpdateBy(userId);
                    ticket.setUpdated(ts);
                    ticket.setState("1");//订票状态待修改成对应的代码值
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
    }
