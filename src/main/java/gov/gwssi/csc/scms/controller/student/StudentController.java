package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by WangZishi on 3/27/2015.
 */
@RestController
@RequestMapping("/service/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.POST
    )

    @Transactional
    public Student saveStudent(@PathVariable String id) {
        Student student = new Student();
        student.setCsc_id(id);
//        student.setCertificateNumber(id);
//        student.setName(id);
//        student.setGender(id);

        return studentService.save(student);
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public String getStuInfoList() {
        System.out.println("backlist===" + studentService.getStuInfoList().toString());
        //list<>
        return "1";
    }
//123
    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    @Transactional
    public String addStudent(@RequestBody String body) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Student student = mapper.readValue(body, Student.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "1";
    }

}
