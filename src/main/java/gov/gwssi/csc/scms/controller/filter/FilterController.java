package gov.gwssi.csc.scms.controller.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.domain.query.StudentFilterObject;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.repository.user.UserException;
import gov.gwssi.csc.scms.service.student.StudentService;
import gov.gwssi.csc.scms.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by Murray on 4/23/2015.
 */
@RestController
@RequestMapping(value = "/filterResult")
public class FilterController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8")
    public int getCountByConditions(@RequestParam String filter, @RequestParam String userId) {
        try {
            User user = userService.getUserByUserId(userId);
            if (user == null) {
                throw new UserException("can't find the user by userId:" + userId);
            }

            String jsonStr = URLDecoder.decode(filter, "utf-8");
            StudentFilterObject sfo = new ObjectMapper().readValue(jsonStr, StudentFilterObject.class);
            return studentService.getCountByQueryFilter(sfo, user);
        } catch (UnsupportedEncodingException uee) {
            uee.printStackTrace();
            return 0;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        } catch (UserException e) {
            e.printStackTrace();
            return 0;
        }
    }

}
