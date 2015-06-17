package gov.gwssi.csc.scms.service.student;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.base.UnitTestBase;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.log.OperationLog;
import gov.gwssi.csc.scms.domain.student.SchoolRoll;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by Lei on 2015-06-17.
 */
public class SchoolrollTest extends UnitTestBase {

    @Test
    public void updateTest() {
        SchoolRollService schoolRollService = getBean(SchoolRollService.class);

        SchoolRoll schoolRoll = schoolRollService.getSchoolRollById("1");

        schoolRoll.setStudent(null);

        schoolRoll.setRegisterYear(2015);

        schoolRollService.updateSchoolRoll(schoolRoll);
    }

    @Test
    public void groupUpdateTest() throws Exception {
        String group = "schoolRoll";

        StudentService studentService = getBean(StudentService.class);
        SchoolRoll schoolRoll = studentService.getStudentById("1").getSchoolRoll();

        schoolRoll.setStudent(null);
        schoolRoll.setRegisterYear(2015);
        schoolRoll.setRegisterState("2");

        ObjectMapper mapper = new ObjectMapper();
        JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, OperationLog.class);
        List<OperationLog> operationLogs = mapper.readValue("[]", javaType);


        studentService.updateGroupByName(group, schoolRoll, operationLogs);
    }
}
