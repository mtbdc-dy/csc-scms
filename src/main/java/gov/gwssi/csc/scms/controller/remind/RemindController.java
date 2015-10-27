package gov.gwssi.csc.scms.controller.remind;

import gov.gwssi.csc.scms.domain.remind.Remind;
import gov.gwssi.csc.scms.service.remind.RemindService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by tianj on 2015/8/12.
 */
@RestController
@RequestMapping(value = "/remind")
public class RemindController {

    @Autowired
    private RemindService remindService;

    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public Map<String,Integer> getReminds(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header) {
        try {
            Map<String,Integer> reminds = remindService.getReminds(header);
            return reminds;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
