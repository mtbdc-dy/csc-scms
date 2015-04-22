package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

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
        //尚未开通支持
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    public String getStuInfoList() {
        //尚未开通支持
        return null;
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public String addStudent(@RequestBody String body) {
//        ObjectMapper mapper = new ObjectMapper();
//        try {
//            Student student = mapper.readValue(body, Student.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "-1";
//        }
        //尚未开通支持
        return "no such method";
    }

    /**
     * 学籍信息管理相关操作，获取学生列表
     * 请求信息为Json格式对应的StudentFilterObject类
     *
     * @param body
     * @return
     */
    @RequestMapping(method = RequestMethod.OPTIONS, headers = "Accept=application/json")
    public List<StudentResultObject> getStudentsByConditions(@RequestBody String body) {
        ObjectMapper mapper = new ObjectMapper();
        StudentFilterObject sfo = null;
        try {
            sfo = mapper.readValue(body, StudentFilterObject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        //按照分页（默认）要求，返回列表内容
        List<StudentResultObject> studentResultObjects = studentService.getStudentsByFilter(sfo);
        //获取列表最大量
        int count = studentService.getCountByQueryFilter(sfo);

        return studentResultObjects;
    }

}
