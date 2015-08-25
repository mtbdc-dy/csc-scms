package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.student.GradeAttachment;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.GradeAttachmentService;
import gov.gwssi.csc.scms.service.student.NoSuchGradeAttachmentException;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by tianj on 2015/8/21.
 */

@RestController
@RequestMapping(value = "/students")
public class GradeAttachmentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GradeAttachmentService gradeAttachmentService;

    @RequestMapping(value = "/{studentId}/gradeattachments", method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public GradeAttachment addGradeAttachment(@PathVariable(value = "studentId") String studentId,
                                    @RequestBody String gradeAttachmentJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(gradeAttachmentJson, JsonBody.class);

            GradeAttachment gradeAttachment = mapper.readValue(jbosy.getValue(), GradeAttachment.class);

            if (gradeAttachment == null) {
                throw new NoSuchGradeAttachmentException("cannot generate the gradeAttachment");
            }
            Student student = studentService.getStudentById(studentId);
            gradeAttachment.setStudent(student);

            gradeAttachment = gradeAttachmentService.saveGradeAttachment(gradeAttachment);
            gradeAttachment.setStudent(null);
            return gradeAttachment;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/{studentId}/gradeattachments", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<GradeAttachment> getGradeAttachment(@PathVariable(value = "studentId") String studentId) {
        try {
            return gradeAttachmentService.getGradeAttachmentByStudentId(studentId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
