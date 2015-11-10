package gov.gwssi.csc.scms.service.ticket;

import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.ticket.Ticket;
import gov.gwssi.csc.scms.domain.ticket.TicketSort;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tianj on 2015/8/29.
 */
public class TicketSortConverter extends BaseService implements Converter<TicketSort, Map<String, Object>>  {

    @Override
    public Map<String, Object> convert(TicketSort ticket) {
        Map<String, Object> target = new HashMap<String, Object>();
        Student student = ticket.getStudent();
        BasicInfo basicInfo = student.getBasicInfo();
        SchoolRoll schoolRoll = student.getSchoolRoll();
        target.put("id",ticket.getId());
        target.put("studentId",student.getId());
        target.put("currentUniversity",schoolRoll.getCurrentUniversity());
        target.put("cscId",student.getCscId());
        target.put("passportName",basicInfo.getPassportName());
        target.put("gender",basicInfo.getGender());
        target.put("birthday",basicInfo.getBirthday());
        target.put("certificateNO",schoolRoll.getCertificateNO());
        target.put("country",basicInfo.getCountry());
        target.put("type",ticket.getType());
        target.put("airLine",ticket.getAirLine());
        target.put("ticketNo",ticket.getTicketNo());
        target.put("applyDate",ticket.getApplyDate());
        target.put("validdate",ticket.getValiddate());
        target.put("flightDate",ticket.getFlightDate());
        target.put("leaveCity",ticket.getLeaveCity());
        target.put("price",ticket.getPrice());
        target.put("state",ticket.getState());
        target.put("remark",ticket.getRemark());
        target.put("createBy",ticket.getCreateBy());
        target.put("created",ticket.getCreated());
        target.put("updateBy",ticket.getUpdateBy());
        target.put("updated",ticket.getUpdated());
        return target;
    }
}
