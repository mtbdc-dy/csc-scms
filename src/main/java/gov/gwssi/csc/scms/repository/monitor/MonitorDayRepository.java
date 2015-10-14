package gov.gwssi.csc.scms.repository.monitor;
import gov.gwssi.csc.scms.domain.monitor.MonitorDay;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("monitorDayRepository")
public interface MonitorDayRepository extends CrudRepository<MonitorDay, String> {
    List<MonitorDay> findByDayBetween(Date startDate, Date endDate);
}
