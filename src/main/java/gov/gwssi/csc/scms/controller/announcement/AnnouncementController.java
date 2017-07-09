package gov.gwssi.csc.scms.controller.announcement;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import gov.gwssi.csc.scms.controller.JsonBody;
import gov.gwssi.csc.scms.domain.announcement.Announcement;
import gov.gwssi.csc.scms.service.announcement.AnnouncementService;
import gov.gwssi.csc.scms.service.announcement.NoSuchAnnouncementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by tianjing on 2015/8/7.
 * 公告维护控制器
 */
@RestController
@RequestMapping(value = "/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    //查询公告
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json; charset=utf-8;Cache-Control=no-cache")
    public Announcement getAnnouncement() {
        try {
            Announcement note = announcementService.getAnnouncement();
            return note;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //修改公告
    @RequestMapping(method = RequestMethod.PUT, headers = "Accept=application/json; charset=utf-8")
    public Announcement editGrade(@RequestBody String announcementJson) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            JsonBody jbosy = new ObjectMapper().readValue(announcementJson, JsonBody.class);

            Announcement announcement = mapper.readValue(jbosy.getValue(), Announcement.class);

            if (announcement == null) {
                throw new NoSuchAnnouncementException("cannot edit the announcement");
            }
            announcement = announcementService.editAnnouncement(announcement);
            return announcement;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
