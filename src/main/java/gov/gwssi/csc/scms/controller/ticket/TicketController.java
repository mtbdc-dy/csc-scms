package gov.gwssi.csc.scms.controller.ticket;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.TicketResultObject;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.service.ticket.TicketService;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
    public List<TicketResultObject> getTickets(@RequestParam(value = "userId") String userId) {
//         User user = userService.getUserByUserId(userId);
//            if (user == null) {
//                throw new NoSuchUserException(userId);
//            }
        List<TicketResultObject> ticketResultObjectList = ticketService.getTicketList(null);
        return ticketResultObjectList;
    }
    //学校用户在前台点击查询，返回列表
            @RequestMapping(value = "/select",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
            public List<TicketResultObject> getTicketsByConditions(@RequestParam(value = "filter") String filter, @RequestParam(value = "userId") String userId) {
                try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

//            User user = userService.getUserByUserId(userId);
//            if (user == null) {
//                throw new NoSuchUserException(userId);
//            }

            //按照分页（默认）要求，返回列表内容
            List<TicketResultObject> ticketResultObjects = ticketService.getTicketListByFilter(sfo,null);
            return ticketResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
//        catch (NoSuchUserException e) {
//            e.printStackTrace();
//            return null;
//        }
    }
    //修改机票管理
    @RequestMapping(value = "/save",method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Ticket modTicket(@RequestBody String ticketJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            boolean rv = true;
            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);
            Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);
            if (ticket == null) {
                return null;
            } else {
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
                List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
                ticket = ticketService.saveTicket(ticket, null);
                return ticket;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    //学校用户提交机票管理
    @RequestMapping(value = "/sub",method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Ticket subTicket(@RequestBody String ticketJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            boolean rv = true;
            JsonBody jbosy = new ObjectMapper().readValue(ticketJson, JsonBody.class);
            Ticket ticket = mapper.readValue(jbosy.getValue(), Ticket.class);
            if (ticket == null) {
                return null;
            } else {
                ticket.setState("1");
                JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
                List<OperationLog> operationLogs = mapper.readValue(jbosy.getLog(), javaType);
                ticket = ticketService.saveTicket(ticket, null);
                return ticket;

            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    }
