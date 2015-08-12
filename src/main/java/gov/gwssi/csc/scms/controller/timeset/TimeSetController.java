package gov.gwssi.csc.scms.controller.timeset;

import gov.gwssi.csc.scms.controller.RequestHeaderError;
import gov.gwssi.csc.scms.domain.user.User;
import gov.gwssi.csc.scms.service.timeset.TimeSetService;
import gov.gwssi.csc.scms.service.user.NoSuchUserException;
import gov.gwssi.csc.scms.service.user.UserIdentityError;
import gov.gwssi.csc.scms.service.user.UserService;
import gov.gwssi.csc.scms.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by LiZhiSheng on 2015/8/10.
 */
@RestController
@RequestMapping(value = "/timeset")
public class TimeSetController {
    @Autowired
    private TimeSetService timeSetService;
    @Autowired
    private UserService userService;
    //点击查询返回代码维护列表
    @RequestMapping(value = "/newstu",method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public List getALLCode(@RequestParam(value = "pro") String pro,@RequestParam(value = "univ") String univ) {
        //按照分页（默认）要求，返回列表内容
        List proAndUnivList = null;
        if(pro ==null||"null".equals(pro)){
            pro = "";
        }
        if(univ ==null||"null".equals(univ)||"undefined".equals(univ)){
            univ = "";
        }
        proAndUnivList = timeSetService.findProAndUniv(pro, univ);

        return proAndUnivList;
    }
    //点击查询返回代码维护列表
    @RequestMapping(value = "/newstus",
            method = RequestMethod.PUT,
            headers = {"Accept=application/json; charset=utf-8;", "Cache-Control=no-cache"})
    public void save(@RequestHeader(value = JWTUtil.HEADER_AUTHORIZATION) String header,
                     @RequestParam(value = "ids") String ids,@RequestParam(value = "begin") String begin,@RequestParam(value = "end") String end) {

      System.out.println("ids=" + ids);

            try {
                User user = userService.getUserByJWT(header);
                String userName = user.getFullName();
                timeSetService.setTime(userName, begin, end, ids);
            } catch (RequestHeaderError requestHeaderError) {
                requestHeaderError.printStackTrace();
            } catch (UserIdentityError userIdentityError) {
                userIdentityError.printStackTrace();
            } catch (NoSuchUserException e) {
                e.printStackTrace();
            }


    }
}
