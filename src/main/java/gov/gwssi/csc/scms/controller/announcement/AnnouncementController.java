package gov.gwssi.csc.scms.controller.announcement;

import gov.gwssi.csc.scms.domain.announcement.Announcement;
import gov.gwssi.csc.scms.service.announcement.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tianjing on 2015/8/7.
 */
@RestController
@RequestMapping(value = "/announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

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
}
