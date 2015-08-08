package gov.gwssi.csc.scms.controller.students;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.filter.Filter;
import gov.gwssi.csc.scms.domain.query.StudentResultObject;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.students.StudentsService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

/**
 * Created by Wang Zishi on 15/8/6.
 * 学生 RESTful 资源控制器
 */

@RestController
@RequestMapping(value = "/students")
public class studentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentsService studentsService;

    /**
     * 返回全部学生字段的所有信息
     * GET /api/students
     * HTTP 响应中包含定制头信息“X-Total-Count”用于告知客户端集合内容的数量
     *
     * @param response http 响应
     */
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json; charset=utf-8"})
    public List<StudentResultObject> getStudents(
            HttpServletResponse response,
            @RequestHeader(value = "Authorization") String jwt,
            @RequestParam(value = "filter") String filterJSON) {
        // TODO.. GET /api/students

        List<StudentResultObject> studentResultObjects = null;
        Integer count = null;

        try {
            String content = URLDecoder.decode(filterJSON, "UTF8");
            Class<Filter> valueType = Filter.class;

            Filter filter = new ObjectMapper().readValue(content, valueType);
            // TODO.. User 部分需要优化。
            /* Map map = */
            JWTUtil.decode(jwt);

            studentsService.getStudentsByFilter(filter);

//            studentResultObjects = studentService.getStudentsByFilter(filter, null);
            count = studentResultObjects.size();
        } catch (IOException e) {
            e.printStackTrace();
        }

        response.setHeader("X-Total-Count", String.valueOf(count));
        return studentResultObjects;
    }

    /**
     * 根据 fields 内容返回部分学生字段信息
     * GET /api/students?fields=cscId,gender,age
     *
     * @param fields   所需要的字段内容
     * @param response http 响应
     */
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"fields"})
    public void getStudents(
            @RequestParam(value = "fields") String fields,
            HttpServletResponse response) {

        // TODO.. GET /api/students?fields=cscId,gender,age
        System.out.println("fields = " + fields);


        response.setHeader("X-Total-Count", "200");
    }

    /**
     * 返回全部学生字段的所有信息，并将集合根据偏离值、限定值分页
     * GET /api/students?offset=0&limit=20
     *
     * @param offset   偏离值
     * @param limit    限定值
     * @param response http 响应
     */
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"offset", "limit"})
    public void getStudents(
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "limit") Integer limit,
            HttpServletResponse response) {

        // TODO.. GET /api/students?offset=0&limit=20
        System.out.println("offset = " + offset);
        System.out.println("limit = " + limit);


        response.setHeader("X-Total-Count", "200");
        response.setHeader("Link", "</api/students?offset=0&limit=20>;rel=\"self\"");
    }

    /**
     * 返回全部学生字段的所有信息，并将集合根据偏离值、限定值分页
     * GET /api/students?fields=cscId,gender,age&offset=0&limit=20
     *
     * @param fields   所需要的字段内容
     * @param offset   偏离值
     * @param limit    限定值
     * @param response http 响应
     */
    @RequestMapping(
            method = RequestMethod.GET,
            headers = {"Accept=application/json"},
            params = {"fields", "offset", "limit"})
    public void getStudents(
            @RequestParam(value = "fields") String fields,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "limit") Integer limit,
            HttpServletResponse response) {

        // TODO.. GET /api/students?fields=cscId,gender,age&offset=0&limit=20
        System.out.println("fields = " + fields);
        System.out.println("offset = " + offset);
        System.out.println("limit = " + limit);


        response.setHeader("X-Total-Count", "200");
        response.setHeader("Link", "</api/students?offset=0&limit=20>;rel=\"self\"");
    }

    /**
     * 根据学生id返回但个学生的所有信息
     * GET /api/students/20150808
     *
     * @param id 学生 id（studentId）
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            headers = {"Accept=application/json"}
    )
    public void getStudent(@PathVariable("id") String id) {

        // TODO.. GET /api/students/20150808
        System.out.println("id = " + id);
    }

    /**
     * 根据 fields 返回对应学生的部分信息
     * GET /api/students/20150808?fields=basicInfo,schoolRoll
     *
     * @param id     学生 id（studentId）
     * @param fields 所需要的字段内容
     */
    @RequestMapping(
            method = RequestMethod.GET,
            value = "/{id}",
            headers = {"Accept=application/json"},
            params = {"fields"}
    )
    public void getStudent(@PathVariable("id") String id, String fields) {

        // TODO.. GET /api/students/20150808?fields=basicInfo,schoolRoll
        System.out.println("id = " + id);
        System.out.println("fields = " + fields);
    }

    /**
     * 创建新的学生
     * POST /api/students
     */
    @RequestMapping(
            method = RequestMethod.POST,
            headers = {"Accept=application/json"}
    )
    public void createStudent() {
        // TODO.. POST /api/students
    }

    /**
     * 完整更新学生信息
     * PUT /api/students/20150808
     *
     * @param body http 请求体
     */
    @RequestMapping(
            method = RequestMethod.PUT,
            headers = {"Accept=application/json"}
    )
    public void updateStudent(@RequestBody String body) {
        // TODO.. PUT /api/students/20150808
        System.out.println("body = " + body);
    }

    /**
     * 部分更新学生信息
     * PATCH /api/students/20150808
     */
    @RequestMapping(
            method = RequestMethod.PATCH,
            headers = {"Accept=application/json"}
    )
    public void updateStudent() {
        // TODO.. PATCH /api/students/20150808
    }

    /**
     * 删除指定的学生信息
     * DELETE /api/students/20150808
     */
    @RequestMapping(
            method = RequestMethod.DELETE,
            headers = {"Accept=application/json"}
    )
    public void deleteStudent() {
        // TODO.. DELETE /api/students/20150808
    }


}
