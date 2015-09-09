package gov.gwssi.csc.scms.repository.monitor;

import gov.gwssi.csc.scms.domain.monitor.MonitorMonth;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("monitorMonthRepository")
public interface MonitorMonthRepository extends CrudRepository<MonitorMonth, String> {
    List<MonitorMonth> findByMonthBetween(Date startDate, Date endDate);
}
