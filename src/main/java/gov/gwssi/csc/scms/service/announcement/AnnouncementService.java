package gov.gwssi.csc.scms.service.announcement;

import gov.gwssi.csc.scms.domain.announcement.Announcement;
import gov.gwssi.csc.scms.repository.announcement.AnnouncementRepository;
import gov.gwssi.csc.scms.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Iterator;

/**
 * Created by tianjing on 2015/8/7.
 */
@Service(value = "AnnouncementService")
public class AnnouncementService extends BaseService {
    @Autowired
    @Qualifier("announcementRepository")
    private AnnouncementRepository announcementRepository;

    public Announcement getAnnouncement() {
        Iterator<Announcement> iterator = announcementRepository.findAll().iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        } else {
            return null;
        }
    }
    public Announcement editAnnouncement(Announcement announcement) {
        return announcementRepository.save(announcement);
    }

}
