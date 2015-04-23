package gov.gwssi.csc.scms.controller.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.student.BasicInfo;
import gov.gwssi.csc.scms.service.student.BasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/service/basicinfo")
public class BasicInfoController {

    @Autowired
    private BasicInfoService basicInfoService;

    @RequestMapping(value = "/{studentId}", method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public BasicInfo saveStudent(@PathVariable String studentId) {
        try {
            Long student = Long.parseLong(studentId);

            BasicInfo basicInfo = basicInfoService.getBasicInfoByStudentId(student);

            basicInfo.setStudent(null);
            return basicInfo;
        } catch (NumberFormatException ne1) {
            throw new RuntimeException(ne1);
        } catch (NullPointerException ne2) {
            return null;
        }
    }

    @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json; charset=utf-8")
    public String getStudentsByConditions(@RequestBody String body) {
        try {
            BasicInfo basicInfo = new ObjectMapper().readValue(body, BasicInfo.class);
            System.out.println(basicInfo.toString());
            if (basicInfoService.updateBasicInfo(basicInfo) == null) {
                return "ERROR";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return "SUCCESS";
    }
}
