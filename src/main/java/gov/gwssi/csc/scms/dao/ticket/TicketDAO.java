package gov.gwssi.csc.scms.dao.ticket;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.query.StudentFilter;
import gov.gwssi.csc.scms.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzs on 2015/6/1.
 * ticket DAO封装类
 */
@Service("ticketDAO")
public class TicketDAO extends BaseDAO {
    public List getStudentList(User user) {
        List studentList = null;

        String userType = user.getUserType();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT SCMS_STUDENT.ID,CSCID,PASSPORTNAME,GENDER,BIRTHDAY,COUNTRY,CERTIFICATENO,TRAVELTYPE FROM SCMS_STUDENT,SCMS_SCHOOLROLL schoolRoll,SCMS_BASIC_INFO ");
        stringBuilder.append(" where SCMS_STUDENT.id = SCMS_BASIC_INFO.STUDENTID and SCMS_STUDENT.id = schoolRoll.STUDENTID");
        if ("2".equals(userType)) {
            stringBuilder.append(" and schoolRoll.currentUniversity = '").append(user.getNode().getNodeId()).append("' ");
            stringBuilder.append(StudentFilter.LEAVEDATA_STUDENT_CONDITION);//  离华时间
            stringBuilder.append(StudentFilter.INTERNAL_STUDENT_CONDITION);// 在校生条件

        }
        stringBuilder.append(" and SCMS_STUDENT.id not in (select distinct STUDENTID from SCMS_AIRTICKET)");
        studentList = super.queryListBySql(stringBuilder.toString());
        return studentList;
    }
    public void doSt(String name, List list){
       // super.doStatement(name,list);
       String str =  super.doStatementForRtn(name,list);
System.out.println("str  ="+str);
    }

}
