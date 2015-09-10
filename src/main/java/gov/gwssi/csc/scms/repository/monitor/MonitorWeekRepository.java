package gov.gwssi.csc.scms.repository.monitor;

import gov.gwssi.csc.scms.domain.monitor.MonitorWeek;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("monitorWeekRepository")
public interface MonitorWeekRepository extends CrudRepository<MonitorWeek, String> {
    List<MonitorWeek> findByWeekBetween(Date startDate, Date endDate);
}
