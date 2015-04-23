package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.domain.student.Student;
import gov.gwssi.csc.scms.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by WangZishi on 3/27/2015.
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 学籍信息管理相关操作，获取学生列表
     * 请求信息为Json格式对应的StudentFilterObject类
     *
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public List<StudentResultObject> getStudentsByConditions(@RequestParam(value = "filter", required = true) String filter) {

        try {
            StudentFilterObject sfo = null;
            sfo = new ObjectMapper().readValue(URLDecoder.decode(filter, "utf-8"), StudentFilterObject.class);

            //按照分页（默认）要求，返回列表内容
            List<StudentResultObject> studentResultObjects = studentService.getStudentsByFilter(sfo);
            return studentResultObjects;
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public Student getStudentById(@PathVariable(value = "id") String id) {
        try {
            Long studentId = Long.parseLong(id);
            Student student = studentService.getStudentById(studentId);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
