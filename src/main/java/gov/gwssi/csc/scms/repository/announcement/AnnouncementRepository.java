package gov.gwssi.csc.scms.repository.announcement;

import gov.gwssi.csc.scms.domain.announcement.Announcement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by tianjing on 2015/8/7.
 */
@Repository("announcementRepository")
public interface AnnouncementRepository extends CrudRepository<Announcement, String> {
}
