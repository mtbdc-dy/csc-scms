package gov.gwssi.csc.scms.repository.monitor;

import gov.gwssi.csc.scms.domain.monitor.Monitor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository("monitorRepository")
public interface MonitorRepository extends CrudRepository<Monitor, String> {
    List<Monitor> findByCreateDBetween(Date startDate, Date endDate);
}
