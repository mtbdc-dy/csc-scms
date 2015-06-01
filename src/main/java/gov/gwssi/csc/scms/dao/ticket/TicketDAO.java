package gov.gwssi.csc.scms.dao.ticket;

import gov.gwssi.csc.scms.dao.BaseDAO;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.domain.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lzs on 2015/6/1.
 * ticket DAO²ã·â×°Àà
 */
@Service("ticketDAO")
public class TicketDAO extends BaseDAO {
    public List<Student> getStudentList(User user) {
            List studentList = null;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT SCMS_STUDENT.ID,CSCID,PASSPORTNAME,GENDER,BIRTHDAY,COUNTRY,CERTIFICATENO,TRAVELTYPE FROM SCMS_STUDENT,SCMS_SCHOOLROLL,SCMS_BASIC_INFO ");
        stringBuilder.append(" where SCMS_STUDENT.id = SCMS_BASIC_INFO.STUDENTID and SCMS_STUDENT.id = SCMS_SCHOOLROLL.STUDENTID");
        studentList = super.queryListBySql(stringBuilder.toString());
return studentList;
    }

}
