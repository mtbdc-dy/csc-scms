package gov.gwssi.csc.scms.controller.student;

//import gov.gwssi.csc.scms.domain.Student;
//import gov.gwssi.csc.scms.service.StudentService;
//import org.springframework.beans.factory.annotation.Autowired;
import gov.gwssi.csc.scms.domain.Student;
import gov.gwssi.csc.scms.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//import java.util.List;

/**
 * Created by WangZishi on 3/27/2015.
 *
 */
@RestController
@RequestMapping("/service/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    //
//    @RequestMapping (method  = RequestMethod.GET, headers = "Accept=application/json")
//    public List<Student> getAllStudent(){
//
//        return studentService.findAllStudent();
//    }
    @RequestMapping (
            value = "/{id}",
            method = RequestMethod.POST
    )
    public Student saveStudent(@PathVariable String id){
        Student student = new Student();
        student.setCscNumber(id);
        student.setCertificateNumber(id);
        student.setName(id);
        student.setGender(id);

        return studentService.save(student);
    }

    @RequestMapping (method = RequestMethod.GET, headers = "Accept=application/json")
    public String getAllStudent(){

        return "1";
    }
}
